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
    <h1>Edit Power</h1>
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
        <h2>Edit Power</h2>
        <sf:form class="form-horizontal" action="/power/edit" method="post" modelAttribute="commandModel">
            <sf:hidden path="powerId"/>
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
            <div class="form-group col-md-offset-4 col-md-8 text-right">
                <button class="btn btn-default" type="submit">Submit</button>
                <a href="/power/homepage">
                    <button class="btn btn-default">Cancel</button>
                </a>
            </div>
        </sf:form>
    </div>
    <div class="col-md-6"></div>

</div>
</body>
</html>
