<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@taglib prefix="h" uri="http://java.sun.com/jsf/html"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<f:view>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
            <title>JSP Page</title>
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
                  width: 30%;
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
                
                input[type="submit"] {
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
                
                input {
                    font-family: inherit;
                    font-size: inherit;
                    color: inherit;
                    box-sizing: border-box;
                }

                input[type="text"] {
                    margin-left: 10px;
                    display: inline-block;
                    width: 10%;
                    height: 38px;
                    margin-top: 2px;
                    font-weight: 500;
                    background: none;
                    border: 0;
                    border-bottom: 1px solid #d8d8d8;
                }

                input[type="text"]:focus {
                    border-color: #4b8f29;
                    outline: 0;
                }
            </style>
        </head>
        <body>
            <h1><h:outputText value="Tabla de multiplicar"/></h1>
            <h:form>
                Introduzca el numero: <h:inputText value="#{calculoTabla.numTabla}"/>
                <h:commandButton style="margin-left: 5px;" value="Calcular" actionListener="#{calculoTabla.calcularTabla()}"/>
            </h:form>
                <h:dataTable value="#{calculoTabla.tablaMultiplicar}" var="tablaMulti" rendered="#{not empty calculoTabla.tablaMultiplicar[0][0]}">
                    <f:facet name="header">
                        <h:outputText value="Tabla del #{calculoTabla.numTabla}"/>
                    </f:facet>
                    <h:column>
                        <h:outputText value="#{tablaMulti[0]}"/>
                    </h:column>
                    
                    <h:column>
                        <h:outputText value="#{tablaMulti[1]}"/>
                    </h:column>
                    
                    <h:column>
                        <h:outputText value="#{tablaMulti[2]}"/>
                    </h:column>
                </h:dataTable>
        </body>
    </html>
</f:view>
