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
            <h:form enctype='multipart/form-data'>
                <h:inputFile value="#{beanFoto.foto}"/>
                <h:commandButton value="Subir foto" actionListener="#{beanFoto.subir()}"/>
            </h:form>
            <h:form>
                <br><br>
                <h:commandButton value="Ir Jugar" action="#{beanFoto.ir()}"/>
            </h:form>
        </body>
    </html>
</f:view>
