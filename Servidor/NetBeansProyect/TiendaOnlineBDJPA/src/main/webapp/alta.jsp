<%-- 
    Document   : alta
    Created on : 18 ene. 2022, 20:46:42
    Author     : Lola
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Alta de articulos</h1>
        
        <form action="altaArticulos" method="Post" enctype="multipart/form-data">
            
           Denominación:     <input type="text" name="denominacion"/> <br>
          Categoria:  <input type="text" name="categoria"/><br>
          Precio:  <input type="number" name="precio" /><br>
          Existencias:   <input type="number" name="existencias" /> <br>
          Proveedor:   <input type="text" name="codproveedor"/><br>
          imagen:  <input type="file" name="imagen" /> <br>
          <input type="submit" value="alta"/>
            
            
            
        </form>
        
        
    </body>
</html>
