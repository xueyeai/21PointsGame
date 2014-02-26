<%--
  Created by IntelliJ IDEA.
  User: xya
  Date: 2/25/14
  Time: 6:38 AM
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Welcome!</title>
</head>
<body>
    <c:if test="${!empty error}">
        <font color="red"><c:out value="${error}"/></font>
    </c:if>
    <form name="login" method="post" action="check">
        Username: <input name="username" type="text"><br>
        Password: <input name="password" type="password"><br>
        <input type="submit" value="Submit">
    </form>
</body>
</html>
