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
                <h2><h:outputText value="#{txt.anuncio}"/></h2>
            </center>
            <h:form>
                <p>
                    <h:outputText value="#{txt.nombre}"/> <h:inputText value="#{datos.nombre}"/>
                </p><br>
                    <h:outputText value="#{txt.motivo}"/>
                    <h:selectOneMenu value="#{datos.motivo}">
                        <f:selectItems value="#{datos.listaMotivos}"/>
                    </h:selectOneMenu><br>
                <p>
                    <h:outputText value="#{txt.comprension}"/>
                    <h:selectManyCheckbox value="#{datos.comprension}">
                        <f:selectItems value="#{datos.listaComprension}"/>
                    </h:selectManyCheckbox>
                </p><br>
                <p>
                    <h:outputText value="#{txt.ponente}"/>
                    <h:selectOneRadio value="#{datos.ponente}">
                        <f:selectItem itemValue="si" itemLabel="SI"/>
                        <f:selectItem itemValue="no" itemLabel="NO"/>
                    </h:selectOneRadio>
                <br>
                    <h:outputText value="#{txt.razon}"/><br>
                    <h:inputTextarea value="#{datos.razon}"/>
                </p>
                <p>
                    <h:outputText value="#{txt.so}"/>
                    <h:selectOneListbox value="#{datos.sistema}">
                        <f:selectItems value="#{datos.listaSistemas}"/>
                    </h:selectOneListbox>
                </p>
                <p>
                    <h:outputText value="#{txt.lenguajes} "/>
                    <h:selectManyListbox value="#{datos.lenguajes}">
                        <f:selectItems value="#{datos.listaLenguajes}"/>
                    </h:selectManyListbox>
                </p>

                <p>
                    <h:outputText value="#{txt.aficiones} "/>
                    <h:selectManyListbox value="#{datos.aficiones}">
                        <f:selectItem itemValue="musica" itemLabel="Música"/>
                        <f:selectItem itemValue="cine" itemLabel="Cine"/>
                        <f:selectItem itemValue="pesca" itemLabel="Pesca"/>
                        <f:selectItem itemValue="deporte" itemLabel="Deporte"/>
                        <f:selectItem itemValue="lectura" itemLabel="Lectura"/>
                    </h:selectManyListbox>
                </p><br>
                <p>
                    <h:commandButton value="#{txt.aceptar}" action="mostrar"/>
                    <h:commandButton value="#{txt.reset}" type="reset"/>
                </p>
            </h:form>
        </body>
    </html>
</f:view>
