<%-- 
    Document   : index
    Created on : 9 ene. 2022, 13:19:00
    Author     : jaxft
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Start Page</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <style>
            *{
                padding:2px;
                margin:5px;
            }
            html{
                margin:0;
                padding:0;
                background-image:-webkit-linear-gradient(left,blue,yellow);
            }
            h1{
                text-align:center;
            }
            form{
                padding:30px;
                width:500px;
                height:80%;
                margin:0 auto;
                border:1px solid red;
                background-color:rgb(234, 118, 184);
                border-radius:10px;
                box-shadow: 2px 2px 2px gray;
            }
            input[type="submit"]{
                margin-left:40%
            }
            img{
                position:absolute;
                width:20%;
                height:25%;
                right:10%;
                top:5%;
            }
            input[type="submit"]{
                text-shadow:2px 2px 2px gray;
                box-shadow:0 1px 0 white inset,2px 2px 3px 2px gray;
                background-image:-webkit-linear-gradient(left,hsla(196, 71%, 55%, 0.8) 20%,hsla(196, 66%, 48%, 0.9) 60%);
                background-image: -moz-linear-gradient(left,hsla(196, 71%, 55%, 0.8) 20%,hsla(196, 66%, 48%, 0.9) 60%);
                background-image: -ms-linear-gradient(left,hsla(196, 71%, 55%, 0.8) 20%,hsla(196, 66%, 48%, 0.9) 60%);
                background-image: -o-linear-gradient(left,hsla(196, 71%, 55%, 0.8) 20%,hsla(196, 66%, 48%, 0.9) 60%);
                background-image: linear-gradient(left,hsla(196, 71%, 55%, 0.8) 20%,hsla(196, 66%, 48%, 0.9) 60%); 
            }
            input[type="submit"]:hover{
                cursor:pointer;
                color:black;
                background-image:-webkit-linear-gradient(top left,hsla(185, 100%, 50%, 0.8) 10%,hsla(109, 100%, 50%, 0.7) 50%,hsla(63, 100%, 50%, 0.7) 90%); 
                background-image: -moz-linear-gradient(top left,hsla(185, 100%, 50%, 0.8) 10%,hsla(109, 100%, 50%, 0.7) 50%,hsla(63, 100%, 50%, 0.7) 90%); 
                background-image: -ms-linear-gradient(top left,hsla(185, 100%, 50%, 0.8) 10%,hsla(109, 100%, 50%, 0.7) 50%,hsla(63, 100%, 50%, 0.7) 90%); 
                background-image: -o-linear-gradient(top left,hsla(185, 100%, 50%, 0.8) 10%,hsla(109, 100%, 50%, 0.7) 50%,hsla(63, 100%, 50%, 0.7) 90%); 
                background-image: linear-gradient(top left,hsla(185, 100%, 50%, 0.8) 10%,hsla(109, 100%, 50%, 0.7) 50%,hsla(63, 100%, 50%, 0.7) 90%); 
            }
            input[type="submit"]:active{
                background-color:#2f0fff;
                background-image: -webkit-radial-gradient(50% 50%, ellipse cover, #2f0fff, #000000 100%);
                color:white;
                box-shadow:0 0 0 0 black,-1px -1px 2px 1px gray inset;
            }
        </style>
        
    </head>
    <jsp:include page="/creaCarpeta"/>
    <body>
        <h1>✨<em>Subida de archivos</em>✨</h1>
        <form action="procesoArchivo" method="post" name="formulario" enctype="multipart/form-data">
            <label for="archivo">Inspeccionar archivo(s): </label><input type="file" name="archivo" multiple><br>
            <input type="text" name="nombre" /> 
            <input type="submit" name="proceso" value="Subir archivo"/>
        </form>
        <img src="img/upload-cloud.png">
    </body>
</html>
