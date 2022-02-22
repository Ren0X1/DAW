<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@taglib prefix="h" uri="http://java.sun.com/jsf/html"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<f:view>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
            <title>Floristería JSF</title>
            <style>
                .previo{
                    transform: rotate(180deg);
                }
                h1,h2{text-align: center;}
                form{text-align: center;}
                .inline{display: inline-block;}
                .imagen{width: 400px;}
                .galeria{
                    width: 40%;
                    margin:auto;
                    display: flex;
                    justify-content: space-between;
                    align-items: center;
                }
                .info{
                    text-align: center;
                    font-weight: bold;
                }
                .acciones{
                    width: 50%;
                    margin:10% auto;
                    display: flex;
                    justify-content: space-between;
                    align-items: center;
                }
                .infoFlor{
                    width: 50%;
                    margin: auto;      
                }
                table{
                    border-collapse: collapse;
                    border: 1px solid black;
                    margin: auto;  
                    display: none;
                }
                table td{border: 1px solid black;padding: 10px;}
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
            <h1>Floristería JSF</h1>
            <h2>Luz-Agua</h2> 
            <h:form>
                <h:selectOneMenu onchange="submit()" valueChangeListener="#{beanFloristeria.filtradoFlores}">
                    <f:selectItems value="#{beanFloristeria.listadoCaracteristicas}"/>
                </h:selectOneMenu>
            </h:form>
            <p class="info">Categoría: <h:outputText value="#{beanFloristeria.categoriaActual}"/></p>
            <p class="info">Ejemplares: <h:outputText value="#{beanFloristeria.ejemplares}"/></p>
            <div class="galeria">
                <h:form styleClass="inline">
                    <h:commandButton image="iconos/next.png" actionListener="#{beanFloristeria.florAnterior()}" styleClass="previo"/>
                </h:form>
                <h:graphicImage value="flores/#{beanFloristeria.florActual.imagen}" rendered="#{beanFloristeria.ejemplares > 0}" styleClass="inline imagen" binding="#{beanFloristeria.respaldoImagen}"/>
                <h:form styleClass="inline">
                    <h:commandButton image="iconos/next.png" actionListener="#{beanFloristeria.siguienteFlor()}"/>
                </h:form>
            </div>
            <div class="acciones">
                <h:form styleClass="inline">
                    <h:commandButton image="iconos/reducir.png" actionListener="#{beanFloristeria.reducirImagen()}" binding="#{beanFloristeria.btnReducir}"/>
                </h:form>
                <h:form styleClass="inline">
                    <h:commandButton image="iconos/restaurar.png" actionListener="#{beanFloristeria.restaurarImagen()}" binding="#{beanFloristeria.btnrestaurar}"/>
                </h:form>
                <h:form styleClass="inline">
                    <h:commandButton image="iconos/aumentar.png" actionListener="#{beanFloristeria.aumentarImagen()}" binding="#{beanFloristeria.btnAumentar}"/>
                </h:form>
                <h:form styleClass="inline">
                    <h:commandButton image="iconos/delete.png" actionListener="#{beanFloristeria.borrarImagen()}"/>
                </h:form>
                <h:form styleClass="inline">
                    <h:commandButton image="iconos/comprar.png" actionListener="#{beanFloristeria.comprar()}"/>
                </h:form>
                <h:form styleClass="inline">
                    <h:commandButton image="iconos/info.png" actionListener="#{beanFloristeria.infoImagen()}"/>
                </h:form>
            </div>
            <h:form>
                <h:commandButton value="Alta Flor" action="alta" styleClass="btn"/>
            </h:form>
            <h:form>
                <h:commandButton value="Ir a carrito" action="#{beanFloristeria.irCarrito()}" styleClass="btn"/>
            </h:form>
            <div class="infoFlor">
                <h:form>
                    <h:dataTable value="#{beanFloristeria.florActual}" var="flor" styleClass="tabla" binding="#{beanFloristeria.respaldoTabla}">
                        <h:column>
                            <h:outputText value="#{flor.nombreComun}"/>
                        </h:column>
                        <h:column>
                            <h:outputText value="#{flor.nombreCientifico}"/>
                        </h:column>
                        <h:column>
                            <h:outputText value="#{flor.precio}">
                                <f:convertNumber type="currency"/>
                            </h:outputText>
                        </h:column>
                        <h:column>
                            <h:commandButton image="iconos/close.png" actionListener="#{beanFloristeria.cerrarInfo()}"/>
                        </h:column>
                    </h:dataTable> 
                </h:form>
            </div>
        </body>
    </html>
    <h:messages/>
</f:view>