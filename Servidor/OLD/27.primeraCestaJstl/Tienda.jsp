<%@page contentType="text/html" pageEncoding="UTF-8" import="pojos.*" import="javax.servlet.jsp.*" import="java.util.*"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<!doctype html>
<html>
	<head>
		<link rel='stylesheet' href='styles.css'>
	</head>
	
	
	<jsp:useBean id="miTienda" scope="application" class="pojos.Tienda">
		<jsp:setProperty name="miTienda" property="nombreTienda" value='PokeTienda' />
		<jsp:setProperty name="miTienda" property="listaArticulos" value='${applicationScope.mapaArticulos}' />	
	</jsp:useBean>
	
	<jsp:useBean id="miCesta" scope="session" class="pojos.Cesta">
		<jsp:setProperty name="miCesta" property="*" />
		<jsp:setProperty name="miCesta" property="nombreUsuario" value='${param.nombreUsuario}' />
 	</jsp:useBean>
	
	
	
	
	
	<jsp:include page='/addArt'/>
	
	
	<body>
	
	Bienvenid@ ${miCesta.nombreUsuario}
	a la ${miTienda.nombreTienda}
	
	
	
	<div class="container">
			<form action="Tienda.jsp">
			<table>
				
				<c:forEach var="articulo" items="${miTienda.listaArticulos}">
				<tr>
					<td> 
						<input type='checkbox' name='articulosSeleccionados' value='${articulo.value.codigoArticulo}'>
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
			
			<input type="submit" value="Añadir artículos">
			
			</form>
			
			<form action="verCesta.jsp">
			
			<input type="submit" value="Ver cesta">
			
			</form>
			
		</div>
	</body>
</html>