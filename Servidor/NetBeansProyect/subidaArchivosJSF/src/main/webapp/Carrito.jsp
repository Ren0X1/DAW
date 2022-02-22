<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@taglib prefix="h" uri="http://java.sun.com/jsf/html"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<f:view>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
            <title>Floristería - Carrito</title>
            <style>
                h1{text-align: center;}
                table{
                    border-collapse: collapse;
                    border:1px solid black;
                    margin: auto;
                }
                table td,
                table th{
                    border:1px solid black;
                    padding: 10px;
                }
                .volver{
                    margin-top: 5%;
                    text-align: center;
                }
                .btn{
                    background: lightpink;
                    border: 2px solid black;
                    border-radius: 10px;
                    padding: 10px;
                    cursor: pointer;
                    margin-bottom: 10px;
                }
            </style>
        </head>
        <body>
            <h1>Carrito</h1>
            <h:form>
                <h:dataTable value="#{beanFloristeria.cestaCliente}" var="carrito" binding="#{beanFloristeria.respaldoTablaCesta}">
                    <h:column>
                        <f:facet name="header">
                            <h:outputText value="Imagen"/>
                        </f:facet>
                        <img src="flores/<h:outputText value="#{carrito.imagen}"/>" width="100px;">
                    </h:column>
                    <h:column>
                        <f:facet name="header">
                            <h:outputText value="Nombre común"/>
                        </f:facet>
                        <h:outputText value="#{carrito.nombreComun}"/>
                    </h:column>
                    <h:column>
                        <f:facet name="header">
                            <h:outputText value="Nombre científico"/>
                        </f:facet>
                        <h:outputText value="#{carrito.nombreCientifico}"/>
                    </h:column>
                    <h:column>
                        <f:facet name="header">
                            <h:outputText value="Cacterísticas Luz y Agua"/>
                        </f:facet>
                        <h:outputText value="#{carrito.tipoLuz} - #{carrito.agua}"/>
                    </h:column>
                    <h:column>
                        <f:facet name="header">
                            <h:outputText value="Precio"/>
                        </f:facet>
                        <h:outputText value="#{carrito.precio}"/>
                    </h:column>
                    <h:column>
                        <f:facet name="header">
                            <h:outputText value="Unidades"/>
                        </f:facet>
                        <h:outputText value="#{carrito.unidades}"/>
                    </h:column>
                    <h:column>
                        <f:facet name="header">
                            <h:outputText value="Total"/>
                        </f:facet>
                        <h:outputText value="#{carrito.totalprecio}">
                            <f:convertNumber type="currency"/>
                        </h:outputText>
                    </h:column>
                    <h:column>
                        <f:facet name="header">
                            <h:outputText value="Acciones"/>
                        </f:facet>
                        <h:commandButton image="iconos/mas.png" actionListener="#{beanFloristeria.aumentarCompra()}"/>
                        <h:commandButton image="iconos/menos.png" actionListener="#{beanFloristeria.reducirCompra()}"/>
                        <h:commandButton image="iconos/quitar.png" actionListener="#{beanFloristeria.eliminarCompra()}"/>
                    </h:column>
                </h:dataTable>
            </h:form>
            <h:form styleClass="volver">
                <h:commandButton value="Volver a la tienda" action="volver" styleClass="btn"/>
            </h:form>
        </body>
    </html>
</f:view>