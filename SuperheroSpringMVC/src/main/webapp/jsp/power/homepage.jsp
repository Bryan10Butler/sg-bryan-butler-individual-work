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
    <title>Power Page</title>
    <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container">
    <h1>Powers</h1>
    <hr/>
    <div class="navbar">
        <ul class="nav nav-tabs">
            <li role="presentation"><a href="${pageContext.request.contextPath}/">Home</a></li>
            <li role="presentation"><a href="${pageContext.request.contextPath}/hero/homepage">Hero</a></li>
            <li role="presentation" class="active"><a href="${pageContext.request.contextPath}/power/homepage">Power</a>
            </li>
            <li role="presentation"><a href="${pageContext.request.contextPath}/organization/homepage">Organization</a>
            </li>
            <li role="presentation"><a href="${pageContext.request.contextPath}/location/homepage">Location</a></li>
            <li role="presentation"><a href="${pageContext.request.contextPath}/sighting/homepage">Sighting</a></li>
        </ul>
    </div>
    <div class="col-md-6">
        <h2>Powers</h2>
        <table id="contactTable" class="table table-hover">
            <tr>
                <th width="40%">Power</th>
                <th width="30%"></th>
                <th width="30%"></th>
            </tr>

            <!--Table for recent sightings-->

            <c:forEach items="${viewModel.powers}" var="power">
                <tr>
                    <td><a href="/power/details?id=${power.id}"><c:out value="${power.name}"/></a></td>
                    <td><a href="/power/edit?id=${power.id}">Edit</a></td>
                    <td><a href="/power/delete?id=${power.id}"onclick="return confirm('Are you sure you want to delete this hero?')">Delete</a></td>
                </tr>
            </c:forEach>
        </table>
    </div>

    <div class="col-md-6">
        <h2>Create Power</h2>
        <!--form to create a new power-->
        <sf:form class="form-horizontal" action="/power/create" method="post" modelAttribute="commandModel">
            <div class="form-group">
                <label for="add-name" class="col-md-4 control-label">Name:</label>
                <div class="col-md-8">
                    <sf:input id="add-name" class="form-control" path="name"/> <sf:errors path="name"/>
                </div>
            </div><br/>
            <div class="form-group">
                <label for="add-description" class="col-md-4 control-label">Description:</label>
                <div class="col-md-8">
                    <sf:input id="add-description" class="form-control" path="description"/> <sf:errors path="description"/>
                </div>
            </div><br />
            <div class="form-group">
                <div class="col-md-offset-4 col-md-8">
            <button class = "btn btn-default" type="submit">Create Power</button>
                </div>
            </div>
        </sf:form>
        <br/>
    </div>
</div>
</body>
</html>
