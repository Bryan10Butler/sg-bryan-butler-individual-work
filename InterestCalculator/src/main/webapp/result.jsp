<%--
  Created by IntelliJ IDEA.
  User: n0280208
  Date: 3/13/2018
  Time: 2:27 PM
  To change this template use File | Settings | File Templates.
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<c:forEach items="${yearList}" var="year">

    <div>
        Year: ${year.year} <br />
        Starting Balance: ${year.startingBalance} <br />
        Ending Balance: ${year.endingBalance} <br />
        Interest Earned: ${year.interestEarned}
    </div>

</c:forEach>


</body>
</html>
