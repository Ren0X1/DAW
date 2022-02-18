<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
    <head>
        <meta charset="utf-8">
        <title>TITULO</title>
		<link rel='stylesheet' href='css/style.css'>
    </head>
    <body>
		<%
			double cap = (double)request.getAttribute("cap");
			double a = (double)request.getAttribute("a");
			double per = (double)request.getAttribute("per");
			double inte = (double)request.getAttribute("inte");
			double i = (double)request.getAttribute("i");
			double np = (double)request.getAttribute("np");
			double[][] tabla = (double[][])request.getAttribute("tabla");
			double cuota = (double)request.getAttribute("cuota");
			double capPendiente = (double)request.getAttribute("capPendiente");
		%>
		<table>
			<thead>
				<tr>
					<th>Indice</th>
					<th>Interes</th>
					<th>Capital Amortizado</th>
					<th>Cuota</th>
					<th>Capital Pendiente</th>
				</tr>
			</thead>
				<%for (int f=0;f!=np;f++) {
					double interesRecibo = capPendiente*i;
					double capAmortizado = cuota-interesRecibo;
					capPendiente = capPendiente-capAmortizado;
					tabla[f][0] = interesRecibo;
					tabla[f][1] = capAmortizado;
					tabla[f][2] = capPendiente;%>
					<tr>
						<td>
							<%=(f+1)%>
						</td>
						<td>
							<%=String.format("%.2f", tabla[f][0])+"$"%>
						</td>
						<td>
							<%=String.format("%.2f", tabla[f][1])+"$"%>
						</td>
						<td>
							<%=String.format("%.2f", cuota)+"$"%>
						</td>
						<td>
							<%=String.format("%.2f", tabla[f][2])+"$"%>
						</td>
					</tr>
			    <%}%>
		</table>
    </body>
</html>