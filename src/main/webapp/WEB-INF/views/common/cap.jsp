<%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<sec:authorize access="isAuthenticated()">
Jesteś zalogowany jako: <sec:authentication property="principal.username"/>
Masz role: <sec:authentication property="principal.authorities"/>
</sec:authorize>

<sec:authorize access="hasRole('ADMIN')">
    MAM ROLE ADMIN
</sec:authorize>

<sec:authorize url="/book/add">
    <a href="<c:url value = "/book/add"/>">Dodaj książkę</a>
</sec:authorize>

<sec:authorize access="isAuthenticated()">
    <form action="/logout" method="post">
        <input class="fa fa-id-badge" type="submit" value="Wyloguj">
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
    </form>
</sec:authorize>