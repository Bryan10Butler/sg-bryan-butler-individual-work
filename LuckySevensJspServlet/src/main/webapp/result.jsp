<%--
  Created by IntelliJ IDEA.
  User: n0280208
  Date: 3/13/2018
  Time: 7:35 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html>
<head>
    <meta http-equiv="Content-Type"
          content="text/html; charset=UTF-8">
    <title>Result</title>
</head>
<body>
    <p>
        You bet: $${userBet}
    </p>
    <p>
        You are broke after ${rollCount} rolls
    </p>
    <p>
        You should have quit after ${maxRollCount} rolls when you had $${maxMoney}
    </p>
    <p>
        <a href="index.jsp">Roll Again</a>
    </p>

</body>
</html>
