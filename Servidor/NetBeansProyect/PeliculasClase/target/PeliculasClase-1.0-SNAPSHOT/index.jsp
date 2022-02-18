<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="x" uri="http://java.sun.com/jsp/jstl/xml" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Listado de peliculas</h1>
        <jsp:include page="/Operaciones"/>
        <table border="1">
            <tr>
                <c:forEach items="${cabeceras}" var="cab" begin="1">
                    <th>${cab[0]}</th>
                </c:forEach>
            </tr>
            <c:forEach items="${requestScope.listaPeliculas}" var="peli">
                <form action="index.jsp">
                    <tr>
                        <td>${peli.nombre}</td>
                        <td>${peli.director}</td>
                        <td>${peli.genero}</td>
                        <td>${peli.estreno}</td>
                        <td>${peli.publico}</td>
                        <td><fmt:formatDate value="${peli.fechaInsercion}" pattern="dd-MM-yyyy"/></td>
                        <td>${peli.imagen}</td>
                        <input type="hidden" value="${peli.codigoPelicula}" name="codigo">
                        <td><input type="submit" value="Borrar" name="op"></td>
                        <td><input type="submit" value="Modificar" name="op"></td>
                    </tr>
                </form>
            </c:forEach>
        </table>
    </body>
</html>
