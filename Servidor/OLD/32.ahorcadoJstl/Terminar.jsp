<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.*"%>
<%@page import="pojos.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<c:set var="jugador" scope="page" value="${applicationScope.juegoAhorcado.obtenerJugador(miPartida.nombreUsu)}" />
<c:set target="${jugador}" property="puntuacion" value="${jugador.puntuacion + miPartida.puntos}" />

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8" />
		<title>Juego Ahorcado</title>
		<link rel="stylesheet" href="style.css">
	</head>
	<body>
		<h1>Gracias por jugar, ${sessionScope.miPartida.nombreUsu}</h1>
		<h3>Recuerda que tus puntos se guardarán en este usuario.</h3>
		<h3>Puntos hasta ahora: ${sessionScope.miPartida.puntos}</h3>
		<% session.invalidate(); %>
		<form action="index.jsp">
			<input type="submit" class="btn_sub" value="Volver a inicio"/>
		</form>
 	</body>
</html>

