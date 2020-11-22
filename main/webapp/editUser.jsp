<%@ page import="com.nexttech.internship.contApp.model.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit user</title>
</head>
<body>
<% User user = (User) request.getAttribute("user"); %>
<form action="${pageContext.request.contextPath}/editUser" method="post">
    <input name="userId" id="userId" hidden value="${user.getId()}">
    <label for="username">Username</label>
    <input name="username" id="username" type="text" value="${user.getUsername()}">
    <label for="password">Password</label>
    <input name="password" id="password" type="text" value="${user.getPassword()}">
    <label for="email">Email</label>
    <input name="email" id="email" type="text" value="${user.getEmail()}">
    <label for="role">Role</label>
    <input name="role" id="role" type="text" value="${user.getRole()}">
    <label for="firmId">Firm ID</label>
    <input name="firmId" id="firmId" type="text" value="${user.getFirmId()}">
    <button name="submitUserUpdate" type="submit" formmethod="post">Update</button>
</form>
</body>
</html>
