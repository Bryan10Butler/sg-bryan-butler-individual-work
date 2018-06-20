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
    <head>
        <title>Hero Page</title>
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
    </head>
<body>
<div class="container">
    <h1>Heroes</h1>
    <hr/>
    <div class="navbar">
        <ul class="nav nav-tabs">
            <li role="presentation"><a href="${pageContext.request.contextPath}/">Home</a></li>
            <li role="presentation" class="active"><a href="${pageContext.request.contextPath}/hero/homepage">Hero</a></li>
            <li role="presentation"><a href="${pageContext.request.contextPath}/power/homepage">Power</a></li>
            <li role="presentation"><a href="${pageContext.request.contextPath}/organization/homepage">Organization</a></li>
            <li role="presentation"><a href="${pageContext.request.contextPath}/location/homepage">Location</a></li>
            <li role="presentation"><a href="${pageContext.request.contextPath}/sighting/homepage">Sighting</a></li>
        </ul>
    </div>
    <div class="col-md-6">
        <h2>Heros</h2>
        <table id="contactTable" class="table table-hover">
            <tr>
                <th width="40%">Heroes</th>
                <th width="30%"></th>
                <th width="30%"></th>
            </tr>

            <!--Table for recent sightings-->

            <c:forEach items="${viewModel.heroes}" var="heroes">
                <tr>
                    <td><a href="/hero/details?id=${heroes.id}"><c:out value="${heroes.name}"/></a></td>
                    <td><a href="/hero/edit?id=${heroes.id}">Edit</a></td>
                    <td><a href="/hero/delete?id=${heroes.id}"onclick="return confirm('Are you sure you want to delete this hero?')">Delete</a></td>
                </tr>
            </c:forEach>
        </table>
    </div>

    <div class="col-md-6">
        <h2>Create Hero</h2>
        <!--form to create a new hero-->
        <sf:form class="form-horizontal" action="/hero/create" method="post" modelAttribute="commandModel">
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
            </div>
            <div class="form-group">
                <label for="add-power" class="col-md-4 control-label">Power:</label>
                <div class="col-md-8">
                    <sf:select id="add-power" class="form-control" path="powerIds">
                            <sf:options items="${viewModel.powers}" itemValue="id" itemLabel="name"/>
                    </sf:select> <sf:errors path="powerIds"/>
                </div>
            </div>
            <div class="form-group">
                <label for="add-organization" class="col-md-4 control-label">Organization:</label>
                <div class="col-md-8">
                    <sf:select id="add-organization" class="form-control" path="organizationIds">
                        <sf:options items="${viewModel.organizations}" itemValue="id" itemLabel="name"/>
                    </sf:select> <sf:errors path="organizationIds"/>
                </div>
            </div><br />
            <div class="form-group">
                <div class="col-md-offset-4 col-md-8">
                    <button class = "btn btn-default" type="submit">Create Hero</button>
                </div>
            </div>
        </sf:form>
        <br/>
    </div>
</div>
</body>
</html>
