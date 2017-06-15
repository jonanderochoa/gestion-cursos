<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="sec"	uri="http://www.springframework.org/security/tags"%>
	
<nav class="navbar navbar-inverse" role="navigation">
	<div class="container-fluid">
		<!-- ESTO SOLO SE VE EN MOVIL -->
		<div class="navbar-header">
			<button type="button" class="navbar-toggle collapsed"
				data-toggle="collapse" data-target="#bs-example-navbar-collapse-1"
				aria-expanded="false">
				<span class="sr-only">Pincha para visualizar</span> <span
					class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
			<!-- Logo de la empresa -->
			<a class="navbar-brand" href="www.ipartek.com">Ipartek</a>
		</div>

		<!-- Collect the nav links, forms, and other content for toggling -->
		<div class="collapse navbar-collapse"
			id="bs-example-navbar-collapse-1">
			<ul class="nav navbar-nav">
				<li><a href="/gestioncursos/"><spring:message code="nav.inicio" text="Inicio"/></a></li>
				<li><a href="<c:url value="/cursos" />"><spring:message code="nav.Intranet" text="Intranet"/></a></li>
			</ul>
			<!-- LO PONE A LA DERECHA --> 
			<ul class="nav navbar-nav navbar-right">
				<li class="dropdown"><a href="#" class="dropdown-toggle"
					data-toggle="dropdown" role="button" aria-haspopup="true"
					aria-expanded="false"><spring:message code="nav.Idioma" text="Idioma"/><span class="caret"></span></a>
					<ul class="dropdown-menu">
						<li><a href="?locale=es"><spring:message code="idioma.castellano" text="Español" /></a></li>
						<li><a href="?locale=en"><spring:message code="idioma.ingles" text="Ingles" /></a></li>
						<li><a href="?locale=eu"><spring:message code="idioma.euskera" text="Euskera" /></a></li>
					</ul>
				</li>
				<sec:authorize access="isAuthenticated()">
					<li><a href="<c:url value="/logout" />"><spring:message code="nav.Logout" text="Cerrar Sesión"/></a></li>
				</sec:authorize>
				<sec:authorize access="isAnonymous()">
					<li><a href="<c:url value='/login.html' />"><spring:message code="nav.Login" text="Iniciar Sesión"/></a></li>
				</sec:authorize>
			</ul>  
		</div>
		<!-- /.navbar-collapse -->
	</div>
</nav>