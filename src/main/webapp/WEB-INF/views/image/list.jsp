<%@ page contentType="text/html;charset=UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Add image</title>
</head>
<body>
<jsp:include page="../common/cap.jsp"/>
<table>
    <c:forEach items="${images}" var="image">
        <tr>
            <td>${image.id}</td>
            <td>${image.title}</td>
            <td>${image.author}</td>
            <%--<td><img src="data:image/jpeg;base64, ${image.imageAsBase64}"/>></td>--%>
            <td><img src="/image/show?id=${image.id}"/></td>
        </tr>
    </c:forEach>
</table>
</body>