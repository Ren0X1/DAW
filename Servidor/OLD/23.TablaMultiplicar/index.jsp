<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Visualización de la fecha y hora</title>
    </head>
    <body>
    <h1>Buenos días</h1>
        <form action="index.jsp">
            <input type="number" value="0" name="numero"/>
            <input type="submit" value="Calcular"/><br>
            <%String num = request.getParameter("numero");
                if (num!=null && num!="") {%>
                    <%="<p>El numero introducido es: "+num+"</p>"%>
                <%  for (int i=1;i!=10+1;i++) {%>
                        <%="<p>"+num+" * "+i+" = "+(Integer.parseInt(num)*i)+"</p>"%>
                <%  }        
                } else if (num=="") {%>
                    <%="<h2>Solo puedes poner numeros</h2>"%>
                <%}
            %>
        <form>
    </body>
</html>