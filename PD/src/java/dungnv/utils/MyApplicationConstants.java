/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dungnv.utils;

/**
 *
 * @author ASUS
 */
public class MyApplicationConstants {
    
    public class DispatchFeature{
        
        public static final String LOGIN_PAGE = "loginPage"; 
        public static final String INVALID_PAGE = "invalidPage"; 
        public static final String LOGIN_CONTROLLER = "loginController"; 
        public static final String FIRST_LOGIN_CONTROLLER = ""; 
        public static final String LOGOUT_CONTROLLER = "logoutController";
        public static final String CREATE_NEW_ACCOUNT_PAGE1 = "createNewAccountPage1";
        public static final String CREATE_NEW_ACCOUNT_PAGE2 = "createNewAccountPage2"; 
        public static final String CREATE_NEWACCOUNT_CONTROLLER ="createNewAccountController";
        public static final String ERROR_PAGE = "errorPage"; 
    }
    
    public class SearchFeature{
        
        public static final String SEARCH_PAGE = "searchPage"; 
        public static final String INVALID_PAGE = "invalidPage";
        public static final String SEARCH_LASTNAME_CONTROLLER = "searchLastNameController"; 
    }
    
    public class DeleteFeature{
        
        public static final String ERROR_PAGE = "error.html";
        public static final String DELETE_ACCOUNT_CONTROLLER = "deleteAccountController"; 
    }
    
    public class UpdateFeature{
        
        public static final String SEARCH_PAGE = "searchPage"; 
        public static final String ERROR_PAGE = "error.html";
        public static final String UPDATE_ACCOUNT_CONTROLLER = "updateAccountController"; 
    }
    
    public class CartItemsFeature{
        public static final String ADD_ITEMS_TO_CART_CONTROLLER = "addItemToCartController"; 
        public static final String VIEW_CART_PAGE = "viewCartPage"; 
        public static final String REMOVE_ITEM_FROM_CART_CONTROLLER = "removeItemFromCartController";
        public static final String SHOPPING_PAGE = "shoppingPage";
    }
    
    public class CartProductFeature{
        public static final String SHOPPING_PRODUCT_PAGE = "shoppingProductPage"; 
        public static final String VIEW_PRODUCT_CART_PAGE = "viewProductCartPage";
        public static final String CHECK_OUT_PRODUCT_PAGE = "checkOutProductPage";
        public static final String SHOPPING_PRODUCT_PAGE_CONTROLLER = "shoppingProductPageController";
        public static final String BUY_PRODUCT_SHOPPING_CONTROLLER = "buyProductShoppingController";
        public static final String VIEW_PRODUCT_ORDER_CART_CONTROLLER = "viewProductOrderCartController";
        public static final String REMOVE_PRODUCT_FROM_CART_CONTROLLER = "removeProductFromCartController";
        public static final String CHECK_OUT_PRODUCT_CONTROLLER = "checkOutProductController";
        public static final String PAYMENT_PRODUCT_CONTROLLER = "paymentProductController"; 
    }
}
