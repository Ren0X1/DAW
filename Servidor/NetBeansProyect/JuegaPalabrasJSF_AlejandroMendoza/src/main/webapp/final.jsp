<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@taglib prefix="h" uri="http://java.sun.com/jsf/html"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<f:view>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
            <title>Final</title>
            <link rel="stylesheet" href="./css/style.css"/>
        </head>
        <body>
            <h1><h:outputText value="#{beanFinal.nombreUsu()}"/></h1>
            <h1>Puntuacion: <h:outputText value="#{beanFinal.puntuacion}"/></h1>
            <h:graphicImage value="./img/felicitaciones.gif" rendered="#{beanFinal.estado}"/>
            <h:graphicImage value="./img/sorry.gif" rendered="#{not beanFinal.estado}"/>
            <h:form>
                <h:commandButton value="otra partida" actionListener="#{beanFinal.terminar()}"/>
            </h:form>
            <h:form rendered="#{beanFinal.estado}">
                <h:commandButton value="nueva partida" actionListener="#{beanFinal.terminar2()}" />
            </h:form>
        </body>
    </html>
</f:view>
