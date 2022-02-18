<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="pojos.*"%>
<%@page import="java.util.*"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="x" uri="http://java.sun.com/jsp/jstl/xml"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<!doctype html>
<html>
	<head>
		<link rel='stylesheet' href='styles.css'>
	</head>
	
	<jsp:useBean id="cursoCiclo" scope="application" class="pojos.Curso">
		<jsp:setProperty name="cursoCiclo" property="nombreCurso" value='<%=application.getInitParameter("curso")%>'/>
 	</jsp:useBean>
	
	<jsp:useBean id="alumno" scope="request" class="pojos.Alumno">
		<jsp:setProperty name="alumno" property="*" />
	</jsp:useBean>

	<body>
		<div class="container">
			<h1>Alta de Alumnos</h1>
				<form action="index.jsp" method="POST">
					<label>Nif:</label> <input type="number" name="nif" value="1"><br>
					<label>Nombre: </label><input type="text" name="nombre" value="Alejandro"><br>
					<label>Primer Apellido: </label><input type="text" name="apellido1" value="ap1"><br>
					<label>Segundo Apellido: </label><input type="text" name="apellido2" value="ap2"><br>
					<label>Edad: </label><input type="number" name="edad" value="5"><br>
					<label>Población: </label><input type="text" name="poblacion" value="Cadiz"><br>
					<label>CP: </label><input type="text" name="cp" value="11690"><br>
					<label>N Hermanos: </label><input type="number" name="numHermanos" value="1"><br>
					<label>Curso: </label><input type="text" name="curso" disabled value='<%=application.getInitParameter("curso")%>'><br>
					<input id="input-submit" type="submit" value="Dar de alta">
				</form>
		</div>
		<%
			if(alumno.getNif()!=-1) {
				cursoCiclo.addAlumno(alumno);
			}
		%>
		<% 
		if (cursoCiclo.getListaAlum().size()!=0) {%>
			<p id="numAlum">Nº Alumnos: <jsp:getProperty name="cursoCiclo" property="numAlum"/></p>
			<p id="numAlum">Curso: <jsp:getProperty name="cursoCiclo" property="nombreCurso"/></p>
			<div class="container">
				<table>
					<tr>
						<th>Nombre</th>
						<th>Apellidos</th>
						<th>Edad</th>
						<th>Curso</th>
						<th>Número de Hermanos</th>
					</tr>
					<%
						Iterator it = cursoCiclo.getListaAlum().entrySet().iterator();
						while (it.hasNext()) {
							Map.Entry<Integer, Alumno> pair = (Map.Entry<Integer, Alumno>)it.next();
							Object obj = pair.getKey();
							Alumno alum = cursoCiclo.getListaAlum().get(obj);%>
							<tr>
								<td><%=alum.getNombre()%></td>
								<td><%=alum.getApellido1() + " " + alum.getApellido2()%></td>
								<td><%=alum.getEdad()%></td>
								<td><%=alum.getCurso()%></td>
								<td><%=alum.getNumHermanos()%></td>
							</tr>
						<%}
					%>
				</table>
			</div>
		<%}%>
	</body>
</html>