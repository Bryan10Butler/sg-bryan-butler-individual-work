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
<head>
    <title>Title</title>
    <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container">
    <h1>Edit Sighting</h1>
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
    <div class="col-md-6">
        <h2>Edit Sighting</h2>
        <sf:form class="form-horizontal" action="/sighting/edit" method="post" modelAttribute="commandModel">
            <sf:hidden path="sightingId"/>
            <div class="form-group">
                <label for="add-name" class="col-md-4 control-label">Hero:</label>
                <div class="col-md-8">
                    <sf:select id="add-name" class="form-control" path="heroIds">
                        <sf:options items="${viewModel.heroes}" itemValue="id" itemLabel="name"/>
                    </sf:select> <sf:errors path="heroIds"/>
                </div>
            </div>
            <div class="form-group">
                <label for="add-date" class="col-md-4 control-label">Date:</label>
                <div class="col-md-8">
                    <sf:input id="add-date" class="form-control" path="date"/> <sf:errors path="date"/>
                </div>
            </div>
            <div class="form-group">
                <label for="add-location" class="col-md-4 control-label">Location:</label>
                <div class="col-md-8">
                    <sf:select id="add-location" class="form-control" path="locationId">
                        <sf:options items="${viewModel.locations}" itemValue="id" itemLabel="name"/>
                    </sf:select> <sf:errors path="locationId"/>
                </div>
            </div>
            <div class="form-group">
                <label for="add-description" class="col-md-4 control-label">Description:</label>
                <div class="col-md-8">
                    <sf:input id="add-description" class="form-control" path="description"/> <sf:errors path="description"/>
                </div>
            </div>
            <div class="form-group col-md-offset-4 col-md-8 text-right">
                <button class="btn btn-default" type="submit">Submit</button>
                <a href="/sighting/homepage">
                    <button class="btn btn-default">Cancel</button>
                </a>
            </div>
        </sf:form>
    </div>
    <div class="col-md-6"></div>

</div>
</body>
</html>
