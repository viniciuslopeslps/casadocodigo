<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Livros de Java, Android, iPhone, Ruby, PHP e muito mais -
	Casa do Código</title>
</head>
<body>
	<p>${message}</p>
	<table>
		<tr>
			<td>Título</td>
			<td>Descrição</td>
			<td>Páginas</td>
		</tr>

		<c:forEach items="${products}" var="product">
			<tr>
				<td><a href="/produtos/detalhe/${product.id}">${product.title}</a></td>
				<td>${product.description}</td>
				<td>${product.pages}</td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>