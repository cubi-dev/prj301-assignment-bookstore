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
import dungnv.registration.RegistrationCreateError;
import dungnv.registration.RegistrationDAO;
import dungnv.registration.RegistrationDTO;
import dungnv.utils.MyApplicationConstants;

/**
 *
 * @author CUBI
 */
@WebServlet(name = "CreateNewAccountServlet", urlPatterns = {"/CreateNewAccountServlet"})
public class CreateNewAccountServlet extends HttpServlet {

//    private final String ERROR_PAGE = "createNewAccount.jsp";//CREATE_NEW_ACCOUNT_PAGE 
//    private final String LOGIN_PAGE = "login.html";

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
        String url = siteMaps.getProperty(MyApplicationConstants.DispatchFeature.CREATE_NEW_ACCOUNT_PAGE2);
        String username = request.getParameter("txtUsername");
        String password = request.getParameter("txtPassword");
        String confirm = request.getParameter("txtConfirm");
        String fullname = request.getParameter("txtFullname");
        boolean errorFound = false;
        RegistrationCreateError errors = new RegistrationCreateError();
        try {
//           1. Check user error 
            if (username.trim().length() < 6 || username.trim().length() > 20) {
                errorFound = true;
                errors.setUserNameIsExisted("Username is required input form 6 to 20 characters");
            }//end username check
            if (password.trim().length() < 6 || password.trim().length() > 20) {
                errorFound = true;
                errors.setPasswordLengthError("Password is required input form 6 to 20 characters");
            } else if (!confirm.trim().equals(password)) {
                errorFound = true;
                errors.setConfirmNotMatched("Confirm doesn't matched the Password");
            }//end password & confirm check 
            if (fullname.trim().length() < 2 || fullname.trim().length() > 50) {
                errorFound = true;
                errors.setFullnameLengthError("Full name is required input form 2 to 50 characters");
            }//end fullname check
            if (errorFound) {
//                1.1 cache store
                request.setAttribute("CREATE_ERROR", errors);
//                1.2 show error to user 
            } else {
//                2. Insert to DB 
                RegistrationDAO dao = new RegistrationDAO();
                RegistrationDTO dto = new RegistrationDTO(username, password, fullname, false);
                boolean result = dao.createAccount(dto);
                if (result) {
                    url = siteMaps.getProperty(MyApplicationConstants.DispatchFeature.LOGIN_PAGE);
                }
            }
//               3.Check System's error 
        } catch (SQLException ex) {
            String errMsg = ex.getMessage();
            log("Create Account SQL " + ex);
            if (errMsg.contains("duplicate")) {
                errors.setUserNameIsExisted(username + " is Existed");
                request.setAttribute("CREATE_ERROR", errors);
            }
        } catch (NamingException ex) {
            log("Create Account SQL " + ex);
        } finally {
//               4.Transfer Specifies Page
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
