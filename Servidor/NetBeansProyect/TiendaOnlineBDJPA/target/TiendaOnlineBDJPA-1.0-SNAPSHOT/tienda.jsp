<%-- 
    Document   : tienda
    Created on : 16 ene. 2022, 11:42:11
    Author     : Lola
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Tienda</h1>
        <c:if test="${!empty param.cantidad}">
            <jsp:include page="gestionCarrito" />
            
        </c:if>
        <jsp:include page="consulta"/>
        <table>
        <c:forEach  items="${listaArticulos}" var="art" >
            <form action="tienda.jsp"> 
            <tr>
                <td>${art.denominacion}</td>
                <td>${art.categoria}</td>
                <td>${art.existencias}</td>
                <td>${art.precio}</td>
                <td><input type="text" name="cantidad"/></td>
            <input type="hidden" name="codArticulo" value="${art.codArticulo}"/>
          
            <td> <input type="submit" value="comprar" />     </td>     
            
            </tr>
            
            </form>
        </c:forEach>
        </table>
    </body>
</html>
