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
            <link rel="stylesheet" href="./css/style.css"/>
        </head>
        <body>
            <h1> Sistema de admin</h1>
            <h:form>
                Sintasis: Significado;comentarios<br><br>
                
                Palabra español: <h:inputText value="#{beanAlta.palabraESP}">
                    <f:converter converterId="convertirESP"/>
                </h:inputText><br><br>
                <h2>Ingles</h2><br><br>
                1º <h:inputText value="#{beanAlta.palabraING1}">
                    <f:converter converterId="convertirING"/>
                </h:inputText><br>
                2º <h:inputText value="#{beanAlta.palabraING2}">
                    <f:converter converterId="convertirING"/>
                </h:inputText><br>
                3º <h:inputText value="#{beanAlta.palabraING3}">
                    <f:converter converterId="convertirING"/>
                </h:inputText><br>
                <h:commandButton value="Guardar" actionListener="#{beanAlta.guardar()}"/>
            </h:form>
        </body>
    </html>
</f:view>
