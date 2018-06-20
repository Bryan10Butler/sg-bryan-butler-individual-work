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
    <h1>Location Details</h1>
    <hr/>
    <div class="navbar">
        <ul class="nav nav-tabs">
            <li role="presentation"><a href="${pageContext.request.contextPath}/">Home</a></li>
            <li role="presentation"><a href="${pageContext.request.contextPath}/hero/homepage">Location</a></li>
            <li role="presentation"><a href="${pageContext.request.contextPath}/power/homepage">Power</a></li>
            <li role="presentation"><a href="${pageContext.request.contextPath}/organization/homepage">Organization</a></li>
            <li role="presentation" class="active"><a href="${pageContext.request.contextPath}/location/homepage">Location</a></li>
            <li role="presentation"><a href="${pageContext.request.contextPath}/sighting/homepage">Sighting</a></li>
        </ul>
    </div>
    <h2>${viewModel.name} Details</h2> <br />
    <h4>Name:</h4> ${viewModel.name} <br />
    <h4>Description:</h4> ${viewModel.description} <br />
    <h4>Street:</h4> ${viewModel.street}<br />
    <h4>City:</h4> ${viewModel.city}<br />
    <h4>State:</h4> ${viewModel.state}<br />
    <h4>Zip Code:</h4> ${viewModel.zip}<br />
    <h4>Latitude:</h4> ${viewModel.latitude}<br />
    <h4>Longitude:</h4> ${viewModel.longitude}<br /><br />

    <a href="/location/homepage"><button class="btn btn-default">Return</button></a><br /><br />

</div>

</body>
</html>
