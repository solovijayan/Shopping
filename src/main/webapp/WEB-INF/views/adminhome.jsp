<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="sec"	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
<title>Bootstrap Example</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
<script
	src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>
<script
	src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.bundle.min.js"></script>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
	
</head>
<body>
welcome <sec:authentication property="name" />
<sec:authentication property="principal.authorities" />
<a href="/logout">logout</a>
	<div class="container">
		<div class="row">
			<h3>store</h3>
		</div>
	</div>
	<div class="container">
		<div class="row">
				<a href="/add-item" class="btn btn-info" role="button">ADD ITEM</a>
		</div>
	</div>
	<br>
	<div class="container">
		<div class="table-responsive">
			<table class="table" id="bookTable">
				<thead>
					<tr>
						<th>ITEM NAME</th>
						<th>DESCRIPTIION</th>
						<th>PRICE</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="item" items="${itemList}">
						<tr>
							<td><c:out value="${item.itemName}"></c:out></td>
							<td><c:out value="${item.itemDescription}"></c:out></td>
							<td><c:out value="${item.itemPrice}"></c:out></td>
							<td><a href="<c:url value="/itemEdit/${item.itemId}"/>">Edit</a></td>		
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
</body>

</html>