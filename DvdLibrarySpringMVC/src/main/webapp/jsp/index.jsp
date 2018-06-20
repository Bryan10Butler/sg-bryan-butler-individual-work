<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Index Page</title>
        <!-- Bootstrap core CSS -->
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
        <style>
            tbody, td, th {border: 1px solid #ddd;}
            th {background-color: lightgrey}
        </style>
    </head>
    <body>
        <div style="max-width: 1600px; margin: 0 auto; padding: 10px;">
            <div class="container-fluid">
                <!--start header row-->
                <div class="row">
                    <!--create dvd button header-->
                    <div class="col-lg-3">
                        <a href="${pageContext.request.contextPath}/displayDvdPage"
                                role="button"
                                id="createDvdButton"
                                class="btn btn-primary"
                        >Create DVD
                        </a>
                    </div>
                    <!--search button header-->
                    <div class="col-lg-3">
                        <button type="button"
                                id="search"
                                class="btn btn-default form-control"
                        >Search
                        </button>
                    </div>
                    <!--drop down header-->
                    <div class="col-lg-3">
                        <select id="searchCategory " class="form-control" required>
                            <option value="title">Title</option>
                            <option value="releaseYear">Release Year</option>
                            <option value="directorName">Director Name</option>
                            <option value="Rating">Rating</option>
                            <option value="Title">Title</option>
                            <option value="" selected disabled hidden> Search Category</option>
                        </select>
                    </div>
                    <!--search-->
                    <div class="col-lg-3">
                        <input type="text"
                               id="searchTerm"
                               class="form-control"
                               placeholder="Search Term" required
                        />
                    </div>
                    <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12"><hr></div>
                </div><!--end of header row-->
                <!--error messages-->
                <div><ul class="list-group" id="errorMessages"></ul></div>
                <!--start of new dvd display row-->
                <div class="row">
                    <div class="col-lg-12">
                        <!--start of dvd display table-->
                        <table id="dvdTable" class="table table-hover text-center">
                            <tr>
                                <th class="text-center" width="20%">Title</th>
                                <th class="text-center" width="20%">Release Date</th>
                                <th class="text-center" width="20%">Director</th>
                                <th class="text-center" width="20%">Rating</th>
                                <th class="text-center" width="20%"></th>
                            </tr>
                            <tbody id="contentRows"></tbody>
                            <c:forEach var="currentDvd" items="${dvd}">
                                <tr>
                                    <td><c:out value="${currentDvd.title}"/></td>
                                    <td><c:out value="${currentDvd.releaseLocalDate}"/></td>
                                    <td><c:out value="${currentDvd.directorName}"/></td>
                                    <td><c:out value="${currentDvd.ratingMppa}"/></td>
                                    <td><a href="displayEditDvdPage?dvdId=${currentDvd.dvdId}">Edit |</a><a href="deleteDvd?dvdId=${currentDvd.dvdId}"> Delete</a></td>
                                </tr>
                            </c:forEach>
                        </table>
                    </div><!--end of content dvd display spacing-->
                </div>
            </div>
        </div>
        <!-- Placed at the end of the document so the pages load faster -->
        <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
    </body>
</html>

