<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
<%--
  Created by IntelliJ IDEA.
  User: n0280208
  Date: 4/13/2018
  Time: 10:30 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit</title>
</head>
<body>

<!-- passing command model from controller -->
<sf:form action="/player/edit" method="post" commandName="commandModel">

    <sf:hidden path="id"/>

    First Name: <sf:input path="first" />  <sf:errors path="first" />     <br />
    Last Name: <sf:input path="last" />    <sf:errors path="last" />      <br />
    Hometown: <sf:input path="hometown" /> <sf:errors path="hometown" />  <br />

    Team:
    <sf:select path="teamId">
        <sf:option value="" label="No team" />
        <sf:options items="${viewModel.teams}" itemValue="id" itemLabel="name" />
    </sf:select>
    <sf:errors path="teamId"/>

    <br />

    Positions:
    <sf:select path="positionIds">
        <sf:options items="${viewModel.positions}" itemValue="id" itemLabel="name" />
    </sf:select>
    <sf:errors path="positionIds"/>

    <br />

    <button type="submit">THE BUTTON THAT DOES SAVING</button>

</sf:form>

</body>
</html>
