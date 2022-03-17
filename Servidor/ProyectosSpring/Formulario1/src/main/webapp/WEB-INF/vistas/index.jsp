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
	<c:out value="Hola a todos"/>
	<table border=1>
		<thead>
			<tr>
				<th>Cod_Alum</th>
				<th>Nombre</th>
				<th>Apellidos</th>
				<th>Edad</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${listaAlumnos}" var="a">
				<tr>
					<td>${a.value.id}</td>
					<td>${a.value.nombre}</td>
					<td>${a.value.apellidos}</td>
					<td>${a.value.edad}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table><br>
	<hr>
	<form action="nuevoAlumno" method="post">
		ID: <input type="text" name="id"/><br>
		Nombre: <input type="text" name="nombre"/><br>
		Apellidos: <input type="text" name="apellidos"/><br>
		Edad: <input type="text" name="edad"/><br>
		<input type="submit" value="grabar"/><br>
	</form>
</body>
</html>