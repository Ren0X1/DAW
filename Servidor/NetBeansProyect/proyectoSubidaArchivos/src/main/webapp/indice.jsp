<%-- 
    Document   : indice
    Created on : 9 ene. 2022, 14:03:17
    Author     : jaxft
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/xml" prefix="x" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <style>
            *{
                padding:2px;
                margin:5px;
            }
            html{
                margin:0;
                padding:0;
                background-image:-webkit-linear-gradient(left,blue,yellow);
            }
            h1{
                text-align:center;
            }
            table,#cristal{
                width:65%;
                border-radius:5px;
                box-shadow: inset 0 0 2000px rgba(255, 100, 0);
                margin:0 auto;
            }
            #cristal{
                filter:blur(10px);
            }
            td{
                text-align:center;
                padding:10px;
                font-weight: bold;
                border:1px solid orange;
            }
            td input[type="submit"]{
                font-weight: bold;
                background-color:transparent;
                color:blue;
                text-decoration:underline;
                border:0px;
                cursor:pointer;
            }
            #volver{
                margin-left:48%;
                text-shadow:2px 2px 2px gray;
                box-shadow:0 1px 0 white inset,2px 2px 3px 2px gray;
                background-image:-webkit-linear-gradient(left,hsla(196, 71%, 55%, 0.8) 20%,hsla(196, 66%, 48%, 0.9) 60%);
                background-image: -moz-linear-gradient(left,hsla(196, 71%, 55%, 0.8) 20%,hsla(196, 66%, 48%, 0.9) 60%);
                background-image: -ms-linear-gradient(left,hsla(196, 71%, 55%, 0.8) 20%,hsla(196, 66%, 48%, 0.9) 60%);
                background-image: -o-linear-gradient(left,hsla(196, 71%, 55%, 0.8) 20%,hsla(196, 66%, 48%, 0.9) 60%);
                background-image: linear-gradient(left,hsla(196, 71%, 55%, 0.8) 20%,hsla(196, 66%, 48%, 0.9) 60%); 
            }
            #volver:hover{
                cursor:pointer;
                color:black;
                background-image:-webkit-linear-gradient(top left,hsla(185, 100%, 50%, 0.8) 10%,hsla(109, 100%, 50%, 0.7) 50%,hsla(63, 100%, 50%, 0.7) 90%); 
                background-image: -moz-linear-gradient(top left,hsla(185, 100%, 50%, 0.8) 10%,hsla(109, 100%, 50%, 0.7) 50%,hsla(63, 100%, 50%, 0.7) 90%); 
                background-image: -ms-linear-gradient(top left,hsla(185, 100%, 50%, 0.8) 10%,hsla(109, 100%, 50%, 0.7) 50%,hsla(63, 100%, 50%, 0.7) 90%); 
                background-image: -o-linear-gradient(top left,hsla(185, 100%, 50%, 0.8) 10%,hsla(109, 100%, 50%, 0.7) 50%,hsla(63, 100%, 50%, 0.7) 90%); 
                background-image: linear-gradient(top left,hsla(185, 100%, 50%, 0.8) 10%,hsla(109, 100%, 50%, 0.7) 50%,hsla(63, 100%, 50%, 0.7) 90%); 
            }
            #volver:active{
                background-color:#2f0fff;
                background-image: -webkit-radial-gradient(50% 50%, ellipse cover, #2f0fff, #000000 100%);
                color:white;
                box-shadow:0 0 0 0 black,-1px -1px 2px 1px gray inset;
            }
        </style>
    </head>
    <jsp:include page="/datosCarpeta"/>
    <c:if test="${empty param.ruta}">
        <script>alert("Se han subido los archivos a la ruta C:\\subidaArchivos")</script>
    </c:if>
    <c:if test="${not empty param.ruta}">
        <jsp:include page="/abreArchivo"/>
    </c:if>
    <body>
        
        <h1>Indice de la carpeta subidaArchivos</h1>
        <div id="cristal">
            
        </div>
        <c:choose>
            <c:when test="${param.no ne null}">
                <h1>No se ha encontrado la ruta especificada. Por favor, vuelva a inicio para seguir.</h1>
            </c:when>
            <c:otherwise>
                <table>
                    <c:forEach var="nombreArch" items="${requestScope.listaArch}">
                        <form action="indice.jsp">
                            <tr><td><input type="submit" value="${nombreArch}"/></td></tr>
                            <input type="hidden" name="ruta" value="C:/subidaArchivos/${nombreArch}"/>
                        </form>
                    </c:forEach>
                </table>
            </c:otherwise>
        </c:choose>
        <form action="index.jsp">
            <input type="submit" id="volver" value="Volver a inicio"/>
        </form>
    </body>
</html>
