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
import java.util.Properties;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import dungnv.listener.MyContextServletListener;
import dungnv.product.ProductDAO;
import dungnv.product.ProductDTO;
import dungnv.utils.MyApplicationConstants;

/**
 *
 * @author ASUS
 */
@WebServlet(name = "ViewProductOrderCartServlet", urlPatterns = {"/ViewProductOrderCartServlet"})
public class ViewProductOrderCartServlet extends HttpServlet {
//    private final String SHOPPING_PAGE = "shoppingList.jsp";//shoppingProductPage
//    private final String VIEW_PRODUCT_CART_PAGE = "viewProductCart.jsp";//viewProductCartPage
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
        String url = siteMaps.getProperty(MyApplicationConstants.CartProductFeature.SHOPPING_PRODUCT_PAGE);
        try {
//            1. Call DAO
            ProductDAO dao = new ProductDAO();
//          2. Call method  
            dao.getProductFromInventory();
            List<ProductDTO> result = dao.getItems();
            request.setAttribute("ITEMS_RESULT", result);
            url = siteMaps.getProperty(MyApplicationConstants.CartProductFeature.VIEW_PRODUCT_CART_PAGE); 
        }catch(SQLException ex){
            log("SQL Exception: "+ ex.getMessage());
        }catch(NamingException ex){
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
