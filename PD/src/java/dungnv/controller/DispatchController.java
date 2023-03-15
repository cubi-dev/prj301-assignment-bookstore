///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package dungnv.controller;
//
//import java.io.IOException;
//import java.io.PrintWriter;
//import java.util.Properties;
//import javax.servlet.RequestDispatcher;
//import javax.servlet.ServletContext;
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import dungnv.utils.MyApplicationConstants;
//
///**
// *
// * @author ASUS
// */
//@WebServlet(name = "DispatchController", urlPatterns = {"/DispatchController"})
//public class DispatchController extends HttpServlet {
////    private final String LOGIN_PAGE = "login.html"; 
////    private final String LOGIN_CONTROLLER = "LoginServlet"; 
////    private final String SEARCH_LAST_NAME_CONTROLLER = "SearchLastNameServlet"; 
////    private final String FIRST_TIME_REQUEST = "FirstTimeServletRequest";
////    private final String LOGOUT_CONTROLLER = "LogoutServlet"; 
////    private final String DELETE_ACCOUNT_CONTROLLER = "DeleteAccountServlet";
////    private final String UPDATE_ACCOUNT_CONTROLLER = "UpdateAccountServlet";
////    private final String CREATE_NEW_ACCOUNT_CONTROLLER = "CreateNewAccountServlet"; 
////    private final String ADD_ITEM_TO_CART_CONTROLLER = "AddItemToCartServlet";
////    private final String VIEW_CART_PAGE = "viewCart.jsp";
////    private final String REMOVE_ITEM_FROM_CART_CONTROLLER = "RemoveItemFromCartServlet";
////    private final String SHOPPING_PAGE_CONTROLLER = "ShoppingPageServlet";
////    private final String BUY_PRODUCT_SHOPPING_CONTROLLER = "BuyProductShoppingServlet";
////    private final String VIEW_PRODUCT_ORDER_CONTROLLER = "ViewProductOrderCartServlet";
////    private final String REMOVE_PRODUCT_FROM_CART_CONTROLLER = "RemoveProductFromCartServlet";
////    private final String CHECK_OUT_PRODUCT_CONTROLLER = "CheckOutProductServlet";
////    private final String PAYMENT_PRODUCT_CONTROLLER = "PaymentProductServlet";
//
//    /**
//     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
//     * methods.
//     *
//     * @param request servlet request
//     * @param response servlet response
//     * @throws ServletException if a servlet-specific error occurs
//     * @throws IOException if an I/O error occurs
//     */
//    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        response.setContentType("text/html;charset=UTF-8");
//        ServletContext context = this.getServletContext();
//        Properties siteMaps = (Properties) context.getAttribute("SITE_MAPS");
//        String url = siteMaps.getProperty(
//                MyApplicationConstants.DispatchFeature.LOGIN_PAGE);
////        Which button did user click 
//        String button = request.getParameter("btAction");
//        try {
////            FIRST TIME REQUEST
//            if (button == null) {
//                url = siteMaps.getProperty(
//                        MyApplicationConstants.DispatchFeature.FIRST_LOGIN_CONTROLLER);
////            LOGIN, SEARCH, LOGOUT, DELETE, UPDATE, LOGOUT ACCOUNT
//            } else if (button.equals("Login")) {
//                url = siteMaps.getProperty(
//                        MyApplicationConstants.DispatchFeature.LOGIN_CONTROLLER);
//            } else if (button.equals("Search")) {
//                url = siteMaps.getProperty(
//                        MyApplicationConstants.SearchFeature.SEARCH_LASTNAME_CONTROLLER);
//            } else if (button.equals("Logout")) {
//                url = siteMaps.getProperty((
//                        MyApplicationConstants.DispatchFeature.LOGOUT_CONTROLLER));
//            } else if (button.equals("Delete")) {
//                url = siteMaps.getProperty(
//                        MyApplicationConstants.DeleteFeature.DELETE_ACCOUNT_CONTROLLER);
//            } else if (button.equals("Update")) {
//                url = siteMaps.getProperty(
//                        MyApplicationConstants.UpdateFeature.UPDATE_ACCOUNT_CONTROLLER);
//
////                CREATE ACCOUNT
//            } else if (button.equals("Create New Account")) {
//                url = siteMaps.getProperty(
//                        MyApplicationConstants.DispatchFeature.CREATE_NEWACCOUNT_CONTROLLER);
//
////                ADD, VIEWCART, REMOVE (ITEM)
//            } else if (button.equals("Add Items To Your Cart")) {
//                url = siteMaps.getProperty(
//                        MyApplicationConstants.CartItemsFeature.ADD_ITEMS_TO_CART_CONTROLLER);
//            } else if (button.equals("View Your Cart")) {
//                url = siteMaps.getProperty(
//                        MyApplicationConstants.CartItemsFeature.VIEW_CART_PAGE);
//            } else if (button.equals("Remove Selected Item")) {
//                url = siteMaps.getProperty(
//                        MyApplicationConstants.CartItemsFeature.REMOVE_ITEM_FROM_CART_CONTROLLER);
//
////              BUY, VIEW, CHECKOUT, PAY (PRODUCT)  
//            } else if (button.equals("Click here to coppup something!")) {
//                url = siteMaps.getProperty(
//                        MyApplicationConstants.CartProductFeature.SHOPPING_PRODUCT_PAGE_CONTROLLER);
//            } else if (button.equals("Buy")) {
//                url = siteMaps.getProperty(
//                        MyApplicationConstants.CartProductFeature.BUY_PRODUCT_SHOPPING_CONTROLLER);
//            } else if (button.equals("View your product cart")) {
//                url = siteMaps.getProperty(
//                        MyApplicationConstants.CartProductFeature.VIEW_PRODUCT_ORDER_CART_CONTROLLER);
//            } else if (button.equals("Remove Selected Product")) {
//                url = siteMaps.getProperty(
//                        MyApplicationConstants.CartProductFeature.REMOVE_PRODUCT_FROM_CART_CONTROLLER);
//            } else if (button.equals("Checkout!")) {
//                url = siteMaps.getProperty(
//                        MyApplicationConstants.CartProductFeature.CHECK_OUT_PRODUCT_CONTROLLER);
//            } else if (button.equals("Pay")) {
//                url = siteMaps.getProperty(
//                        MyApplicationConstants.CartProductFeature.PAYMENT_PRODUCT_CONTROLLER);
//            }
//        } finally {
//            RequestDispatcher rd = request.getRequestDispatcher(url);
//            rd.forward(request, response);
//        }
//    }
//
//    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
//    /**
//     * Handles the HTTP <code>GET</code> method.
//     *
//     * @param request servlet request
//     * @param response servlet response
//     * @throws ServletException if a servlet-specific error occurs
//     * @throws IOException if an I/O error occurs
//     */
//    @Override
//    protected void doGet(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        processRequest(request, response);
//    }
//
//    /**
//     * Handles the HTTP <code>POST</code> method.
//     *
//     * @param request servlet request
//     * @param response servlet response
//     * @throws ServletException if a servlet-specific error occurs
//     * @throws IOException if an I/O error occurs
//     */
//    @Override
//    protected void doPost(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        processRequest(request, response);
//    }
//
//    /**
//     * Returns a short description of the servlet.
//     *
//     * @return a String containing servlet description
//     */
//    @Override
//    public String getServletInfo() {
//        return "Short description";
//    }// </editor-fold>
//
//}
