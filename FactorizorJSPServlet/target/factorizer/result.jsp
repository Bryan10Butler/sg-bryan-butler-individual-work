<%--
  Created by IntelliJ IDEA.
  User: n0280208
  Date: 3/12/2018
  Time: 5:12 PM
  To change this template use File | Settings | File Templates.
--%>
<!--directive imports jstl core library for us to use-->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
    <!--JSP EL opportunity to get value of attributes by key-->
    You asked to factor ${numberToFactor}
</p>
<p>
    <!--iterate through all factors using JSTL-->
    <!--items represents the array of items you wish to iterate through
    <!--factors is in EL language-->
    Factors are:
    <c:forEach var="currentFactor" items="${factors}">
        <c:out value="${currentFactor} "/>
    </c:forEach>
</p>
<p>
    <!--describes whether or not our factor is perfect-->
    <!--similar to switch statement-->
    <!--put boolean expression in test-->
    <c:choose>
        <c:when test="${isPerfect}">
            <!--when perfect-->
            <c:out value="The number is perfect."/>
        </c:when>
        <c:otherwise>
            <c:out value="The number is not perfect."/>
        </c:otherwise>
    </c:choose>
</p>
<p>
    <!--prime or not prime-->
    <c:choose>
        <c:when test="${isPrime}">

            <c:out value="The number is prime."/>
        </c:when>
        <c:otherwise>
            <c:out value="The number is not prime."/>
        </c:otherwise>
    </c:choose>
</p>
<p>
    <!--link to index.jsp as this is where we want to go-->
    <a href="index.jsp">Factor Another One!</a>
</p>
</body>
</html>
