/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dungnv.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import dungnv.registration.RegistrationDAO;
import dungnv.registration.RegistrationDTO;
import dungnv.utils.MyApplicationConstants;
import java.util.Properties;
import javax.servlet.ServletContext;

/**
 *
 * @author ASUS
 */
@WebServlet(name = "FirstTimeServletRequest", urlPatterns = {"/FirstTimeServletRequest"})
public class FirstTimeServletRequest extends HttpServlet {

//    private final String LOGIN_PAGE = "login.html";//loginPage
//    private final String SEARCH_PAGE = "search.jsp";//searchPage

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
        String url = siteMaps.getProperty(MyApplicationConstants.DispatchFeature.LOGIN_PAGE);
        try {
//          1. get all Cookies form Request 
            Cookie[] cookies = request.getCookies();
            if (cookies != null) {
//          2. Traverse all cookies to check authentication
                for (Cookie cookie : cookies) {
                    String username = cookie.getName();
                    String password = cookie.getValue();
//          3. Call DAO to checkLogin
                    RegistrationDAO dao = new RegistrationDAO();
//                boolean result = dao.checkLogin(userName, passWord); 
                    RegistrationDTO result = dao.checkLogin(username, password);
                    HttpSession session = request.getSession();
                    session.setAttribute("USER", result);
//          4. process
                    if (result != null) {
                        url = siteMaps.getProperty(MyApplicationConstants.SearchFeature.SEARCH_PAGE);
                        break;
                    }//End user has existed 
                }//End for traverse cookies 
            }//End cookies has existed
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (NamingException e) {
            e.printStackTrace();
        } finally {
            response.sendRedirect(url);
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
