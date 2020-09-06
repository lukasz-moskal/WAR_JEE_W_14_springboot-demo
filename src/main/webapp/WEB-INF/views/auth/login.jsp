<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Login page</title>
</head>
<body>

<%--
<form method="post">
    <div><label> User Name : <input type="text" name="username"/> </label></div>
    <div><label> Password: <input type="password" name="password"/> </label></div>
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
    <div><input type="submit" value="Sign In"/></div>
</form>
--%>
<%--<%@ include file="../common/cap.jsp"%>--%>
<jsp:include page="../common/cap.jsp"/>

<c:if test="${empty param.error and param.error != null}">
    <p>Błędne dane logowania</p>
</c:if>


<form:form method="post">
    <div><label> User Name : <input type="text" name="username"/> </label></div>
    <div><label> Password: <input type="password" name="password"/> </label></div>
    <div><input type="submit" value="Sign In"/></div>
    <c:out value="${param.param}"/>
</form:form>

</body>
</html>