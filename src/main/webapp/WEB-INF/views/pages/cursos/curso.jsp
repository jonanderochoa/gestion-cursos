<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<main>
	<form:form action="save" method="post" modelAttribute="curso" class="container">
	<c:if test="${!empty curso}">
		<form:hidden path="codigo" />
	</c:if>

	<div class="form-group row">
		<form:label cssClass="control-label hidden-xs col-sm-2" path="codcurso">Código del curso:</form:label>
		<div class="col-sm-7"><form:input placeholder="Introduzca el código del curso" path="codcurso" cssErrorClass="text-danger" cssClass="form-control" /></div>
		<div class="col-sm-3"><form:errors path="codcurso" cssClass="text-danger" /></div>
	</div>

	<div class="form-group row">
		<form:label cssClass="control-label hidden-xs col-sm-2" path="nomcurso">Nombre del curso:</form:label>
		<div class="col-sm-7"><form:input placeholder="Escriba el nombre del curso" path="nomcurso" cssErrorClass="text-danger" cssClass="form-control" /></div>
		<div class="col-sm-3"><form:errors path="nomcurso" cssClass="text-danger" /></div>
	</div>
	
	<c:set var="men" value="Crear" />
	<c:if test="${curso.codigo > 0}">
		<c:set var="men" value="Editar" />
	</c:if>
	<input class="btn btn-success" type="submit" value="${men}">
</form:form> 
</main>