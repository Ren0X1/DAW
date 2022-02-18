<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="pojos.*"%>
<%@page import="java.util.*"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="x" uri="http://java.sun.com/jsp/jstl/xml" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<!DOCTYPE HTML>
<html>
    <head>
        <meta charset="utf-8">
        <title>TITULO</title>
		<style>
			.espacio {
				margin-left: 5px;
			}
		</style>
		<link rel='stylesheet' href='./css/styles.css'>
		<script src="./js/index.js"></script>
    </head>
    <body>
		<c:if test="${param.select1!=null}">
			<fmt:setLocale value="${param.select1}"/>
		</c:if>
		<fmt:setBundle basename="locale"/>
		<h1><fmt:message key="txtLogin"/></h1>
        <form action="index.jsp">
			<label for="nombreUsuario"><fmt:message key="txtNombre"/></label><input class="espacio" id="nombreUsuario" name="nombreUsuario" type="text"><br><br>
			<label for="contraseniaUsuario"><fmt:message key="txtPassword"/></label><input class="espacio" id="contraseniaUsuario" name="contraseniaUsuario" type="password"><br><br>
			<label for="select1"><fmt:message key="txtIdioma"/></label>
			<select id="select1" name="select1">
				<option value="es"><fmt:message key="txtSpa"/></option>
				<option value="en"><fmt:message key="txtIngles"/></option>
			</select>
			<br><br>
			<input type="submit" value='<fmt:message key="btnAceptar"/>'><br><br>
			<hr><br>
			<button type="submit" class="us" name="pais" value="GMT-7"></button>
			<button type="submit" class="id" name="pais" value="GMT+5"></button>
			<button type="submit" class="ko" name="pais" value="GMT+8"></button>
			<button type="submit" class="ru" name="pais" value="GMT+6"></button>
			<button type="submit" class="ch" name="pais" value="GMT+7"></button>
        </form><br>
		<h4>Hora: </h4>
		<c:if test="${param.pais!=null}">
			<fmt:setTimeZone value="${param.pais}"/>
		</c:if>
		<jsp:useBean id="now" class="java.util.Date"/>
		<fmt:formatDate value="${now}" type="TIME" dateStyle="FULL"/><br><br>
		<h2>TEST SIMBOLOS DE DINERO</h2><br>
		<c:set var = "dinero" value = "120000.2309" />
		<fmt:formatNumber value = "${dinero}" type = "currency"/>
    </body>
</html>