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
import dungnv.product.ProductDAO;
import dungnv.product.ProductDTO;
import dungnv.utils.MyApplicationConstants;

/**
 *
 * @author ASUS
 */
@WebServlet(name = "CheckOutProductServlet", urlPatterns = {"/CheckOutProductServlet"})
public class CheckOutProductServlet extends HttpServlet {

//    private final String ERROR_PAGE = "error.html";//errorPage
//    private final String CHECK_OUT_PRODUCT_CONTROLLER = "checkOut.jsp";//checkOutProductPage
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
        String url = siteMaps.getProperty(MyApplicationConstants.DispatchFeature.ERROR_PAGE);
        try {
            HttpSession session = request.getSession();
            cartObject cart = (cartObject) session.getAttribute("PRODUCTS");//BuyProductShoppingServlet
            if (cart != null) {
                Map<String, Integer> items = cart.getItems();
                if (items != null) {
                    ProductDAO dao = new ProductDAO();
                    for (String id : items.keySet()) {
//           1. Call DAO 
//           2. Call method 
                        dao.getProductFromCart(id);
                    }
                    List<ProductDTO> list = dao.getList();
                    session.setAttribute("CHECKOUT_RESULT", list);
                    url = siteMaps.getProperty(MyApplicationConstants.CartProductFeature.CHECK_OUT_PRODUCT_PAGE);
                }
            }
        } catch (SQLException ex) {
            log("SQL Exception: " + ex.getMessage());
        } catch (NamingException ex) {
            log("Naming Exception: "+ ex.getMessage());
        } finally {
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
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
