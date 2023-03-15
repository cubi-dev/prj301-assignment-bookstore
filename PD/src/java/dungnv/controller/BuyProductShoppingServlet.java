/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dungnv.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
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
@WebServlet(name = "BuyProductShoppingServlet", urlPatterns = {"/BuyProductShoppingServlet"})
public class BuyProductShoppingServlet extends HttpServlet {
//        private final String SHOPPING_PRODUCT_CONTROLLER = "ShoppingPageServlet"; 

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
        String url = siteMaps.getProperty(MyApplicationConstants.CartProductFeature.SHOPPING_PRODUCT_PAGE_CONTROLLER);
        try {
//        1. Customer goes to product placement(SessionScope)  
            HttpSession session = request.getSession();
//        2. Customer take product(attribute) 
            cartObject cart = (cartObject) session.getAttribute("PRODUCTS");//Dùng CartObj để dùng các method bởi Cart là 1 Attribute
            if (cart == null) {
                cart = new cartObject();
            }
//        3. Customer choose one product(parameter) 
            String id = request.getParameter("txtID");
            String quantity = request.getParameter("txtQuantity");
//        4. Customer drop product to cart 
            cart.addItemToCart(id);
//        4.1 Update Product in Inventory 
//            ProductDAO dao = new ProductDAO(); 
//            dao.updateProductInInventory(id, quantity); 
            session.setAttribute("PRODUCTS", cart);
//        }catch(SQLException ex){
//        }catch(NamingException ex){
        } finally {
//        5. Turn back to step 3 (IF) 
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