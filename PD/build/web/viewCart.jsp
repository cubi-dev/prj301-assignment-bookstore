<%-- 
    Document   : viewCart
    Created on : Oct 25, 2022, 11:35:19 PM
    Author     : ASUS
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core"   prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Web Mart</title>
    </head>
    <body>
        <h1>Your Cart Include</h1>
        <!--1. Customer goes to his/her cart place--> 
        <c:if    test="${not empty sessionScope}">
            <!--2.Customer takes his/her cart-->
            <c:set  var="cart"  value="${sessionScope.CART}"/> 
            <c:if   test="${not empty cart}">
                <!--3.Customer takes items from cart-->
                <c:set  var="items" value="${cart.items}"/>
                <!--4.show items-->
                <c:if   test="${not empty items}">
                    <form action="removeItemFromCartController">
                        <table border="1">
                            <thead>
                                <tr>
                                    <th>No.</th>
                                    <th>Name</th>
                                    <th>Quantity</th>
                                    <th>Action</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:set var="cartList" value="${sessionScope.CART.items}"/>
                                <c:forEach  var="items" items="${cartList}" varStatus="counter">
                                    <tr>
                                        <td>
                                            ${counter.count}
                                            .</td>
                                        <td>
                                            ${items.key}
                                        </td>
                                        <td>
                                            ${items.value}
                                        </td>
                                        <td>
                                            <input type="checkbox" name="ckItem" 
                                                   value="${items.key}" />
                                        </td>
                                    </tr>
                                </c:forEach>
                                <tr>
                                    <td colspan="3">
                                        <a href="shoppingPage">Click here to buy more item</a>
                                    </td>
                                    <td>
                                        <input type="submit" value="Remove Selected Item" name="btAction" />
                                    </td>
                                </tr>
                            </tbody>
                        </table>
                    </form>
                </c:if>
            </c:if>
        </c:if>
        <c:if   test="${empty sessionScope.CART.items}">
            <h2>No Cart is existed!!!</h2>
            <a href="shoppingPage">Click here to buy more item</a>
        </c:if>
    </body>
</html>
