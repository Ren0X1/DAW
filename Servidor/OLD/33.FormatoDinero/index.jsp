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
        <form action="index.jsp">
			<label for="select1">Pais: </label>
			<select id="select1" name="select1">
				<option value="." disabled selected>Seleccionar Pais</option>
				<option value="es_ES">España</option>
				<option value="es_MX">Mexico</option>
				<option value="en_US">USA</option>
				<option value="en_GB">UK</option>
				<option value="de_DE">Alemania</option>
				<option value="ru_RU">Rusia</option>
				<option value="ja_JP">Japon</option>
				<option value="zh_CN">China</option>
			</select>
			<br><br>
			<input id="boton" type="submit" value='OK'><br><br>
			<hr><br>
        </form><br>
		<h2>TEST SIMBOLOS DE DINERO</h2><br>
		<c:set var="dinero" value="120000.2309" />
		<fmt:formatNumber value="${dinero}" type="currency"/>
    </body>
</html>