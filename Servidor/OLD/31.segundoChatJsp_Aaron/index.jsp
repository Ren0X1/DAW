<html>
    <head>
        <meta charset="UFT-8">
        <style>
            form {
                display: block;
                width: 20em;
                margin: 1em auto;
                border: 1px solid black;
                border-radius: 25px;
                padding: 1%;
            }
            input[type="submit"] {
                display: block;
                height: 30px;
                margin: 10px 10px;
            }
        </style>
    </head>
    <body>
        <form method="GET" action="SalasChat.jsp">
            Nick: <input type="text" name="nick">
            <input type="color" name="color">
            <input type="submit" value="Enviar">
        </form>
    </body>
</html>