<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>PrimerJSP</title>
</head>
<body>
	<h1>Primer Formulario</h1>
	<h3><c:out value="El alumno ${alumno.nombre} se ha registrado correctamente"/></h3>
	<a href="volver">Volver</a>
</body>
</html>