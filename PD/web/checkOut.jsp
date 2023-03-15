<%-- 
    Document   : checkOut
    Created on : Oct 28, 2022, 10:06:47 PM
    Author     : ASUS
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core"   prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Check Out Page</title>
    </head>
    <body>
        <h1>Check Out Page</h1>
        <form action="PaymentProductServlet">
            <c:if   test="${not empty sessionScope}">
                <c:set  var="productList"   value="${sessionScope.CHECKOUT_RESULT}"/>
                <!--Lấy ra attribute của List<ProductDTO> thông qua [name = CHECKOUT_RESULT] trong checkOutProductServlet-->
                <c:if   test="${not empty productList}">
                    <!--Kiểm tra xem list các DTO có null không thì mới cho làm bảng, Vì ở đây dùng đến 2 vòng lặp forEach nên sẽ ảnh hưởng tableHead-->
                    <table border="1">
                        <thead>
                            <tr>
                                <th>No.</th>
                                <th>Name</th>
                                <th>Quantity</th>
                                <th>Price</th>
                                <th>Total</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:set  var="tmp"   value="${0}"/>
                            <!--Tạo ra biến tmp gán giá trị = 0 để chạy forEach và lấy ra tổng price của tất cả sp-->
                            <c:forEach  var="productListItem"  items="${productList}"    varStatus="counter">
                                <!--Lấy ra trong mỗi Product ở trong List<ProductDTO>--> 
                                <c:set  var="products"  value="${sessionScope.PRODUCTS.items}"/>
                                <!--Đặt tên = products , value = items trong cartObject đã ép kiểu bên checkOutProductServlet với tên  "PRODUCTS"
                                => Lấy ra Attribute-->
                                <c:if   test="${not empty products}">
                                    <!--Kiểm tra xem trong cart có hay không-->
                                    <c:forEach  var="productCartItem"  items="${products}">
                                        <c:if   test="${productListItem.sku eq productCartItem.key}">
                                            <!--So sánh các product ở trong List<ProductDTO> và ở trong PRODUCTS(Tức là CART) có trùng hay ko mới cho thực hiện duyệt-->
                                            <tr>
                                                <td>${counter.count}</td>
                                                <td>${productListItem.name}</td> 
                                                <td>${productCartItem.value}</td>
                                                <td>${productListItem.price}</td>
                                                <td>${productCartItem.value * productListItem.price}</td>
                                                <c:set  var="tmp"  value="${productCartItem.value * productListItem.price + tmp}"/>
                                                <!--Lấy ra tổng của quantity * price và + với biến tmp => Gán tmp = tổng tất cả-->  
                                            </tr>
                                        </c:if>
                                    </c:forEach>
                                </c:if>
                            </c:forEach>
                            <tr>
                                <td  colspan="3">
                                    Sum All
                                </td>
                                <td  colspan="2" style="text-align: center">
                                    <font color ="red">
                                    ${tmp}
                                    <input type="hidden" name="txtTotalBill" value="${tmp}" />
                                    </font>
                                </td>
                            </tr>
                        </tbody>
                    </table> 
                    <input type="submit" value="Pay" name="btAction" />
                </c:if>
            </c:if>
        </form>
        <c:if   test="${empty sessionScope}">
            <h2>No CheckOut is existed!!!</h2>
        </c:if>
    </body>
</html>
