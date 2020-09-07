<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Send email</title>
</head>
<body>
<jsp:include page="../common/cap.jsp"/>
<h1>Send email</h1>
<form:form method="post">
  To: <input type="text" name="to"/>
  Subject: <input type="text" name="subject"/>
  Text: <input type="text" name="text" />
  <input type="submit" value="Send email">
</form:form>
</body>
</html>