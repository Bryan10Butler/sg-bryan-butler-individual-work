<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf" %>
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
</head>
<body>
<div class="container">
    <h1>Edit Location</h1>
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
    <div class="col-md-6">
        <h2>Edit Location</h2>
        <sf:form class="form-horizontal" action="/location/edit" method="post" modelAttribute="commandModel">
            <sf:hidden path="locationId"/>
            <div class="form-group">
                <label for="edit-name" class="col-md-4 control-label">Name:</label>
                <div class="col-md-8">
                    <sf:input id="edit-name" class="form-control" path="name"/> <sf:errors path="name"/>
                </div>
            </div>
            <div class="form-group">
                <label for="edit-description" class="col-md-4 control-label">Description:</label>
                <div class="col-md-8">
                    <sf:input id="edit-description" class="form-control" path="description"/> <sf:errors
                        path="description"/>
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
            <div class="form-group col-md-offset-4 col-md-8 text-right">
                <button class="btn btn-default" type="submit">Submit</button>
                <a href="/location/homepage">
                    <button class="btn btn-default">Cancel</button>
                </a>
            </div>
        </sf:form>
    </div>
    <div class="col-md-6"></div>

</div>
</body>
</html>
