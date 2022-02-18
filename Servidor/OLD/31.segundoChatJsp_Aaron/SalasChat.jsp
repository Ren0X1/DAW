<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="pojos.*"%>
<%@page import="java.util.*"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/xml" prefix="x"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

    <!-- Crear las salas de chat introducidas por el usuario -->
    <c:if test="${!empty param.nuevaSala}">
        <jsp:useBean id="salaX" scope="request" class="pojos.Sala">
            <jsp:setProperty name="salaX" property="nombreSala" value="${param.nuevaSala}"/>
        </jsp:useBean>
        
        <jsp:setProperty name="chat" property="addSala" value="${salaX}"/>
    </c:if>

    <!-- Salas preestablecidas -->
    <jsp:useBean id="sala1" scope="application" class="pojos.Sala">
        <jsp:setProperty name="sala1" property="nombreSala" value="General"/>
    </jsp:useBean>

    <jsp:useBean id="sala2" scope="application" class="pojos.Sala">
        <jsp:setProperty name="sala2" property="nombreSala" value="Comida"/>
    </jsp:useBean>

    <jsp:useBean id="sala3" scope="application" class="pojos.Sala">
        <jsp:setProperty name="sala3" property="nombreSala" value="Cine"/>
    </jsp:useBean>

    <!-- Crear chat y guardar salas -->
    <jsp:useBean id="chat" scope="application" class="pojos.Chat">
        <jsp:setProperty name="chat" property="addSala" value="${sala1}"/>
        <jsp:setProperty name="chat" property="addSala" value="${sala2}"/>
        <jsp:setProperty name="chat" property="addSala" value="${sala3}"/>
    </jsp:useBean>

    <!-- Crear el usuario -->
    <jsp:useBean id="usuario" scope="session" class="pojos.Usuario">
        <jsp:setProperty name="usuario" property="nick" value="${param.nick}"/>
        <jsp:setProperty name="usuario" property="color" value="${param.color}"/>
    </jsp:useBean>

    <!-- Eliminar el usuario de la sala de la que ha salido -->
    <c:if test="${!empty param.cambiarSala}">
        <c:set target="${applicationScope.chat.getObtenerSala(sessionScope.salaElegida)}" property="removeUsuario" value="${sessionScope.usuario}"/>        
    </c:if>

<!-- Incluir cabecera -->
<jsp:include page="/cabecera.html"/>

    <h2>Seleccione la sala</h2>
    <form action='Chat.jsp'>
        
        <c:forEach var="sala" items="${applicationScope.chat.listaSalas}">
            <input type='radio' name='sala' value='${sala.value.nombreSala}' required>${sala.value.nombreSala}</br>
        </c:forEach>

        <input type='submit' value='Entrar'>
    </form>

    <form action='SalasChat.jsp'>
        <input type="text" name="nuevaSala">
        <input type="submit" value="Crear Nueva Sala">
    </form>

<!-- Incluir pie -->
<jsp:include page="/pie.html"/>