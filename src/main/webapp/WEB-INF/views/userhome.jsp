<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="sec"	uri="http://www.springframework.org/security/tags"%>

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
			<h3>Store</h3>
		</div>
	</div>
	
	<div class="container">
		<div class="row">
			<c:if test="${message != null }">
					<div class="alert alert-danger">
						<strong>${message}</strong>
					</div>
			</c:if>
			<form class="form-horizontal" action="/item-search" method="GET">
				<input class="form-control input-sm" type="text" name="itemName"
					placeholder="Search"> <br>
				<div class="form-group">
					<button type="submit" class="btn btn-primary">Submit</button>
				</div>
				</form>
		</div>
	</div>
	<br>
	<div class="container">
		<div class="table-responsive">
			<table class="table" id="bookTable">
				<thead>
					<tr>
						<th>ITEM NAME</th>
						<th>DESCRIPTION</th>
						<th>PRICE</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="item" items="${itemList}">
						<tr>
							<td><c:out value="${item.itemName}"></c:out></td>
							<td><c:out value="${item.itemDescription}"></c:out></td>
							<td><c:out value="${item.itemPrice}"></c:out></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
</body>

</html>