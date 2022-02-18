<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="pojos.*"%>
<%@page import="java.util.*"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="x" uri="http://java.sun.com/jsp/jstl/xml"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>
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
		<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
		<script src="./js/index.js"></script>
    </head>
    <body>
		<h1>Login</h1>
        <form action="index.jsp">
			<label for="nombreUsuario">nombre:  </label><input class="espacio" id="nombreUsuario" name="nombreUsuario" type="text"><br><br>
			<label for="contraseniaUsuario">contraseña:  </label><input class="espacio" id="contraseniaUsuario" name="contraseniaUsuario" type="password"><br><br>
			<input type="submit" value="Entrar">
        </form>

        <%
            String nombre = request.getParameter("nombreUsuario");
            String contrasenia = request.getParameter("contraseniaUsuario");
            if (nombre!=null && nombre!="" && contrasenia!=null && contrasenia!="") {%>
				<jsp:forward page="/index"/>
			<%}
        %>
    </body>
</html>