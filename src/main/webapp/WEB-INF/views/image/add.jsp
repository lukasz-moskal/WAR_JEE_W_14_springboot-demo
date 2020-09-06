<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Add image</title>
</head>
<body>
<jsp:include page="../common/cap.jsp"/>
<h1>Add image</h1>
<form:form method="post" enctype="multipart/form-data">
  Title: <input type="text" name="title"/>
  Author: <input type="text" name="author"/>
  Image: <input type="file" name="image" multiple/>
  <input type="submit" value="Add image">
</form:form>
</body>
</html>