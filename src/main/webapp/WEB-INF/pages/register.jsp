<%--
  Created by IntelliJ IDEA.
  User: xya
  Date: 2/26/14
  Time: 4:42 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title></title>
</head>
<body>
    <form method="POST" action="registerCheck">
        Username:<input name="username" type="text"><br>
        Password:<input name="password1" type="password"><br>
        Password Again:<input name="password2" type="password"><br>
        <input type="submit" value="Submit">
    </form>
    <c:if test="${!empty error}">
        <font color="red"><c:out value="${error}"/></font>
    </c:if>
</body>
</html>
