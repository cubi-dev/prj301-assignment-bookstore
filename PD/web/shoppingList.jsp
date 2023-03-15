<%-- 
    Document   : shoppingList
    Created on : Oct 26, 2022, 10:02:30 PM
    Author     : ASUS
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core"   prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Shopping Product</title>
    </head>
    <body>
        <h1>Shopping list</h1>
        <c:set  var="result"    value="${requestScope.ITEMS_RESULT}"/>
        <c:if test="${not empty result}">
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
        <form action="viewProductOrderCartController">
            <input type="submit" value="View your product cart" name="btAction" />
        </form> 
    </c:if>
    <c:if test="${empty requestScope.ITEMS_RESULT}">
        <h2>We are updating more items! We'll comeback soon!!</h2>
    </c:if>
        
        
        
        <!--PAY-->
        <c:set var="signal" value="${requestScope.SIGNAL}"/>
    <c:if test="${not empty signal}">
        <h1>Buying Successfully!</h1>
    </c:if>
</body>
</html>
