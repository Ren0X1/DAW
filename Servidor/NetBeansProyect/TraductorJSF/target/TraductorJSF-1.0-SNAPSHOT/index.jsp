<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@taglib prefix="h" uri="http://java.sun.com/jsf/html"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<f:view>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
            <title>Traductor JSF</title>
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
                    width: 98%;
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
            <f:loadBundle basename="locale" var="local"/>
            <h1><h:outputText value="#{local.titulo}"/></h1>
            <h:form>
                <h:commandButton actionListener="#{beanTraduccion.cambioIdioma}" value="es" image="data:image/png;base64,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"/>
                <h:commandButton actionListener="#{beanTraduccion.cambioIdioma}" value="en" image="data:image/png;base64,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"/>
            </h:form>
            <h:messages/>
        </body>
    </html>
</f:view>
