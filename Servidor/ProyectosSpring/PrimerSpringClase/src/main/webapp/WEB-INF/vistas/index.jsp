<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>PrimerJSP</title>
</head>
<body>
	<h1>PrimerJSP</h1>
	<c:out value="Bienvenido ${saludo} ${nombreUsu}"/><br>
	<a href="saludo">Saludo</a><br>
	<a href="quienes">Quienes Somos</a><br>
</body>
</html>