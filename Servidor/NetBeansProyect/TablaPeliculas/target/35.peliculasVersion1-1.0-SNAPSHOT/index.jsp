<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.*"%>
<%@page import="com.daw.DTO.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="x" uri="http://java.sun.com/jsp/jstl/xml" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel='stylesheet' href='styles.css'>
        <title>JSP Page</title>
    </head>
    <body>
        <jsp:useBean id="peli" scope="request" class="com.daw.DTO.Pelicula">
            <jsp:setProperty name="peli" property="*"/>
        </jsp:useBean>
        
        <c:if test="${param.addRegistro!=null}">
            <jsp:include page="/OperacionesDB"/>
        </c:if>
        <c:if test="${param.borrar!=null}">
            <jsp:include page="/OperacionesDB"/>
        </c:if>
        <jsp:include page="/ConsultarPeli"/>
        <table border="1">
            <tr>
                <th>Codigo Pelicula</th>
                <th>Director</th>
                <th>Estreno</th>
                <th>Publico</th>
                <th>Fecha</th>
                <th>Genero</th>
                <th>Imagen</th>
                <th>Nombre</th>
                <th>Operacion</th>
            </tr>
            <tr>
            <form action="index.jsp" method="POST">
                <td><input type="text" name="key" disabled value="AUTO INCREMENTADA"/></td>
                <td><input type="text" name="director"/></td>
                <td><input type="checkbox" name="estreno"/></td>
                <td><input type="checkbox" name="publico"/></td>
                <td><input type="text" name="fecha"/></td>
                <td><input type="text" name="genero"/></td>
                <td><input type="text" name="imagen"/></td>
                <td><input type="text" name="nombre"/></td>
                <input type="hidden" name="opera" value="add"/>
                <td><input type="submit" name="addRegistro" value="Guardar"/></td>
            </form>
            </tr>
            <c:forEach var="var" items="${requestScope.ResultadoTabla.rows}">
                <tr>
                    <td><c:out value="${var.codigo_pelicula}"/></td>
                    <td><c:out value="${var.director}"/></td>
                    <td>
                        <c:if test="${var.estreno!=false}">
                            <input type="checkbox" disabled checked>
                        </c:if>
                        <c:if test="${var.estreno!=true}">
                            <input type="checkbox" disabled>
                        </c:if>
                    </td>
                    <td>
                        <c:if test="${var.publico!=false}">
                            <input type="checkbox" disabled checked>
                        </c:if>
                        <c:if test="${var.publico!=true}">
                            <input type="checkbox" disabled>
                        </c:if>
                    </td>
                    <td><c:out value="${var.fecha_insercion}"/></td>
                    <td><c:out value="${var.genero}"/></td>
                    <td><c:out value="${var.imagen}"/></td>
                    <td><c:out value="${var.nombre}"/></td>
                    <form action="index.jsp" method="POST">
                        <input type="hidden" name="codigoBorrar" value="<c:out value="${var.codigo_pelicula}"/>"/>
                        <input type="hidden" name="opera" value="borrar"/>
                        <td><input type="submit" name="borrar" value="Borrar"/></td>
                    </form>
                </tr>
            </c:forEach>
        </table>
    </body>
</html>
