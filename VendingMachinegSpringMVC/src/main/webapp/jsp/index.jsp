<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
    <title>Index Page</title>
    <!-- Bootstrap core CSS -->
    <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
    <style>
        hr {
            background-color: blue;
            height: 1px;
        }

        input, textarea {
            border-radius: 5px;
            background-color: #f1f1f1;
            border: none;
        }
    </style>
</head>
<body>
    <!--page setup-->
    <div style="max-width: 1600px; margin: 0 auto; padding: 10px;">
        <!--start of main container-->
        <div class="container-fluid">
            <div id="header" class="text-center">
                <h2>Vending Machine</h2>
                <hr>
            </div>
            <!--start of main row-->
            <div class="row">
                <!--start of content div-->
                <div id="content">
                    <!----------start of items--------->
                    <div class="col-sm-9">
                        <div id="items">
                            <!--print out each item detail as currentItem.something-->
                            <c:forEach var="currentItem" items="${itemList}">
                                <!--store itemId as reference selectedItemId-->
                                <a href="${pageContext.request.contextPath}/item?selectedItemId=${currentItem.itemId}">
                                    <!--build div-->
                                    <div id="${currentItem.itemId}" class="well col-md-3 col-sm-12"
                                         style="margin: 20px 25px">
                                        <!--print contents within div-->
                                        <c:out value="${currentItem.itemId}"/>
                                        <p align="center">
                                            <c:out value="${currentItem.name}"/><br>
                                            <c:out value="$${currentItem.price}"/><br>
                                            <c:out value="quantity: ${currentItem.quantity}"/>
                                        </p>
                                    </div>
                                </a>
                            </c:forEach>
                        </div>
                    </div>
                    <!--start of form-->
                    <div class="col-sm-3">
                        <div id="forms" align="center">
                            <h4>Total $ In</h4>
                            <!----------start of transactions form---------->
                            <form id-="transactionsForm">
                                <div class="form-group">
                                    <!--money added as a result of clicking money buttons-->
                                    <input value="${moneyToBeAdded}" id="moneyInput" type="text" disabled/>
                                </div>
                                <div id="moneyButtons">
                                    <!--add dollar-->
                                    <!--amount as string dollar-->
                                    <a href="${pageContext.request.contextPath}/moneyButtons/dollar?selectedItemId=${selectedItemId}"
                                       role="button"
                                       class="btn btn-default col-lg-6 col-md-6 col-sm-12 col-xs-12"
                                       id="addDollar"
                                       type="button"
                                    >Add Dollar
                                    </a>
                                    <!--add quarter-->
                                    <!--amount as string quarter-->
                                    <a href="${pageContext.request.contextPath}/moneyButtons/quarter?selectedItemId=${selectedItemId}"
                                       role="button"
                                       class="btn btn-default col-lg-6 col-md-6 col-sm-12 col-xs-12"
                                       id="addQuarter"
                                       type="button"
                                    >Add Quarter
                                    </a>
                                    <!--add dime-->
                                    <!--amount as string dime-->
                                    <a href="${pageContext.request.contextPath}/moneyButtons/dime?selectedItemId=${selectedItemId}"
                                       role="button"
                                       class="btn btn-default col-lg-6 col-md-6 col-sm-12 col-xs-12"
                                       id="addDime"
                                       type="button"
                                    >AddDime
                                    </a>
                                    <!--add nickel-->
                                    <!--amount as string nickel-->
                                    <a href="${pageContext.request.contextPath}/moneyButtons/nickel?selectedItemId=${selectedItemId}"
                                       role="button"
                                       class="btn btn-default col-lg-6 col-md-6 col-sm-12 col-xs-12"
                                       id="addNickel"
                                       type="button"
                                    >AddNickel
                                    </a>
                                </div><!--closing money buttons div-->
                                <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                                    <hr>
                                </div>
                                <!--messages and make purchase-->
                                <h4>Messages</h4>
                                <!--user messages-->
                                <div class="form-group">

                                    <!--display error and success message-->
                                    <input id="userMessage"
                                           type="text"

                                    />
                                    <c:if test="${not empty message}">
                                        <spring:message code="${message}"/>
                                    </c:if>

                                </div>
                                <!--item number display-->
                                <div class="form-group">
                                    <label for="itemNumber">ITEM:</label>
                                    <!--display item number-->
                                    <input value="${selectedItemId}" id="itemNumber" type="text" disabled/>
                                </div>
                                <!--make purchase button-->
                                <!--store itemId into reference makePurchase-->
                                <a href="${pageContext.request.contextPath}/makePurchase?selectedItemId=${selectedItemId}"
                                   role="button"
                                   class="btn btn-default"
                                   id="makePurchase"
                                   type="button"
                                >Make Purchase
                                </a>
                                <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                                    <hr>
                                </div>
                                <!--return change-->
                                <h4>Change</h4>
                                <div class="form-group">
                                    <textarea id="changeInput" type="text" disabled>
                                        Quarters: ${change.numQuarters}
                                        Dimes: ${change.numDimes}
                                        Nickels: ${change.numNickels}
                                        Pennies: ${change.numPennies}
                                    </textarea>
                                </div>
                                <!--return change button-->
                                <a href="${pageContext.request.contextPath}/getChange"
                                   role="button"
                                   class="btn btn-default"
                                   id="returnChange"
                                   type="button"
                                >Return Change
                                </a>
                            </form><!--end of form-->
                        </div>
                    </div>
                </div><!--end of content div-->
            </div><!--end of main row-->
        </div><!--end of main container-->
    </div><!--end of page setup-->
    <script src="js/jquery-2.2.4.min.js"></script>
    <script src="../js/bootstrap.js"></script>
</body>
</html>

