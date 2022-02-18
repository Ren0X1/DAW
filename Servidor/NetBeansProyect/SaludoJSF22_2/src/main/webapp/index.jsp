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
            <h1><h:outputText value="Bienvenido a la primera aplicación"/></h1>
            <h:form>
                <label>Introducir nombre:</label><h:inputText value="#{saludar.usuario}"/>
                <h:commandButton value="Entrar" action="ir"/>
            </h:form>
        </body>
    </html>
</f:view>
