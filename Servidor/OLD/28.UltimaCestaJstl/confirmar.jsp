<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="pojos.*"%>
<%@page import="java.util.*"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="x" uri="http://java.sun.com/jsp/jstl/xml"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>
<html>
	<head>
		<link rel='stylesheet' href='./css/styles.css'>
		<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
		<script src="./js/index.js"></script>
	</head>
	<body>
        <h1>Gracias por comprar</h1>
		<c:choose>
			<c:when test="${session != null}">
				${sessionScope.invalidate}
			</c:when>
		</c:choose>
		<a href="index.jsp">Volver</a>
	</body>
</html>