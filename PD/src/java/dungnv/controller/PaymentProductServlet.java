/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dungnv.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import dungnv.cart.cartObject;
import dungnv.orders.OrderDAO;
import dungnv.product.ProductDAO;
import dungnv.product.ProductDTO;
import dungnv.utils.MyApplicationConstants;

/**
 *
 * @author ASUS
 */
@WebServlet(name = "PaymentProductServlet", urlPatterns = {"/PaymentProductServlet"})
public class PaymentProductServlet extends HttpServlet {

//    private final String SHOPPING_PAGE_CONTROLLER = "ShoppingPageServlet";//shoppingProductPageController
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        ServletContext context = this.getServletContext();
        Properties siteMaps = (Properties) context.getAttribute("SITE_MAPS");
        String totalBill = request.getParameter("txtTotalBill");
        HttpSession session = request.getSession();
        cartObject cart = (cartObject) session.getAttribute("PRODUCTS"); //Copy BuyProductShoppingServlet
        List<ProductDTO> list = (List<ProductDTO>) session.getAttribute("CHECKOUT_RESULT"); //Copy CheckOutProductServlet
        boolean result = false;
        if (cart != null) {//Check cart's Product có hay chưa 
            String url = siteMaps.getProperty(MyApplicationConstants.CartProductFeature.SHOPPING_PRODUCT_PAGE_CONTROLLER); //Mặc định sẽ về trang mua sản phẩm 
            try {
//                Get items form cart 
                Map<String, Integer> items = cart.getItems();//Vì cart gồm (id, quantity) (cái này lấy các productBuy)
//                1. Call DAO 
                OrderDAO ordersDao = new OrderDAO();
                ProductDAO productsDAO = new ProductDAO();
                            result = ordersDao.updateOrders(totalBill);
                for (String skuItems : items.keySet()) {//Duyệt các key(vì sử dụng items.keySet)trong Map<String, Integer> items
                    for (ProductDTO productDTO : list) {//Duyệt các key(id) trong List<ProductDTO>
                        if (productDTO.getSku().equals(skuItems) && productDTO.getQuantity() >= items.get(skuItems)) {//[id(Product) = id(Cart)] và quantity(Product) >= quantity(Cart)
                            //2. update order and orderdetail(call method)
                            ordersDao.updateOrderDetail(skuItems, items.get(skuItems));
                            productsDAO.updateItemsInInventory(//String id  |   quantityBuy |   quantityInInventory      
                                    productDTO.getSku(), items.get(skuItems), productDTO.getQuantity());
                        }
                    }
                }
                session.invalidate();
                request.setAttribute("SIGNAL", result);//shoppingList.jsp (Lúc này sẽ lấy 1 scope để check xem đã Pay thành công hay chưa)
            } catch (SQLException ex) {
                log("SQL Exception :" + ex.getMessage());
            } catch (NamingException ex) {
                log("Naming exception :" + ex.getMessage());
            } finally {
                RequestDispatcher rd = request.getRequestDispatcher(url);
                rd.forward(request, response);
            }
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
