<%@ page contentType="text/html" pageEncoding="UTF-8" session="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="nl">
<head>
<title>Pizza's</title>
<link rel="shortcut icon" href="images/favicon.ico" type="images/x-icon" />
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="styles/default.css">
</head>
<body>
	<c:import url="/WEB-INF/JSP/menu.jsp" />
	<h1>
		Pizza's
		<c:forEach begin="1" end="5">&#9733;</c:forEach>
	</h1>
	<ul class="zebra">
		<c:forEach var="pizza" items="${pizzas}">
			<li>${pizza.id}:<c:out value="${pizza.naam}" />&euro;${pizza.prijs}
				${pizza.pikant ? "pikant" : "niet pikant"} <c:url
					value="/pizzas/detail.htm" var="detailURL">
					<c:param name="id" value="${pizza.id}" />
				</c:url> <a href='${detailURL}'>Detail</a> <c:if
					test='${pizzaIdsMetFoto.contains(pizza.id)}'>
					<c:url value='/pizzafotos/${pizza.id}.jpg' var='fotoURL' />
					<a href='${fotoURL}'>Foto</a>
				</c:if>
			</li>
		</c:forEach>
	</ul>
</body>
</html>