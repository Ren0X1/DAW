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
        <link rel="stylesheet" href="css/style.css">
        <title>JSP Page</title>
    </head>
    <body>
        <jsp:include page="/Operaciones"/>
        <c:if test="${not empty param.titulo}">
            <jsp:include page="/Operaciones1"/>
        </c:if>
        <c:if test="${not empty param.director}">
            <jsp:include page="/Operaciones2"/>
        </c:if>
        <c:if test="${not empty param.genero}">
            <jsp:include page="/Operaciones3"/>
        </c:if>
        <h1>Cine Online</h1>
        <table border="1">
            <tr>
            <form action="index.jsp">
                <th><input type="submit" value="Titulo" name="titulo"></th>
                <th><input type="submit" value="Director" name="director"></th>
                <th><input type="submit" value="Genero" name="genero"></th>
                <th>Ver Ficha</th>
            </form>
            </tr>
            <c:forEach items="${requestScope.listaPeliculas}" var="peli">
                <form action="index.jsp">
                    <tr>
                        <td>${peli.titulo}</td>
                        <td>${peli.nombreDirector}</td>
                        <td>${peli.genero}</td>
                        <input type="hidden" value="${peli.titulo}" name="codigo">
                        <td><input type="submit" value="Informacion" name="op"></td>
                    </tr>
                </form>
            </c:forEach>
        </table>
        <br><br><hr>
        <c:if test="${not empty param.codigo}">
            <jsp:include page="/VerInformacion"/>
        </c:if>
    </body>
</html>
