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
	<body>
	<h3>Cesta de ${miCesta.nombreUsuario}</h3>
	<jsp:useBean id="miCesta"  scope="session" class="pojos.Cesta"/>
	<div class="container">
			<form action="Tienda.jsp">
			<table>
				<tr>
					<th> 
						Imagen
					</th>
					<th>
						Nombre
					</th>
					<th> 
						Cantidad
					</th>
					<th> 
						Precio
					</th>
					<th> 
						Añadir Articulo
					</th>
					<th> 
						Quitar Articulo
					</th>
					<th> 
						Borrar Articulo
					</th>
				</tr>
				<c:forEach var="articuloCesta" items="${miCesta.carrito}">
					<c:set var="objArticulo" scope="page" value="${miTienda.getObtenerArticulo(articuloCesta.key)}"/>
					<c:choose>
						<c:when test="${articuloCesta.value!=0}">
							<tr>
								<td>
									<img src="imagenes/${objArticulo.imagenArticulo}.png">	
								</td>
								<td> 
									${objArticulo.nombreArticulo}		
								</td>
								<td> 
									${articuloCesta.value}
								</td>
								<td>
									${objArticulo.precioArticulo*articuloCesta.value}<spam>$</spam>
								</td>
								<td>
									<form action="verCesta.jsp">
										<input type="submit" name="add" value="+">
									</form>
								</td>
								<td>
									<form action="verCesta.jsp">
										<input type="submit" name="remove" value="-">
									</form>
								</td>
								<td>
									<form action="verCesta.jsp">
										<input type="submit" name="quit" value="X">
									</form>
								</td>
							</tr>
						</c:when>
					</c:choose>
				</c:forEach>
			</table>
			</form>
			<hr>
			<form action="Tienda.jsp">
			<input type="submit" value="Volver a comprar">
			</form>
			<form action="confirmar.jsp">
			<input type="submit" value="Confirmar Compra">
			</form>
		</div>
	</body>
</html>