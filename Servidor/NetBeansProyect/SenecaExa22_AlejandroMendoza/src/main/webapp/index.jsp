<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@taglib prefix="h" uri="http://java.sun.com/jsf/html"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<f:view>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
            <title>Aplicacion | Seneca</title>
        </head>
        <body>
            <h1><h:outputText value="#{beanIndex.usuario} Bienvenido a Seneca 2DAW - Gestor Academico"/></h1><br><br>
            <h:form>
                <h:selectOneRadio onchange="submit()" value="#{beanIndex.eva}">
                    <f:selectItem itemValue="1" itemLabel="1ª Evaluacion"/>
                    <f:selectItem itemValue="2" itemLabel="2ª Evaluacion"/>
                    <f:selectItem itemValue="3" itemLabel="3ª Evaluacion"/>
                </h:selectOneRadio>
                Curso: <h:selectOneMenu value="#{beanIndex.curso}" onchange="submit()">
                    <f:selectItem noSelectionOption="true" itemDisabled="true" itemValue="-1" itemLabel="Curso"/>
                    <f:selectItems value="#{beanIndex.listaCursos}"/>
                </h:selectOneMenu>
                Asignatura: <h:selectOneMenu valueChangeListener="#{beanIndex.mostrar()}" value="#{beanIndex.asignatura}" onchange="submit()" rendered="#{beanIndex.listaAsig!=null}">
                    <f:selectItem noSelectionOption="true" itemDisabled="true" itemValue="-1" itemLabel="Asignatura"/>
                    <f:selectItems value="#{beanIndex.listaAsig}"/>
                </h:selectOneMenu><br><br>
                <h:dataTable border="1" value="#{beanIndex.listaAlumnos()}" var="alum" binding="#{beanIndex.tablaNotas}" rendered="#{beanIndex.mostrar}">
                    <h:column>
                        <f:facet name="header">
                            <h:outputText value="Alumno"/>
                        </f:facet>
                        <h:outputText value="#{alum.nomAlumno}" rendered="#{alum.idASig==beanIndex.asignatura}"/>
                    </h:column>
                    <h:column rendered="#{beanIndex.eva==1}">
                        <f:facet name="header">
                            <h:outputText value="Nota 1"/>
                        </f:facet>
                        <h:selectOneMenu valueChangeListener="#{beanIndex.cambiarNota(alum)}" value="#{alum.nota3}" rendered="#{alum.idASig==beanIndex.asignatura}">
                            <f:selectItem itemValue="#{alum.nota3}" itemLabel="#{alum.nota3}"/>
                            <f:selectItem itemValue="3" itemLabel="3"/>
                            <f:selectItem itemValue="5" itemLabel="5"/>
                            <f:selectItem itemValue="8" itemLabel="8"/>
                        </h:selectOneMenu>
                    </h:column>
                    <h:column rendered="#{beanIndex.eva==2}">
                        <f:facet name="header">
                            <h:outputText value="Nota 2"/>
                        </f:facet>
                        <h:selectOneMenu valueChangeListener="#{beanIndex.cambiarNota(alum)}" value="#{alum.nota3}" rendered="#{alum.idASig==beanIndex.asignatura}">
                            <f:selectItem itemValue="#{alum.nota3}" itemLabel="#{alum.nota3}"/>
                            <f:selectItem itemValue="3" itemLabel="3"/>
                            <f:selectItem itemValue="5" itemLabel="5"/>
                            <f:selectItem itemValue="8" itemLabel="8"/>
                        </h:selectOneMenu>
                    </h:column>
                    <h:column rendered="#{beanIndex.eva==3}">
                        <f:facet name="header">
                            <h:outputText value="Nota 3"/>
                        </f:facet>
                        <h:selectOneMenu valueChangeListener="#{beanIndex.cambiarNota(alum)}" value="#{alum.nota3}" rendered="#{alum.idASig==beanIndex.asignatura}">
                            <f:selectItem itemValue="#{alum.nota3}" itemLabel="#{alum.nota3}"/>
                            <f:selectItem itemValue="3" itemLabel="3"/>
                            <f:selectItem itemValue="5" itemLabel="5"/>
                            <f:selectItem itemValue="8" itemLabel="8"/>
                        </h:selectOneMenu>
                    </h:column>
                </h:dataTable>
                <h:commandButton actionListener="#{beanIndex.tablarDos()}" image="./img/ok.png" rendered="#{beanIndex.mostrar}"/>
            </h:form>
            <h:form>
                <h:dataTable border="1" value="#{beanIndex.listaAlumnos()}" var="alum2" binding="#{beanIndex.tablaNotas2}" rendered="#{beanIndex.mostrar2}">
                    <h:column>
                        <h:commandButton value="Datos" actionListener="#{beanIndex.datosAlum()}" rendered="#{alum2.idASig==beanIndex.asignatura}"/>
                    </h:column>
                    <h:column>
                        <f:facet name="header">
                            <h:outputText value="Alumno"/>
                        </f:facet>
                        <h:outputText value="#{alum2.nomAlumno}" rendered="#{alum2.idASig==beanIndex.asignatura}"/>
                    </h:column>
                    <h:column>
                        <f:facet name="header">
                            <h:outputText value="Nota 1"/>
                        </f:facet>
                        <h:outputText value="#{alum2.nota1}" rendered="#{alum2.idASig==beanIndex.asignatura}"/>
                    </h:column>
                    <h:column>
                        <f:facet name="header">
                            <h:outputText value="Nota 2"/>
                        </f:facet>
                    <h:outputText value="#{alum2.nota2}" rendered="#{alum2.idASig==beanIndex.asignatura}"/>
                    </h:column>
                    <h:column>
                        <f:facet name="header">
                            <h:outputText value="Nota 3"/>
                        </f:facet>
                    <h:outputText value="#{alum2.nota3}" rendered="#{alum2.idASig==beanIndex.asignatura}"/>
                    </h:column>
                </h:dataTable>
                <h:commandButton actionListener="#{beanIndex.tablarDos()}" image="./img/guardar.png" rendered="#{beanIndex.mostrar2}"/>
            </h:form>Datos Alumnos;
                <h:dataTable value="#{beanIndex.listaAlum}" var="alum3" rendered="#{beanIndex.mostrar3}">
                    <h:column>
                        <f:facet name="header">
                            <h:outputText value="ID"/>
                        </f:facet>listaAlum
                        <h:outputText value="#{alum3.id}"/>
                    </h:column>
                    <h:column>
                        <f:facet name="header">
                            <h:outputText value="DNI"/>
                        </f:facet>
                        <h:outputText value="#{alum3.dni}"/>
                    </h:column>
                    <h:column>
                        <f:facet name="header">
                            <h:outputText value="Nota 2"/>
                        </f:facet>
                        <h:outputText value="#{alum3.id}"/>
                    </h:column>
                    <h:column>
                        <f:facet name="header">
                            <h:outputText value="Nota 3"/>
                        </f:facet>
                        <h:outputText value="#{alum3.nomApell}"/>
                    </h:column>
                </h:dataTable>
            <h:form>
                <h:commandButton value="Volver a evaluar" action="volver"/>
            </h:form>
            <h:form>
                <h:commandButton value="Alta de alumnos" action="alta"/>
            </h:form>
                <h3>No guarda la nota en la tabla de respaldo</h3>
        </body>
    </html>
</f:view>
