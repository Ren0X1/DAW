<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@taglib prefix="h" uri="http://java.sun.com/jsf/html"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<f:view>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
            <title>Jugar</title>
            <style>
                .separado {
                    margin-left: 150px;
                }
                
                .separado img {
                    height: 150px;
                }
            </style>
            <link rel="stylesheet" href="./css/style.css"/>
        </head>
        <body>
            <h:panelGrid columns="3">
                <h:panelGroup layout="block" id="container">
                    <h1><h:outputText value="#{beanJugar.nombreUsu()}"/></h1>
                    <h1><h:outputText value="Bienvenido al juego de las Palabras"/></h1>
                </h:panelGroup>
                <h:panelGroup layout="block" id="container2" styleClass="separado">
                    <h2>Llevas superado: <h:outputText value="#{beanJugar.superados()}"/> niveles</h2>
                    <h2>Estas jugando el nivel: <h:outputText value="#{beanJugar.superados()+1}"/></h2>
                </h:panelGroup>
                <h:panelGroup layout="block" id="container3" styleClass="separado">
                    <h:graphicImage value="#{beanJugar.foto()}"/>
                    <h:form>
                        <h:commandButton value="Modificar Perfil" action="#{beanJugar.irfoto()}"/>
                    </h:form>
                </h:panelGroup>
            </h:panelGrid>
            <h:form>
                Color: <h:selectOneMenu valueChangeListener="#{beanJugar.cambioColor}" onchange="submit()" value="#{beanJugar.color}">
                    <f:selectItem itemValue="skyblue" itemLabel="Celeste"/>
                    <f:selectItem itemValue="red" itemLabel="Rojo"/>
                </h:selectOneMenu>
                <h:dataTable styleClass="tabla" value="#{beanJugar.lista()}" var="pal" binding="#{beanJugar.tablaPrincipal}">
                    <h:column>
                        <f:facet name="header">
                            <h:outputText value="Palabra Español"/>
                        </f:facet>
                        <h:outputText value="#{pal.palabraESP}"/>
                    </h:column>
                    <h:column>
                        <f:facet name="header">
                            <h:outputText value="En ingles"/>
                        </f:facet>
                        <h:inputText value="#{pal.palabraING}"/>
                    </h:column>
                    <h:column rendered="#{beanJugar.mostrarResul}">
                        <f:facet name="header">
                            <h:outputText value="Resultado"/>
                        </f:facet>
                        <h:graphicImage value="./img/ok.png" rendered="#{pal.ok}"/>
                        <h:graphicImage value="./img/nocheck.png" rendered="#{not pal.ok}"/>
                    </h:column>
                </h:dataTable>
                <h:commandButton value="Comprobar" actionListener="#{beanJugar.showResul()}" rendered="#{not beanJugar.mostrarResultados}"/>
                <h:panelGroup layout="block" id="container4" rendered="#{beanJugar.mostrarResultados}">
                    <h2>Has conseguido <h:outputText value="#{beanJugar.puntuacion}"/> puntos.</h2>
                    <h2><h:outputText value="#{beanJugar.mensaje()}"/></h2>
                </h:panelGroup>
            </h:form>
            <h:form rendered="#{beanJugar.mostrarResultados}">
                <h:commandButton value="Continuar Jugando" actionListener="#{beanJugar.addPun()}"/>
                <h:commandButton value="Finalizar partida" action="#{beanJugar.irfin()}"/>
            </h:form>
        </body>
    </html>
</f:view>
