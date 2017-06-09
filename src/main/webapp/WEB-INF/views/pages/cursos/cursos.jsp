<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

	<main class="center">
		<div class="container">
			<header><h2>Cursos</h2></header>
		</div>
		<div class="container-fluid">
		<div id="nuevo" class ="row col-xs-12"><a class="btn btn-primary" href="<c:url value="/cursos/addCurso"/>">Nuevo curso</a></div>
		<div id="cabecera" class="row">
				<div class="col-xs-2">Código del curso</div>
				<div class="col-xs-2">Nombre del curso</div>
				<div class="col-xs-2"></div>
			</div>
			
				<c:choose>
					<c:when test="${not empty listadocursos}">
						<c:forEach var="curso" items="${listadocursos}">
						<div class="row">
							<div class="col-xs-2">${curso.codcurso}</div>
							<div class="col-xs-2">${curso.nomcurso}</div>
							<div class="btn-group col-xs-2">
							<a class="btn btn-warning" href="<c:url value='/cursos/${curso.codigo}'/>">Editar</a>
							<a class="btn btn-danger" href="<c:url value='/cursos/deleteCurso/${curso.codigo}'/>">Borrar</a>
							</div>
						</div>
						</c:forEach>
					</c:when>
					<c:otherwise>
						<div class="row"><p class="col-xs-12">No se han encontrado cursos</p></div>
					</c:otherwise>
				</c:choose>
		</div>
	</main>