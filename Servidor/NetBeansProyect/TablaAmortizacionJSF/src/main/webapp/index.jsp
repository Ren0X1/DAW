<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@taglib prefix="h" uri="http://java.sun.com/jsf/html"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<f:view>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
            <title>Tabla amortizacion</title>
            <style>
                @import url('https://fonts.googleapis.com/css2?family=Poppins:wght@300;400;500;600;700&display=swap');
                *,
                *::before,
                *::after {
                  box-sizing: border-box;
                  margin: 0;
                  padding: 0;
                }
                body {
                  font-family: 'Poppins', sans-serif;
                  display: grid;
                  padding: 100px;
                  color: #4f546c;
                  font-size: 0.9rem;
                  background-color: #f9fbff;;
                }

                table {
                  border-collapse: collapse;
                  box-shadow: 0 5px 10px #e1e5ee;
                  background-color: white;
                  text-align: left;
                  overflow: hidden;
                  width: 50%;
                }
                thead {
                  box-shadow: 0 5px 10px #e1e5ee;
                }

                th {
                  padding: 1rem 2rem;
                  text-transform: uppercase;
                  letter-spacing: 0.1rem;
                  font-size: 0.7rem;
                  font-weight: 900;
                }

                td {
                  padding: 1rem 2rem;
                }
                
                tr:nth-child(even) {
                  background-color: #f4f6fb;
                }
                
                .botonNew {
                    box-shadow: 0px 10px 23px -8px #3e7327;
                    background:linear-gradient(to bottom, #77b55a 5%, #72b352 100%);
                    background-color:#77b55a;
                    border-radius:7px;
                    width: 200px;
                    border:1px solid #4b8f29;
                    display:inline-block;
                    cursor:pointer;
                    color:#ffffff;
                    font-family:Arial;
                    font-size:13px;
                    font-weight:bold;
                    padding:8px 24px;
                    text-decoration:none;
                    text-shadow:0px 0px 0px #5b8a3c;
                }
                
                
                .botonGuardar {
                    box-shadow: 0px 10px 23px -8px #3e7327;
                    background:linear-gradient(to bottom, #77b55a 5%, #72b352 100%);
                    background-color:#77b55a;
                    border-radius:7px;
                    border:1px solid #4b8f29;
                    display:inline-block;
                    cursor:pointer;
                    color:#ffffff;
                    font-family:Arial;
                    font-size:13px;
                    font-weight:bold;
                    padding:8px 24px;
                    text-decoration:none;
                    text-shadow:0px 0px 0px #5b8a3c;
                }
                
                .botonEli {
                    box-shadow: 0px 10px 23px -8px #732727;
                    background:linear-gradient(to bottom, #b55a5a 5%, #b35252 100%);
                    background-color:#b55a5a;
                    border-radius:7px;
                    border:1px solid #8f2929;
                    display:inline-block;
                    cursor:pointer;
                    color:#ffffff;
                    font-family:Arial;
                    font-size:13px;
                    font-weight:bold;
                    padding:8px 24px;
                    text-decoration:none;
                    text-shadow:0px 0px 0px #8a3c3c;
                }

                .botonModi {
                    box-shadow: 0px 10px 23px -8px #276173;
                    background:linear-gradient(to bottom, #5aa0b5 5%, #528cb3 100%);
                    background-color:#5a98b5;
                    border-radius:7px;
                    border:1px solid #296f8f;
                    display:inline-block;
                    cursor:pointer;
                    color:#ffffff;
                    font-family:Arial;
                    font-size:13px;
                    font-weight:bold;
                    padding:8px 24px;
                    text-decoration:none;
                    text-shadow:0px 0px 0px #3c5b8a;
                }
                
                input {
                    font-family: inherit;
                    font-size: inherit;
                    color: inherit;
                    box-sizing: border-box;
                }

                input[type="text"] {
                    margin-left: 10px;
                    display: inline-block;
                    width: 70%;
                    height: 38px;
                    margin-top: 2px;
                    font-weight: 500;
                    background: none;
                    border: 0;
                    border-bottom: 1px solid #d8d8d8;
                }

                input[type="text"]:focus {
                    border-color: #5aa0b5;
                    outline: 0;
                }
                
                [type="radio"]:checked,
                [type="radio"]:not(:checked) {
                    position: absolute;
                    left: -9999px;
                }
                [type="radio"]:checked + label,
                [type="radio"]:not(:checked) + label
                {
                    position: relative;
                    padding-left: 28px;
                    cursor: pointer;
                    line-height: 20px;
                    display: inline-block;
                    color: #666;
                }
                [type="radio"]:checked + label:before,
                [type="radio"]:not(:checked) + label:before {
                    content: '';
                    position: absolute;
                    left: 0;
                    top: 0;
                    width: 18px;
                    height: 18px;
                    border: 1px solid #ddd;
                    border-radius: 100%;
                    background: #fff;
                }
                [type="radio"]:checked + label:after,
                [type="radio"]:not(:checked) + label:after {
                    content: '';
                    width: 12px;
                    height: 12px;
                    background: linear-gradient(to bottom, #5aa0b5 5%, #528cb3 100%);
                    position: absolute;
                    top: 4px;
                    left: 4px;
                    border-radius: 100%;
                    -webkit-transition: all 0.2s ease;
                    transition: all 0.2s ease;
                }
                [type="radio"]:not(:checked) + label:after {
                    opacity: 0;
                    -webkit-transform: scale(0);
                    transform: scale(0);
                }
                [type="radio"]:checked + label:after {
                    opacity: 1;
                    -webkit-transform: scale(1);
                    transform: scale(1);
                }
                
                .select-css {
                    display: block;
                    font-size: 16px;
                    font-family: 'Verdana', sans-serif;
                    font-weight: 400;
                    color: #444;
                    line-height: 1.3;
                    padding: .4em 1.4em .3em .8em;
                    width: 400px;
                    max-width: 100%; 
                    box-sizing: border-box;
                    margin: 20px auto;
                    border: 1px solid #aaa;
                    box-shadow: 0 1px 0 1px rgba(0,0,0,.03);
                    border-radius: .3em;
                    -moz-appearance: none;
                    -webkit-appearance: none;
                    appearance: none;
                    background-color: #fff;
                    background-image: url('data:image/svg+xml;charset=US-ASCII,%3Csvg%20xmlns%3D%22http%3A%2F%2Fwww.w3.org%2F2000%2Fsvg%22%20width%3D%22292.4%22%20height%3D%22292.4%22%3E%3Cpath%20fill%3D%22%23007CB2%22%20d%3D%22M287%2069.4a17.6%2017.6%200%200%200-13-5.4H18.4c-5%200-9.3%201.8-12.9%205.4A17.6%2017.6%200%200%200%200%2082.2c0%205%201.8%209.3%205.4%2012.9l128%20127.9c3.6%203.6%207.8%205.4%2012.8%205.4s9.2-1.8%2012.8-5.4L287%2095c3.5-3.5%205.4-7.8%205.4-12.8%200-5-1.9-9.2-5.5-12.8z%22%2F%3E%3C%2Fsvg%3E'),
                    linear-gradient(to bottom, #ffffff 0%,#f7f7f7 100%);
                    background-repeat: no-repeat, repeat;
                    background-position: right .7em top 50%, 0 0;
                    background-size: .65em auto, 100%;
                }
                .select-css::-ms-expand {
                    display: none;
                }
                .select-css:hover {
                    border-color: #888;
                }
                .select-css:focus {
                    border-color: #aaa;
                    box-shadow: 0 0 1px 3px rgba(59, 153, 252, .7);
                    box-shadow: 0 0 0 3px -moz-mac-focusring;
                    color: #222; 
                    outline: none;
                }
                .select-css option {
                    font-weight:normal;
                }
                
                .select-css option[selected] {
                    color: white;
                    background-color: #5aa0b5;
                }
            </style>
        </head>
        <body>
            <f:loadBundle basename="mensajes" var="msg"/>
            <h1><h:outputText value="#{msg.titulo}"/></h1>
            <h:form>
                <table>
                    <th colspan="4">
                            <h:outputText value="#{msg.portada}"/>
                    </th>
                    <tr>
                        <td>
                            <h:outputText value="#{msg.idioma}"/>
                        </td>
                        <td colspan="4">
                            <h:selectOneMenu styleClass="select-css" value="#{beanAmortizacion.idioma}" onchange="submit()" valueChangeListener="#{beanAmortizacion.cambioIdioma}">
                                <f:selectItems value="#{beanAmortizacion.listaIdiomas}"/>
                            </h:selectOneMenu>
                        </td>
                    </tr>
                    <tr>
                        <td width="55">
                            <h:outputText value="#{msg.cantidad}"/>
                        </td>
                        <td colspan="2">
                            <h:inputText id="capital" value="#{beanAmortizacion.dcapital}" size="11">
                                <f:convertNumber minIntegerDigits="4"/>
                            </h:inputText>
                            <h:message for="capital"/>
                        </td>
                        <td align="center">
                            <h:outputText value="#{msg.frecuencia_pago}"/>
                            <h:selectOneRadio id="select" value="#{beanAmortizacion.speriodicidad}">
                                <f:selectItem itemValue="mes" itemLabel="#{msg.mensual}"/>
                                <f:selectItem itemValue="ano" itemLabel="#{msg.anual}"/>
                            </h:selectOneRadio>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <h:outputText value="#{msg.tae}"/>
                        </td>
                        <td>
                            <h:inputText id="tae" value="#{beanAmortizacion.dtae}" size="4">
                                <f:convertNumber maxFractionDigits="3"/>
                            </h:inputText>
                            %
                            <h:message for="tae"/>
                        </td>
                        <td>
                            <h:outputText value="#{msg.periodos}"/>
                        </td>
                        <td>
                            <h:inputText id="inper" value="#{beanAmortizacion.inper}" size="7">
                                <f:convertNumber minIntegerDigits="1"/>
                            </h:inputText>
                            <h:message for="inper"/>
                        </td>
                    </tr>
                </table>
                <br><br>
                <h:commandButton styleClass="botonModi" value="#{msg.calcular}" actionListener="#{beanAmortizacion.calcular()}"/>
                <h:commandButton style="margin-left: 10px;" styleClass="botonModi" value="#{msg.cuadro}" actionListener="#{beanAmortizacion.pintarCuadro()}"/>
                <table>
                    <tr>
                        <td width="101">
                            <h:outputText value="#{msg.cuota}"/>
                        </td>
                        <td width="115" align="right">
                            <h:outputText value="#{beanAmortizacion.dcuota}" >
                                <f:convertNumber type="currency"/>
                            </h:outputText>
                        </td>
                    </tr>
                </table>
                <h:dataTable rendered="#{beanAmortizacion.tablaFinal!=null}" value="#{beanAmortizacion.tablaFinal}" var="column">
                    
                    <h:column>
                        <f:facet name="header">
                            <h:outputText value="#{msg.indice}"/>
                        </f:facet>
                        <h:outputText value="#{column[3]}">
                            <f:convertNumber type="number"/>
                        </h:outputText>
                    </h:column>
                    
                    <h:column>
                        <f:facet name="header">
                            <h:outputText value="#{msg.interes}"/>
                        </f:facet>
                        <h:outputText value="#{column[0]}">
                            <f:convertNumber type="currency"/>
                        </h:outputText>
                    </h:column>
                    
                    <h:column>
                        <f:facet name="header">
                            <h:outputText value="#{msg.capitalm}"/>
                        </f:facet>
                        <h:outputText value="#{column[1]}">
                            <f:convertNumber type="currency"/>
                        </h:outputText>
                    </h:column>
                    
                    <h:column>
                        <f:facet name="header">
                            <h:outputText value="#{msg.capitalp}"/>
                        </f:facet>
                        <h:outputText value="#{column[2]}">
                            <f:convertNumber type="currency"/>
                        </h:outputText>
                    </h:column>
                </h:dataTable>        
            </h:form>
            <h:messages/>
        </body>
    </html>
</f:view>
