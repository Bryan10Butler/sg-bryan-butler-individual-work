<%--
  Created by IntelliJ IDEA.
  User: n0280208
  Date: 3/14/2018
  Time: 6:26 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type"
          content="text/html; charset=UTF-8">
    <title>Result</title>
</head>
<body>
    <h1>Result</h1>
    <p>
        This is the total time: ${totalTime}
    </p>
    <P>
        This is the total cost: ${totalCost}
    </P>
    <p>
        <a href="index.jsp">Calculate Again</a>
    </p>

</body>
</html>
