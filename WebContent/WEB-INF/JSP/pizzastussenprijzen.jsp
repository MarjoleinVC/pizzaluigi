<%@ page contentType="text/html" pageEncoding="UTF-8" session="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="nl">
<head>
<c:import url="/WEB-INF/JSP/head.jsp">
	<c:param name="title" value="Pizza's tussen prijzen" />
</c:import>
</head>
<body>
	<c:import url="/WEB-INF/JSP/menu.jsp" />
	<h1>Pizza's tussen prijzen</h1>
	<form>
		<label>Van prijs<span>${fouten.van}</span><input name="van"
			value="${param.van}" autofocus type="number" min="0" required></label>
		<label>Tot prijs<span>${fouten.tot}</span> <input name="tot"
			value="${param.tot}" type="number" min="0" required></label> <input
			type="submit" value="Zoeken">
	</form>
	<c:if test="${not empty pizzas}">
		<ul class="zebra">
			<c:forEach var="pizza" items="${pizzas}">
				<li><c:out value="${pizza.naam}" />&euro;${pizza.prijs}</li>
			</c:forEach>
		</ul>
	</c:if>
</body>
</html>