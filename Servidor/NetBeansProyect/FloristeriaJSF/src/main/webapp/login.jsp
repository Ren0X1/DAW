<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@taglib prefix="h" uri="http://java.sun.com/jsf/html"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<f:view>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
            <title>Login | Floristeria</title>
        </head>
        <body>
            <h1><h:outputText value="Login floristeria"/></h1>
            <h:form>
                <label>DNI: </label> <h:inputText required="" value="#{beanLogin.dni}"/><br>
                <label>CONTRASEÑA: </label> <h:inputSecret required="" value="#{beanLogin.passwd}"/><br>
                <h:commandButton action="#{beanLogin.esUsuario()}" value="Login"/>
            </h:form>
        </body>
    </html>
</f:view>
