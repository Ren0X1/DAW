<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@taglib prefix="h" uri="http://java.sun.com/jsf/html"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" 
"http://www.w3.org/TR/html4/loose.dtd">
<f:view>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
            <f:loadBundle basename="textos" var="txt"/>
            <title><h:outputText value="#{txt.titulo}"/></title>
        </head>
        <body>
            <center>
                <h2><h:outputText value="#{txt.most_anuncio}"/></h2>
            </center>
            <p>
                <h:outputText value="#{txt.most_nombre}"/>: <h:outputText value="#{datos.nombre}"/>
            </p>
            <p>
                <h:outputText value="#{txt.most_motivo}"/>: <h:outputText value="#{datos.motivo}"/>
            </p>
            <p>
                <h:outputText value="#{txt.most_comprension}"/>: <h:outputText value="#{datos.secComprension}"/>
            </p>
            <p>
                <h:outputText value="#{txt.most_ponente}"/>: <h:outputText value="#{datos.ponente} es apropiado."/>
            </p>
            <p>
                <h:outputText value="#{txt.most_razon}"/>: <h:outputText value="#{datos.razon}"/>
            </p>
            <p>
                <h:outputText value="#{txt.most_so}"/>: <h:outputText value="#{datos.sistema}"/>
            </p>
            <p>
                <h:outputText value="#{txt.most_lenguajes}"/>: <h:outputText value="#{datos.secLenguajes}"/>
            </p>
            <p>
                <h:outputText value="#{txt.most_aficiones}"/>: <h:outputText value="#{datos.secAficiiones}"/>
            </p>
            <h:form>
                <p>
                    <h:commandButton value="#{txt.volver}" action="volver"/>
                </p>
            </h:form>
        </body>
    </html>
</f:view>