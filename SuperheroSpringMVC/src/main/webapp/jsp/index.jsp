<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Index Page</title>
    <!-- Bootstrap core CSS -->
    <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
    <style>

        body {
            background: white url("/css/Image.jpg") no-repeat fixed center;
            opacity: .70;
            font-family: "Comic Sans MS";
        }

        th, .container{
            background-color: lightgray;
            border-radius: 15px
        }

        h1, h2 {
            text-align: center;
            color: red;
        }

    </style>
</head>
<body>
<div style="max-width: 1600px; margin: 0 auto; padding: 20px;">
<div class="container">
    <h1>Superhero Sightings Homepage</h1>
    <hr/>
    <div class="navbar">
        <ul class="nav nav-tabs">
            <li role="presentation" class="active"><a href="${pageContext.request.contextPath}/jsp/index.jsp">Home</a></li>
            <li role="presentation"><a href="${pageContext.request.contextPath}/hero/homepage">Hero</a></li>
            <li role="presentation"><a href="${pageContext.request.contextPath}/power/homepage">Power</a></li>
            <li role="presentation"><a href="${pageContext.request.contextPath}/organization/homepage">Organization</a></li>
            <li role="presentation"><a href="${pageContext.request.contextPath}/location/homepage">Location</a></li>
            <li role="presentation"><a href="${pageContext.request.contextPath}/sighting/homepage">Sighting</a></li>
        </ul>
    </div>
    <h2>Recent Sightings</h2>
    <!--Table for recent sightings-->
    <table id="recentSightings" class="table table-hover text-center">
        <tr>
            <th class="text-center" width="25%">Date</th>
            <th class="text-center" width="25%">Location</th>
            <th class="text-center" width="25%">Hero</th>
            <th class="text-center" width="25%">Description</th>
        </tr>
        <tbody id="contentRows"></tbody>
        <c:forEach items="${homePageViewModel.sightings}" var="sighting">
            <tr>
                <td>${sighting.date}</td>
                <td>${sighting.location.name}</td>
                <td><c:forEach items="${sighting.heroes}" var="heroes">
                    ${heroes.name}
                </c:forEach></td>
                <td>${sighting.description}</td>
            </tr>
        </c:forEach>
    </table>
</div>
<!-- Placed at the end of the document so the pages load faster -->
</div>
<script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
</body>
</html>

