<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
    <head>
        <meta charset="utf-8">
        <title>TITULO</title>
		<style>
			.espacio {
				margin-left: 5px;
			}
		</style>
    </head>
    <body>
		<h1>Calculo Tabla Amortización</h1>
        <form action="index.jsp">
			<label for="capital">Capital:  </label><input class="espacio" id="capital" name="capital" type="number"><br><br>
			<label for="interes">Interes:  </label><input class="espacio" id="interes" name="interes" type="number" step="0.01"><br><br>
			<label for="anos">Años:  </label><input class="espacio" id="anos" name="anos" type="number" step="1"><br><br>
			<label for="periodo">Periodicidad:  </label>
			<select id="periodo" name="periodo">
				<option value="elegir" selected disabled>Elegir</option>
				<option value="1">Anual</option>
				<option value="2">Semestral</option>
				<option value="4">Trimestral</option>
				<option value="12">Mensual</option>
			</select><br><br>
			<input type="submit" value="Calcular">
        </form>

        <%
            String capital = request.getParameter("capital");
            String interes = request.getParameter("interes");
            String anos = request.getParameter("anos");
            String periodo = request.getParameter("periodo");
            if (capital!=null && capital!="" && interes!=null && interes!="" && anos!=null && anos!="" && periodo!=null && periodo!="" && periodo!="elegir") {
				double cap = Integer.parseInt(capital);
				double a = Integer.parseInt(anos);
				double per = Integer.parseInt(periodo);
				double inte = Integer.parseInt(interes);
				double i = (inte/100)/per;
				double np = a*per;
				double[][] tabla = new double[(int)np][3];
				double cuota = cap*i*Math.pow((1+i),np)/(Math.pow((1+i),np)-1);
				double capPendiente = cap;
				request.setAttribute("cap", cap);
				request.setAttribute("a", a);
				request.setAttribute("per", per);
				request.setAttribute("inte", inte);
				request.setAttribute("i", i);
				request.setAttribute("np", np);
				request.setAttribute("tabla", tabla);
				request.setAttribute("cuota", cuota);
				request.setAttribute("capPendiente", capPendiente);
				%>
				<jsp:forward page="/Pintar.jsp"/>
			<%}
        %>
    </body>
</html>