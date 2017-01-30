<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!-- Import da taglib -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>


<p>${message}</p>

<form:form action="/produtos" method="post" commandName="product" enctype="multipart/form-data">
	<div>
		<label>Título</label>
		 <form:input type="text" path="title" />
		 <form:errors path="title" />
	</div>
	<div>
		<label>Descrição</label>
		<form:textarea rows="10" cols="20" path="description"></form:textarea>
		<form:errors path="description" />
	</div>
	<div>
		<label>Páginas</label>
		 <form:input type="text" path="pages" />
		 <form:errors path="pages" />
	</div>
	<div>
		<label>Data de lançamento</label>
		<form:input path="launchDate" type="text"/>
		<form:errors path="launchDate"></form:errors>
	</div>
	 <c:forEach items="${types}" var="type" varStatus="status">
        <div>
            <label>${type}</label>
            <form:input type="text" path="prices[${status.index}].value" />
            <form:input type="hidden" path="prices[${status.index}].type" value="${type}" />
        </div>
    </c:forEach>
	<div>
		<label>Sumário</label> 
		<input name="summaryFile" type="file" />
	</div>
	<button type="submit">Cadastrar</button>
</form:form>