<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@taglib prefix="h" uri="http://java.sun.com/jsf/html"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<f:view>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
            <title>Floristería - Alta</title>
            <style>
                h1{text-align: center;}
                .btn{
                    background: lightpink;
                    border: 2px solid black;
                    border-radius: 10px;
                    padding: 10px;
                    cursor: pointer;
                    margin-bottom: 10px;
                    margin-top: 10px;
                }
                .form{
                    text-align: center;
                }
            </style>
        </head>
        <body>
            <h1>Alta de Flores</h1>
            <h:form styleClass="form">
                Nombre común: <br> <h:inputText value="#{beanFloristeria.nombreComunAlta}"/><br>
                Nombre científico: <br> <h:inputText value="#{beanFloristeria.nombreCientificoAlta}"/><br>
                Tipo Luz y Agua: <br>
                <h:selectOneMenu value="#{beanFloristeria.caracteristicasAlta}">
                    <f:selectItems value="#{beanFloristeria.listadoCaracteristicas}"/>
                </h:selectOneMenu><br>
                Precio: <br> <h:inputText value="#{beanFloristeria.precioAlta}"/><br>
                Imagen: <br> <h:inputFile/><br>
                <h:commandButton value="Alta" action="#{beanFloristeria.alta()}" styleClass="btn"/>
            </h:form>
            <h:form styleClass="form">
                <h:commandButton value="Volver a la tienda" action="volver" styleClass="btn"/>
            </h:form>
        </body>
    </html>
</f:view>
