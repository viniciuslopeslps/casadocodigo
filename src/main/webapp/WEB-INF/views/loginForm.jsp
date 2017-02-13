<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<!DOCTYPE html>
<html>
<head>
    <!-- Import da taglib -->
    <c:url value="/resources/bootstrap" var="bootstrapPath"/>
    <link rel="stylesheet" href="${bootstrapPath}/css/bootstrap.min.css"/>
    <link rel="stylesheet" href="${bootstrapPath}/css/bootstrap-theme.min.css"/>
</head>
<body>

<div class="container">
    <h1>Login</h1>

    <p>${message}</p>

    <form:form servletRelativeAction="/login" method="post">
        <div class="form-group">
            <label>Email</label>
            <input type="text" name="username" class="form-control"/>
        </div>
        <div class="form-group">
            <label>Senha</label>
            <input type="password" name="password" class="form-control"/>
        </div>
        <button type="submit" class="btn btn-primary">Logar</button>
    </form:form>
    <script href="${bootstrapPath}/js/bootstrap.js"></script>
</div>
</body>
</html>