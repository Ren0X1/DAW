<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@taglib prefix="h" uri="http://java.sun.com/jsf/html"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<f:view>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
            <title>Mantenimiento de Personas</title>
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
                    display: block;
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
            <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
            <script src="https://code.jquery.com/ui/1.13.0/jquery-ui.min.js"></script>
            <link rel="stylesheet" href="https://code.jquery.com/ui/1.13.0/themes/smoothness/jquery-ui.css"/>
            <script>
                $(function() {
                    $('.fecha').attr('readonly', true).datepicker({
                        dateFormat: 'dd/mm/yy',
                        changeMonth: true,
                        changeYear: true,
                        yearRange: "1920:2021"
                    });
                });  
            </script>
        </head>
        <body>
            <h1><h:outputText value="Alta Personas"/></h1>
            <h:form>
                <h:outputText value="Nombre: "/><h:inputText value="#{altaPersona.nombre}"/>
                <h:outputText value="Apellidos: "/><h:inputText value="#{altaPersona.apellidos}"/>
                <h:outputText value="Fecha nacimiento: "/><h:inputText styleClass="fecha" value="#{altaPersona.fnacimiento}"/>
                <h:outputText value="Observaciones: "/><h:inputText value="#{altaPersona.observaciones}"/>
                <h:commandButton styleClass="botonNew" value="Guardar Persona" actionListener="#{altaPersona.guardarPersona()}"/>
            </h:form>
            <h:form>
                <h:commandButton styleClass="botonModi" value="Volver Index" action="volverIndex"/>
            </h:form>
        </body>
    </html>
    <h:messages/>
</f:view>
