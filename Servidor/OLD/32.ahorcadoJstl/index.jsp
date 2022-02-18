<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.*"%>
<%@page import="pojos.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<jsp:useBean id="juegoAhorcado" scope="application" class="pojos.Lista">
	<jsp:setProperty name="juegoAhorcado" property="cargarPalabras" value="${initParam.palabrasWebxml}"/>
</jsp:useBean>


<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8" />
		<title>Juego Ahorcado</title>
		<link rel="stylesheet" href="style.css">
	</head>
	<body>
		<h1>Inicio de Sesión - Ahorcado</h1>
		<form action="Ahorcado.jsp" method="POST">
			<label> Nombre de usuario: 
				<input type="text" name="nombreUsu" required />
			</label>
			<label> Contraseña: 
				<input type="password" name="passwd"/>
			</label>
			<input class="btn_sub" type="submit" value="Iniciar Sesión"/>
		</form>
 	</body>
</html>