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
        </head>
        <body>
            <h1><h:outputText value="Resultado!"/></h1>
            <h:outputText value="#{beanValidar.nombre}"/><br><br>
            <h:outputText value="#{beanValidar.apellidos}"/><br><br>
            <h:outputText value="#{beanValidar.edad}"/><br><br>
            <h:outputText value="#{beanValidar.nif}"/><br><br>
            <h:outputText value="#{beanValidar.email}"/><br><br>
        </body>
    </html>
</f:view>
