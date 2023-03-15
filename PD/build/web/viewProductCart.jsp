<%-- 
    Document   : viewProductCart
    Created on : Oct 27, 2022, 10:34:04 PM
    Author     : ASUS
--%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Product Cart</title>
    </head>
    <body>
        <!--SHOW PRODUCT"S TO ORDER-->
        <c:set  var="result"    value="${requestScope.ITEMS_RESULT}"/>
        <c:if test="${not empty result}">
            <h1>Here are some items for chosen:</h1><br/>
            <table border="1">
                <thead>
                    <tr>
                        <th>No.</th>
                        <th>Product ID</th>
                        <th>Product Name</th>
                        <th>Product Description</th>
                        <th>Product Quantity</th>
                        <th>Product Price</th>
                        <th>Action</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach  var="products"  items="${result}"    varStatus="counter">
                    <form action="buyProductShoppingController">
                        <tr>
                            <td>${counter.count}</td>
                            <td>${products.sku}
                                <input type="hidden" name="txtID" value="${products.sku}" />    
                            </td>
                            <td>${products.name}</td>
                            <td>${products.description}</td>
                            <td>${products.quantity}
                                <input type="hidden" name="txtQuantity" value="${products.quantity}" />    
                            </td>
                            <td>${products.price}</td>
                            <td>
                                <input type="submit" value="Buy" name="btAction" />
                            </td>
                        </tr>
                    </form>
                </c:forEach>
            </tbody>
        </table>
        <!--        <form action="DispatchController">
                    <input type="submit" value="View your Order" name="btAction" />
                </form> -->
    </c:if>
    <c:if test="${empty requestScope.ITEMS_RESULT}">
        <h2>We are updating more items! We'll comeback soon!!</h2>
    </c:if>






    <!--VIEW PRODUCT's ORDER--> 
    <h1>Your Product's Cart Include</h1>
    <!--1. Customer goes to his/her cart place--> 
    <c:if    test="${not empty sessionScope}">
        <!--2.Customer takes his/her cart-->
        <c:set  var="cart"  value="${sessionScope.PRODUCTS}"/> 
        <c:if   test="${not empty cart}">
            <!--3.Customer takes items from cart-->
            <c:set  var="items" value="${cart.items}"/>
            <!--4.show items-->
            <c:if   test="${not empty items}">
                <form action="removeProductFromCartController">
                    <table border="1">
                        <thead>
                            <tr>
                                <th>No.</th>
                                <th>Order ID</th>
                                <th>Quantity</th>
                                <th>Action</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:set var="cartList" value="${sessionScope.PRODUCTS.items}"/>
                            <c:forEach  var="items" items="${cartList}" varStatus="counter">
                                <tr>
                                    <td>
                                        ${counter.count}
                                        .</td>
                                    <td>
                                        ${items.key}
                                        <input type="hidden" name="txtId" value="${item.key}" />
                                    </td>
                                    <td>
                                        ${items.value}
                                        <input type="hidden" name="txtQuantity" value="${items.value}" />
                                    </td>
                                    <td>
                                        <input type="checkbox" name="ckProducts" 
                                               value="${items.key}" />
                                    </td>
                                </tr>
                            </c:forEach>
                            <tr>
                                <td colspan="3">
                                    <a href="ShoppingPageServlet">Click here to buy more item</a>
                                </td>
                            </tr>
                        </tbody>
                    </table>
                    <input type="submit" value="Remove Selected Product" name="btAction" />
                </form>
                <form action="checkOutProductController">
                    <input type="submit" value="Checkout!" name="btAction" />
                </form>
            </c:if>
        </c:if>
    </c:if>
    <c:if   test="${empty sessionScope.PRODUCTS.items}">
        <h2>Product's Cart is empty!!! You should buy something first!</h2>
    </c:if>
</body>
</html>
