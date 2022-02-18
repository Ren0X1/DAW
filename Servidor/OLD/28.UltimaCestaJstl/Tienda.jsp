<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="pojos.*"%>
<%@page import="java.util.*"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="x" uri="http://java.sun.com/jsp/jstl/xml"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>
<html>
	<head>
		<link rel='stylesheet' href='./css/styles.css'>
		<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
		<script src="./js/index.js"></script>
	</head>
	<jsp:useBean id="miTienda" scope="application" class="pojos.Tienda">
		<jsp:setProperty name="miTienda" property="nombreTienda" value='Tienda2DAW' />
		<jsp:setProperty name="miTienda" property="listaArticulos" value='${applicationScope.mapaArticulos}' />	
	</jsp:useBean>
	<jsp:useBean id="miCesta" scope="session" class="pojos.Cesta">
		<jsp:setProperty name="miCesta" property="*" />
		<jsp:setProperty name="miCesta" property="nombreUsuario" value='${param.nombreUsuario}' />
 	</jsp:useBean>
	<jsp:include page='/addArt'/>
	<body>
		<h3>Bienvenido ${miCesta.nombreUsuario} <br>a ${miTienda.nombreTienda}</h3>
	<div class="container">
			<form action="Tienda.jsp">
			<table>
				<tr>
					<th> 
						Cantidad
					</th>
					<th>
						Imagen
					</th>
					<th> 
						Nombre
					</th>
					<th> 
						Existencias
					</th>
				</tr>
				<c:forEach var="articulo" items="${miTienda.listaArticulos}">
				<tr>
					<td> 
					<span class="input-number-decrement" onclick="decrementar(this)">–</span><input disabled name='articulosSeleccionados' class="input-number" type="text" value="0" min="0" max='${articulo.value.existenciasArticulo}'><span class="input-number-increment" onclick="aumentar(this)">+</span>
					</td>
					<td>
						<img src='imagenes/${articulo.value.imagenArticulo}.png'>
					</td>
					<td>
						${articulo.value.nombreArticulo}
					</td>
					<td>
						<c:choose>
							<c:when test="${articulo.value.existenciasArticulo ne 0}">
								Unidades disponibles: ${articulo.value.existenciasArticulo}
							</c:when>
							<c:otherwise>
								No hay unidades en stock
							</c:otherwise>
						</c:choose>
					</td>
				</tr> 
				</c:forEach>
			</table>
			<hr>
			<input type="submit" value="Añadir artículos">
			</form>
			<form action="verCesta.jsp">
			<input type="submit" value="Ver cesta">
			</form>
		</div>
	</body>
</html>