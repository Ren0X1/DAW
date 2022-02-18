<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <title>Demostración Subida de Ficheros</title>
    </head>
    <body>
        <form action="Upload" method="post" enctype="multipart/form-data">
            <label>Selecciona un archivo: <input type="file" name="fichero"></label><br>
            <input type="submit" value="Subir Archivo" name="enviar">
        </form>
    </body>
</html>