<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Visualización de la fecha y hora</title>
    </head>
    <body>
    <h1>Buenos días</h1>
        <p>estamos a <%
            out.println(new java.util.GregorianCalendar().getTime().toLocaleString());
            %>
        </p>
    </body>
</html>