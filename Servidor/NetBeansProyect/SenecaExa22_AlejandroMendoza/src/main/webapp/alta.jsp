<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@taglib prefix="h" uri="http://java.sun.com/jsf/html"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<f:view>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
            <title>Alta | Seneca</title>
        </head>
        <body>
            <h1><h:outputText value="Alta de alumnos!"/></h1>
        </body>
        <h:form>
            DNI: <h:inputText value="#{beanAlta.DNI}">
                <f:converter converterId="convertirNif"/>
                <f:validator validatorId="validatorNif"/>
            </h:inputText><br><br>
            Nombre Apellidos: <h:inputText value="#{beanAlta.nombreape}"></h:inputText><br><br>
            Localidad: <h:inputText value="#{beanAlta.localidad}"></h:inputText><br><br>
            Curso: <h:selectOneMenu value="#{beanAlta.curso}">
                <f:selectItems value="#{beanAlta.listaCurso}"/>
            </h:selectOneMenu><br><br>
            Imagen: <h:inputText value="#{beanAlta.imagen}"></h:inputText><br><br>
            <h:commandButton value="Guardar Alumno" actionListener="#{beanAlta.guardarAlumno()}"/>
        </h:form>
    </html>
</f:view>
