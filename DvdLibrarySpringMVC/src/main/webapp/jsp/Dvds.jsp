<%--
  Created by IntelliJ IDEA.
  User: n0280208
  Date: 3/16/2018
  Time: 10:16 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<html>
<head>
    <title>Company Contacts</title>
    <!-- Bootstrap core CSS -->
    <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div id="createContactDiv">
    <h2>Create Dvd</h2>
    <hr>
    <div class="row">
        <div class="col-lg-6">
            <!--Form-->
            <form method="POST" action="createDvd" class="form-horizontal" role="form" id="add-form" modelAttribute="Dvd">
                <div class="form-group">
                    <!--For dvd title-->
                    <label for="dvdTitle" class="col-md-4 control-label">
                        Dvd Title:
                    </label>
                    <div class="col-md-8">
                        <input type="text"
                               class="form-control"
                               name="title"
                               id="dvdTitle"
                               placeholder="Enter Title"/>
                    </div>
                </div>
                <div class="form-group">
                    <!--For release year-->
                    <label for="releaseYear" class="col-md-4 control-label">
                        Release Year:
                    </label>
                    <div class="col-md-8">
                        <input type="text"
                               class="form-control"
                               name="releaseLocalDate"
                               id="releaseYear"
                               placeholder="Enter Release Year"/>
                    </div>
                </div>
                <div class="form-group">
                    <!--For director-->
                    <label for="Director" class="col-md-4 control-label">
                        Director:
                    </label>
                    <div class="col-md-8">
                        <input type="text"
                               class="form-control"
                               name="directorName"
                               id="Director"
                               placeholder="Enter Director"/>
                    </div>
                </div>
                <div class="form-group">
                    <!--For rating-->
                    <label for="rating" class="col-md-4 control-label">
                        Rating:
                    </label>
                    <div class="col-md-8">
                        <select name="ratingMppa" id="rating" class="form-control">
                            <option value="pg">PG-12</option>
                            <option value="pgThirteen">PG-13</option>
                            <option value="r">R</option>
                            <option value="" selected disabled hidden> Choose Rating</option>
                        </select>
                    </div>
                </div>
                <div class="form-group">
                    <!--For notes-->
                    <label for="notes" class="col-md-4 control-label">
                        Notes:
                    </label>
                    <div class="col-md-8">
                                <textarea type="text"
                                          class="form-control"
                                          name="notes"
                                          id="notes"
                                          placeholder="Enter Title">
                                </textarea>
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-md-6 text-right">
                        <a href="${pageContext.request.contextPath}/">
                        <input
                                type="button"
                                role="button"
                                id="createCancelButton"
                                class="btn btn-default"
                                value="cancel"
                        />
                        </a>
                    </div>
                    <div class="col-md-6">
                        <input
                                type="submit"
                                role="button"
                                id="createDvdBtn"
                                class="btn btn-default"
                                value="Create Dvd"
                        />
                    </div>
                </div>
            </form>
        </div>
    </div><!--end of create contact row-->
</div><!--end of create contact div-->
<!-- Placed at the end of the document so the pages load faster -->
<script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
</body>
</html>
