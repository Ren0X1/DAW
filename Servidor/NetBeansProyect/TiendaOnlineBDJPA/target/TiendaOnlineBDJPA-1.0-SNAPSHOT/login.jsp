
<%-- 
    Document   : login
    Created on : 16 ene. 2022, 11:20:25
    Author     : Lola
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Bienvenido a la tienda Online 2º Daw</h1>
        <c:if test="${!empty param.usu}">
            
        <jsp:include page="comprobarUsu"/>
           <c:if test="${encontrado}"><jsp:forward page="tienda.jsp"/> </c:if>  
           <c:out value="lo sentimos revise usuario y contraseña"/>
        </c:if>
        <form action="login.jsp">
            usuario <input type="text" name="usu">
            contraseña <input type="text" name="pass">
            <input type="submit" value="entrar"/>
            
            
        </form>
    </body>
</html>
