<%-- 
    Document   : Index
    Created on : 26 ene. 2022, 15:42:08
    Author     : Javier
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:if test="${not empty param.operacion}">
    <jsp:include page="GestionBD"/>
</c:if>
<jsp:include page="ConsultasBD"/>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>App Instituto</title>
    </head>
    <body>
        <h1>App Instituto</h1>
        <form action="Index.jsp">
            <select name="alumno">
                <c:forEach var="alumno" items="${requestScope.alumnos}">
                    ${alumno}
                    <option value="${alumno.nmatricula}">
                        ${alumno.nombrealumno}
                    </option>
                </c:forEach>
            </select>
            <input type="submit" value="Borrar Alumno">
            <input type="hidden" name="operacion" value="borrarAlumno">
        </form><br>
        <form action="Index.jsp">
            <select name="alumno">
                <c:forEach var="alumno" items="${requestScope.alumnos}">
                    <option value="${alumno.nmatricula}">
                        ${alumno.nombrealumno}
                    </option>
                </c:forEach>
            </select>
            <select name="asignatura">
                <c:forEach var="asignatura" items="${requestScope.asignaturas}">
                    <option value="${asignatura.codasignatura}">
                        ${asignatura.nombreasignatura}
                    </option>
                </c:forEach>
            </select><br><br>
            Nota 1: <input type="number" name="nota1"><br>
            Nota 2: <input type="number" name="nota2"><br>
            Nota 3: <input type="number" name="nota3"><br>
            <input type="submit" value="Alta Matrícula">
            <input type="hidden" name="operacion" value="altaMatricula">
        </form>
    </body>
</html>
