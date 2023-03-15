<%-- 
    Document   : createNewAccount
    Created on : Oct 25, 2022, 9:46:21 AM
    Author     : ASUS
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core"   prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Create New Account</title>
    </head>
    <body>
        <h1>Create New Account</h1>
        <form action="createNewAccountController" method="POST">
<!--USERNAME-->
            <c:set  var="errors"    value="${requestScope.CREATE_ERROR}"/>
            Username* <input type="text" name="txtUsername" 
                             value="${param.txtUsername}" />(6-20 Characters)<br>
            <c:if   test="${not empty errors.usernameLengthError}">
                <font color="red">
                ${errors.usernameLengthError}
                </font><br>
            </c:if>
            <c:if   test="${not empty errors.userNameIsExisted}">
                <font color="red">
                ${errors.userNameIsExisted}
                </font><br>
            </c:if>

<!--PASSWORD-->
            Password* <input type="password" name="txtPassword"
                             value="${param.txtPassword}" />(6-20 Characters)<br>
            <c:if   test="${not empty errors.passwordLengthError}">
                <font color="red">
                ${errors.passwordLengthError}
                </font><br>
            </c:if>

<!--CONFIRM-->
            Confirm* <input type="password" name="txtConfirm"
                            value="${param.txtConfirm}" /><br>
            <c:if   test="${not empty errors.confirmNotMatched}">
                <font color="red">
                ${errors.confirmNotMatched}
                </font><br>
            </c:if>

<!--FULL NAME-->
            Full name* <input type="text" name="txtFullname" 
                              value="${param.txtFullname}" />(2-50 Characters)<br>
            <c:if   test="${not empty errors.fullnameLengthError}">
                <font color="red">
                ${errors.fullnameLengthError}
                </font><br>
            </c:if>
            <input type="submit" value="Create New Account" name="btAction" /><br>
            <input type="reset" value="Reset" /><br>
        </form>
    </body>
</html>
