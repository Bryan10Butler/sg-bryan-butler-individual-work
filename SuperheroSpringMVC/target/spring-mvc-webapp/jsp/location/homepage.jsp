<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
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
        <title>Location Page</title>
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
    </head>
<body>
<div class=" col-lg-12 alert-danger text-center">
    <c:if test="${not empty message}">
        <spring:message code="${message}" />
    </c:if>
</div>
<div class="container">
    <h1>Locations</h1>
    <hr/>
    <div class="navbar">
        <ul class="nav nav-tabs">
            <li role="presentation"><a href="${pageContext.request.contextPath}/">Home</a></li>
            <li role="presentation"><a href="${pageContext.request.contextPath}/hero/homepage">Hero</a></li>
            <li role="presentation"><a href="${pageContext.request.contextPath}/power/homepage">Power</a></li>
            <li role="presentation"><a href="${pageContext.request.contextPath}/organization/homepage">Organization</a></li>
            <li role="presentation" class="active"><a href="${pageContext.request.contextPath}/location/homepage">Location</a></li>
            <li role="presentation"><a href="${pageContext.request.contextPath}/sighting/homepage">Sighting</a></li>
        </ul>
    </div>
    <div class="col-md-6">
        <h2>Locations</h2>
        <table id="contactTable" class="table table-hover">
            <tr>
                <th width="40%">Locations</th>
                <th width="30%"></th>
                <th width="30%"></th>
            </tr>

            <!--Table for recent sightings-->

            <c:forEach items="${viewModel.locations}" var="locations">
                <tr>
                    <td><a href="/location/details?id=${locations.id}"><c:out value="${locations.name}"/></a></td>
                    <td><a href="/location/edit?id=${locations.id}">Edit</a></td>
                    <td><a href="/location/delete?id=${locations.id}" onclick="return confirm('Are you sure you want to delete this location?')">Delete</a></td>
                </tr>
            </c:forEach>
        </table>
    </div>

    <div class="col-md-6">
        <h2>Create Location</h2>
        <!--form to create a new location-->
        <sf:form class="form-horizontal" action="/location/create" method="post" modelAttribute="commandModel">
            <div class="form-group">
                <label for="add-name" class="col-md-4 control-label">Name:</label>
                <div class="col-md-8">
                    <sf:input id="add-name" class="form-control" path="name"/> <sf:errors path="name"/>
                </div>
            </div>
            <div class="form-group">
                <label for="add-description" class="col-md-4 control-label">Description:</label>
                <div class="col-md-8">
                    <sf:input id="add-description" class="form-control" path="description"/> <sf:errors path="description"/>
                </div>
            </div>
            <div class="form-group">
                <label for="add-street" class="col-md-4 control-label">Street:</label>
                <div class="col-md-8">
                    <sf:input id="add-street" class="form-control" path="street"/> <sf:errors path="street"/>
                </div>
            </div>
            <div class="form-group">
                <label for="add-city" class="col-md-4 control-label">City:</label>
                <div class="col-md-8">
                    <sf:input id="add-city" class="form-control" path="city"/> <sf:errors path="city"/>
                </div>
            </div>
            <div class="form-group">
                <label for="add-state" class="col-md-4 control-label">State:</label>
                <div class="col-md-8">
                    <sf:input id="add-state" class="form-control" path="state"/> <sf:errors path="state"/>
                </div>
            </div>
            <div class="form-group">
                <label for="add-zip" class="col-md-4 control-label">Zip code:</label>
                <div class="col-md-8">
                    <sf:input id="add-zip" class="form-control" path="zip"/> <sf:errors path="zip"/>
                </div>
            </div>
            <div class="form-group">
                <label for="add-latitude" class="col-md-4 control-label">Latitude:</label>
                <div class="col-md-8">
                    <sf:input id="add-latitude" class="form-control" path="latitude"/> <sf:errors path="latitude"/>
                </div>
            </div>
            <div class="form-group">
                <label for="add-longitude" class="col-md-4 control-label">Longitude:</label>
                <div class="col-md-8">
                    <sf:input id="add-longitude" class="form-control" path="longitude"/> <sf:errors path="longitude"/>
                </div>
            </div>
            <div class="form-group">
                <div class="col-md-offset-4 col-md-8">
                    <button class = "btn btn-default" type="submit">Create Location</button>
                </div>
            </div>
        </sf:form>
        <br/>
    </div>
</div>
</body>
</html>
