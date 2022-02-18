<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@taglib prefix="h" uri="http://java.sun.com/jsf/html"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<f:view>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
            <title>JSP Page</title>
            <style>
                input {
                    display: block;
                }
            </style>
        </head>
        <body>
            <h1><h:outputText value="Validar Formulario!"/></h1>
            <h:form>
                Nombre:
                <h:inputText value="#{beanValidar.nombre}"/>
                Apellidos:
                <h:inputText value="#{beanValidar.apellidos}"/>
                Edad:
                <h:inputText converter="#{Integer}" value="#{beanValidar.edad}"/>
                Nif:
                <h:inputText value="#{beanValidar.nif}">
                    <f:converter converterId="convertirNif"/>
                    <f:validator validatorId="validatorNif"/>
                </h:inputText>
                Email:
                <h:inputText value="#{beanValidar.email}">
                    <f:validator validatorId="validatorEmail"/>
                </h:inputText>
                
                <h:commandButton value="Tramitar" action="comprobar"/>
                
            </h:form>
        </body>
    </html>
</f:view>
