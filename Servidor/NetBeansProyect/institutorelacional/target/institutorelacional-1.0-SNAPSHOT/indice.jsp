<%-- 
    Document   : indice
    Created on : 26 ene. 2022, 17:58:54
    Author     : Christian
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        
        <jsp:include page="opAlumnos"/>
        
        <c:if test="${not empty param.editarAlumno}">
            <jsp:include page="modificarNotasAlumno"/>
            entro
        </c:if>
        <c:if test="${not empty param.verBoletin}">
            <jsp:include page="verBoletinAlumno"/>
        </c:if>
        
        <h2>Consulta asignaturas de un alumno</h2>
        
        <form action="indice.jsp">
            <select name="codalum">
                <c:forEach var="alum" items="${listaAlumnos}">
                    <option value="${alum.nmatricula}">${alum.nombrealumno}</option>
                </c:forEach>
            </select>
            
            <input type="submit" name="op" value="Asignaturas"> 
            
            <c:forEach var="asig" items="${listaAsigs}">
                    ${asig}
            </c:forEach>
            
        </form>
        
        <hr>
        
        <h2>Consulta matriculados a una asignatura</h2>
        
        <form action="indice.jsp">
            <select name="codasig">
                <c:forEach var="asig" items="${listaAsignaturas}">
                    <option value="${asig.codasignatura}">${asig.nombreasig}</option>
                </c:forEach>
            </select>
            
            <input type="submit" name="op" value="Alumnnos">
            
            <c:forEach var="alums" items="${listaAlumns}">
                    ${alums}
            </c:forEach>
            
        </form>
        
        <hr>
        
        <h2>Ver boletín de notas de un alumno</h2>
        
        <form action="indice.jsp">
            <select name="codalum">
                <c:forEach var="alum" items="${listaAlumnos}">
                    <option value="${alum.nmatricula}">${alum.nombrealumno}</option>
                </c:forEach>
            </select>
            
            <input type="submit" name="verBoletin" value="Ver">   
        </form>
        
        <p>${alumnoBoletin.nmatricula}</p>
        <p>${alumnoBoletin.nombrealumno}</p>
        <p>${alumnoBoletin.dni}</p>
        <p>${alumnoBoletin.email}</p>
        
        <form action="indice.jsp">
        <table>
            <tr>
                <th>Asignatura</th>
                <th>Profesor</th>
                <th>Nota 1</th>
                <th>Nota 2</th>
                <th>Nota 3</th>
                <th>Nota Final</th>     
            </tr>
            <c:forEach var="al" items="${alumnoBoletin.matriculaList}" varStatus="it">
                <tr>
                    <td>${al.asignatura.nombreasig}</td>
                    <td>${al.asignatura.dniprofesor.nombreprofesor}</td>
                    <c:choose>
                        <c:when test = "${0 eq colEditable and al.nota1 eq 0}">
                            <td><input type="text" value="${al.nota1}" name="notaAl1"></td>
                        </c:when>
                        <c:otherwise>
                            <td><input type="text" value="${al.nota1}" name="notaAl1" disabled></td>
                        </c:otherwise>
                    </c:choose>
                            
                    <c:choose>
                        <c:when test = "${1 eq colEditable and al.nota2 eq 0}">
                            <td><input type="text" value="${al.nota2}" name="notaAl2"></td>
                        </c:when>
                        <c:otherwise>
                            <td><input type="text" value="${al.nota2}" name="notaAl2" disabled></td>
                        </c:otherwise>
                    </c:choose>
                            
                    <c:choose>
                        <c:when test = "${2 eq colEditable and al.nota3 eq 0}">
                            <td><input type="text" value="${al.nota3}" name="notaAl3"></td>
                        </c:when>
                        <c:otherwise>
                            <td><input type="text" value="${al.nota3}" name="notaAl3" disabled></td>
                        </c:otherwise>
                    </c:choose>     
                    <td>${al.notafinal}</td>
                    <td></td>
                    <td>${it.index}</td>
                <input type="hidden" name="codAlMod" value="${alumnoBoletin.nmatricula}">
                </tr>
            </c:forEach>
            
        </table>
        <input type="submit" name="editarAlumno" value="Editar">
        </form>
        
        col editable = ${colEditable}
        
    </body>
</html>
