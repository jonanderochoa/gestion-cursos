<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

	<main class="center" role="main">
		<div class="container">
			<header><h2>Cursos</h2></header>
		</div>
		<div class="container-fluid text-center">
		
			<div class="row">
		        
		    
			<P>  The time on the server is ${serverTime}. </P>
			<div id="cabecera" class="row">
					<div class="col-xs-2">Código del curso</div>
					<div class="col-xs-2">Nombre del curso</div>
			</div>
				<div id="diezUltimos">
					<c:choose>
						<c:when test="${not empty listadocursos}">
							<c:forEach var="curso" items="${listadocursos}">
							<div class="row">
								<div class="col-xs-2">${curso.codcurso}</div>
								<div class="col-xs-2">${curso.nomcurso}</div>
							</div>
							</c:forEach>
						</c:when>
						<c:otherwise>
							<div class="row"><p class="col-xs-12">No se han encontrado cursos</p></div>
						</c:otherwise>
					</c:choose>
				</div>
				
				<div role="search">
					<form role="form">
			  			<div class="form-group">
			  				<label role="">Introduzca el Nombre del curso que quiere buscar</label>
			    			<input type="text" class="form-control" id="busqueda">
			  			</div>
			  			<button  role="button" id="busca" type="button" class="btn btn-default">Buscar</button>
					</form>
					<div id="buscador">
						<table id="resultado">
							<caption>Busqueda</caption>
						</table>
					</div>
				</div>
				</div>
		</div>
	</main>