<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Página de error de login</title>
</head>
<body>


Usuario o password incorrectos,<a href=<%= response.encodeURL(request.getHeader("referer"))%>>Reintentar...</a>
Usuario o password incorrectos, prueba <a href="<%= response.encodeURL("PedidosCDs_67.html") %>"> de nuevo</a>

</body>
</html>
