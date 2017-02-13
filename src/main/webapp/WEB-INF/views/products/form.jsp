<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html>
<head>
    <!-- Import da taglib -->
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

    <c:url value="/resources/bootstrap" var="bootstrapPath"/>
    <link rel="stylesheet" href="${bootstrapPath}/css/bootstrap.min.css"/>
    <link rel="stylesheet" href="${bootstrapPath}/css/bootstrap-theme.min.css"/>
</head>
<body>
<nav class="navbar navbar-inverse">
    <div class="container">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse"
                    data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="/">Casa do Código</a>
        </div>
        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <ul class="nav navbar-nav">
                <li><a href="/produtos/lista">Lista de Produtos</a></li>
                <li><a href="/produtos/form">Cadastro de Produtos</a></li>
            </ul>
        </div><!-- /.navbar-collapse -->
    </div>
</nav>
<div class="container">
    <h1>Cadastro de produtos</h1>

    <p>${message}</p>

    <form:form servletRelativeAction="/produtos" method="post" commandName="product" enctype="multipart/form-data">
        <div class="form-group">
            <label>Título</label>
            <form:input type="text" path="title" cssClass="form-control"/>
            <form:errors path="title"/>
        </div>
        <div class="form-group">
            <label>Descrição</label>
            <form:textarea rows="10" cols="20" path="description" cssClass="form-control"/>
            <form:errors path="description"/>
        </div>
        <div class="form-group">
            <label>Páginas</label>
            <form:input type="text" path="pages" cssClass="form-control"/>
            <form:errors path="pages"/>
        </div>
        <div class="form-group">
            <label>Data de lançamento</label>
            <form:input path="launchDate" type="text" cssClass="form-control"/>
            <form:errors path="launchDate"/>
        </div>
        <c:forEach items="${types}" var="type" varStatus="status">
            <div class="form-group">
                <label>${type}</label>
                <form:input type="text" path="prices[${status.index}].value" cssClass="form-control"/>
                <form:input type="hidden" path="prices[${status.index}].type" value="${type}"/>
            </div>
        </c:forEach>
        <div class="form-group">
            <label>Sumário</label>
            <input name="summaryFile" type="file" class="form-control"/>
        </div>
        <button type="submit" class="btn btn-primary">Cadastrar</button>
    </form:form>
    <script href="${bootstrapPath}/js/bootstrap.js"></script>
</div>
</body>
</html>