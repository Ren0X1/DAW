<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@taglib prefix="h" uri="http://java.sun.com/jsf/html"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<f:view>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
            <title>Floristería - Login</title>
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
            <h1>Floristería - Login</h1>
            <h:form styleClass="form">
                Usuario: <br> <h:inputText value="#{beanFloristeria.username}"/><br>
                DNI: <br> <h:inputText value="#{beanFloristeria.dni}"/><br>
                Password: <br> <h:inputSecret value="#{beanFloristeria.password}"/><br>
                <h:commandButton value="Acceder" action="#{beanFloristeria.login()}" styleClass="btn"/>
            </h:form>
        </body>
    </html>
</f:view>