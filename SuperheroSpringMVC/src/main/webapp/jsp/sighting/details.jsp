<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: n0280208
  Date: 4/16/2018
  Time: 3:48 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
    <style>
        h4 {
            color : red;
        }
    </style>
</head>
<body>
<div class="container">
    <h1>Sighting Details</h1>
    <hr/>
    <div class="navbar">
        <ul class="nav nav-tabs">
            <li role="presentation"><a href="${pageContext.request.contextPath}/">Home</a></li>
            <li role="presentation"><a href="${pageContext.request.contextPath}/hero/homepage">Hero</a></li>
            <li role="presentation"><a href="${pageContext.request.contextPath}/power/homepage">Power</a></li>
            <li role="presentation"><a href="${pageContext.request.contextPath}/organization/homepage">Organization</a></li>
            <li role="presentation"><a href="${pageContext.request.contextPath}/location/homepage">Location</a></li>
            <li role="presentation" class="active"><a href="${pageContext.request.contextPath}/sighting/homepage">Sighting</a></li>
        </ul>
    </div>
    <h2>${viewModel.locationName} Details</h2> <br />
    <h4>Name:</h4> ${viewModel.locationName} <br />
    <h4>Date:</h4> ${viewModel.date} <br />
    <h4>Description:</h4> ${viewModel.description} <br />

    <h4>Heroes Sighted:</h4>
    <c:forEach items="${viewModel.heroes}" var="heroes">
        ${heroes.name}
    </c:forEach><br /><br />

    <a href="/sighting/homepage"><button class="btn btn-default">Return</button></a>

</div>


</body>
</html>
