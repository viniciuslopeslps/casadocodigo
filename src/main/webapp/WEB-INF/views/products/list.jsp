<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<head>
    <c:url value="/resources/bootstrap" var="bootstrapPath"/>
    <link rel="stylesheet" href="${bootstrapPath}/css/bootstrap.min.css"/>
    <link rel="stylesheet" href="${bootstrapPath}/css/bootstrap-theme.min.css"/>

    <meta charset="UTF-8">
    <title>Livros de Java, Android, iPhone, Ruby, PHP e muito mais -
        Casa do Código</title>
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
            <a class="navbar-brand" href="#">Casa do Código</a>
        </div>
        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <ul class="nav navbar-nav">
                <li><a href="/produtos/lista">Lista de Produtos</a></li>
                <li><a href="/produtos/form">Cadastro de Produtos</a></li>
            </ul>
            <ul class="nav navbar-nav navbar-right">
                <li>
                    <a href="#">
                        <security:authorize access="isAuthenticated()">
                            <security:authentication property="principal" var="usuario"/>
                            Usuário: ${usuario.username}
                        </security:authorize>
                    </a>
                </li>
            </ul>
        </div>
    </div>
</nav>
<div class="container">
    <h1>Lista de Produtos</h1>
    <p>${message}</p>
    <table class="table table-bordered table-striped table-hover">
        <tr>
            <th>Título</th>
            <th>Descrição</th>
            <th>Páginas</th>
        </tr>

        <c:forEach items="${products}" var="product">
            <tr>
                <td><a href="/produtos/detalhe/${product.id}">${product.title}</a></td>
                <td>${product.description}</td>
                <td>${product.pages}</td>
            </tr>
        </c:forEach>
    </table>

    <script href="${bootstrapPath}/js/bootstrap.js"></script>
</div>
</body>
</html>