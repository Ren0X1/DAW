<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="pojos.*"%>
<%@page import="java.util.*"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/xml" prefix="x" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

    <!-- Guardar nombre de la sala en la session para no perderla al enviar el mensaje y
        sobreescribirla cada vez que se entre desde SalasChat.jsp -->
    <c:if test="${!empty param.sala}">
        <c:set var="salaElegida" scope="session" value="${applicationScope.chat.getObtenerSala(param.sala).nombreSala}" />
        <c:set target="${applicationScope.chat.getObtenerSala(sessionScope.salaElegida)}" property="addUsuario" value="${sessionScope.usuario}"/>
    </c:if>
    
    <!-- Generar la instancia de Mensaje y guardarla en la sala correspondiente -->
    <c:if test="${!empty param.mensaje}">
        <jsp:useBean id="mensajito" scope="request" class="pojos.Mensaje">
            <jsp:setProperty name="mensajito" property="texto" value="${param.mensaje}"/>
            <jsp:setProperty name="mensajito" property="nick" value="${sessionScope.usuario.nick}"/>
            <jsp:setProperty name="mensajito" property="color" value="${sessionScope.usuario.color}"/>
        </jsp:useBean>
        
        <c:set target="${applicationScope.chat.getObtenerSala(sessionScope.salaElegida)}" property="addMensaje" value="${mensajito}" />
    </c:if>

<!-- Incluir cabecera -->
<jsp:include page="/cabecera.html"/>
    <h1>Chat: <c:out value="${sessionScope.salaElegida}"/></h1>

    <div style="float: left; width: 30%;margin-right: 1em;">
        <h2>Mensajes</h2>
        <c:forEach var="mensajote" items="${applicationScope.chat.getObtenerSala(sessionScope.salaElegida).listaMensajes}">
            <p style="color:${mensajote.color};"><c:out value="${mensajote.nick}"/>: <c:out value="${mensajote.texto}"/></p>
        </c:forEach>
    </div>

    <div style="float: left; width: 20%;">
        <h2>Usuarios conectados</h2>
        <c:forEach var="usuarioCon" items="${applicationScope.chat.getObtenerSala(sessionScope.salaElegida).usuariosConectados}">
            <p style="color:${usuarioCon.value.color};"><c:out value="${usuarioCon.value.nick}"/></p>
        </c:forEach>
    </div>


    <form action='Chat.jsp' method='POST'>
        <input type='text' name='mensaje'>
        <input type='submit' value='Enviar'>
    </form>

    <form action='SalasChat.jsp' method='POST'>
        <input type='submit' value='Cambiar de Sala' >
        <input type="hidden" name="cambiarSala" value="ok">
    </form>

    <form action='fin' method='POST'>
        <input type='submit' value='Desconectar'>
    </form>


<!-- Incluir pie -->
<jsp:include page="/pie.html"/>