/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dungnv.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;
import java.util.Properties;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import dungnv.cart.cartObject;
import dungnv.utils.MyApplicationConstants;

/**
 *
 * @author ASUS
 */
@WebServlet(name = "RemoveProductFromCartServlet", urlPatterns = {"/RemoveProductFromCartServlet"})
public class RemoveProductFromCartServlet extends HttpServlet {
//        private final String SHOPPING_PAGE_CONTROLLER = "ShoppingPageServlet";//shoppingProductPageController
//        private final String VIEW_PRODUCT_CART_PAGE = "viewProductCart.jsp"; //viewProductCartPage

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
        String url = siteMaps.getProperty(MyApplicationConstants.CartProductFeature.VIEW_PRODUCT_CART_PAGE);
        try {
//           1. Customer goes to his/her product's cart placement(sessionScope) 
            HttpSession session = request.getSession(false);
            if (session != null) {
//           2. Customer takes his/her product's cart(attribute) 
                cartObject cart = (cartObject) session.getAttribute("PRODUCTS");
                if (cart != null) {
//           3. Customer check items(Map)  
                    Map<String, Integer> products = cart.getItems();
                    if (products != null) {
//           4. Customer get all selected items(parameter)
                        String[] removedProducts = request.getParameterValues("ckProducts");
                        if (removedProducts != null) {
//           5. Remove all selected items
                            for (String product : removedProducts) {
                                cart.removeItemFromCart(product);
                                url = siteMaps.getProperty(MyApplicationConstants.CartProductFeature.SHOPPING_PRODUCT_PAGE_CONTROLLER);
                            }//end traverse products 
                        }//end items have existed 
                    }//end if product have existed 
                }//end if cart have existed 
            }//session have existed 
        } finally {
//           6. Refresh recall viewProductCart again 
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
