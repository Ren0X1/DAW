<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.*"%>
<%@page import="pojos.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!--Comprobamos si el jugador introducido existe o no-->
<jsp:include page="/ComprobarJugador"/>

<jsp:useBean id="juegoAhorcado" scope="application" class="pojos.Lista"/>

<!--................Creamos bean de partida.......................-->
<jsp:useBean id="miPartida" scope="session" class="pojos.Partida">
	<jsp:setProperty name="miPartida" property="nombreUsu" value="${param.nombreUsu}"/>
	<jsp:setProperty name="miPartida" property="puntos" value="${requestScope.puntuacion}"/>
	<jsp:setProperty name="miPartida" property="cambiarPalabra" value="${juegoAhorcado.palabras}"/>
</jsp:useBean>

<c:if test="${!empty param.letraIntroducida}">
	<jsp:include page="/ComprobarLetra"/>
</c:if>

<!--................Si le da a volver a jugar cambiamos palabra.......................-->
<c:if test="${!empty param.volverJugar}">
	<jsp:setProperty name="miPartida" property="cambiarPalabra" value="${juegoAhorcado.palabras}"/>
</c:if>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8" />
		<title>Juego Ahorcado</title>
		<link rel="stylesheet" href="style.css">
	</head>
	<body>
		<div class="estadisticas">
			<h3>Estadísticas de partida</h3>
			<p><b>Nombre: </b>${sessionScope.miPartida.nombreUsu}</p>
			<p><b>Puntos: </b>${sessionScope.miPartida.puntos}</p>
			<p><b>Aciertos: </b>${sessionScope.miPartida.aciertos}</p>
			<p><b>Fallos: </b>${sessionScope.miPartida.fallos}</p>
		</div>
		<img src="img/${miPartida.fallos}.jpg" alt="Imagen del ahorcado por fases">
		<h1>La palabra oculta es...</h1>
		
		<div class="palabra">
		<c:forEach var="letra" items="${sessionScope.miPartida.palabraAcertada}" >
			<p class="guion">${letra}</p>
		</c:forEach>
		</div>
		
		<div class="teclado">
			<!--..........Pintamos un teclado con las letras......................-->
			<c:forEach var="tecla" items="${juegoAhorcado.abc}">
				<form action="Ahorcado.jsp">
					<input type="hidden" name="letraIntroducida" value="${tecla}"/>
				<c:if test="${sessionScope.miPartida.letrasUsadas.contains(tecla)}">
					<input type="submit" name="letraT" value="${tecla}" disabled />
				</c:if>
				<c:if test="${!sessionScope.miPartida.letrasUsadas.contains(tecla)}">
					<input type="submit" name="letraT" value="${tecla}" />
				</c:if>						
				</form>
			</c:forEach>
		</div>
		
		<c:if test="${sessionScope.miPartida.fallos lt 5 && miPartida.aciertos lt miPartida.getAciertosMax()}">
			<button id="pista" title="${String.valueOf(sessionScope.miPartida.miPalabra)}">PISTA</button>
		</c:if>
		
		<c:if test="${sessionScope.miPartida.fallos eq 5}">
			<h2 class="red">HAS PERDIDO, TE HAN AHORCAO</h2>
			<form action="Terminar.jsp">
				<input type="submit" value="Cerrar Sesión"/>
			</form>
		</c:if>
		<c:if test="${miPartida.aciertos eq miPartida.getAciertosMax()}">
			<jsp:setProperty name="miPartida" property="aciertos" value="${0}"/>
			<jsp:setProperty name="miPartida" property="fallos" value="${0}"/>
			<jsp:setProperty name="miPartida" property="limpiaLetrasUs" value="prueba"/>
			
			<h2 class="green">HAS GANADO...¡¡ENHORABUENA!!</h2>
			<form action="Terminar.jsp">
				<input class="btn_sub" type="submit" value="Cerrar Sesión"/>
			</form>
			<form action="Ahorcado.jsp">
				<input type="hidden" name="volverJugar" value="si"/>
				<input class="btn_sub" type="submit" value="Volver a jugar"/>
			</form>
		</c:if>
 	</body>
</html>