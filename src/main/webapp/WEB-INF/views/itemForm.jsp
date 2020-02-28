<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
<title>Add item</title>
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
	<style type="text/css">
	.formError
	{
		color:red;
	}
	
	</style>
</head>
<body>
	<div class="container">
		<div class="row">
			<div class="col-sm-12">
				<h2 class="text-left">Add Item Details</h2>
				<form:form class="form-horizontal" action="/itemInsertion"
					modelAttribute="item" method="POST">
					<div class="form-group">
						<form:input type="hidden" class="form-control" path="itemId" value="${item.itemId}" />
					</div>
					<div class="form-group">
						<label for="bookName">ITEM NAME:</label>
						<form:input type="text" class="form-control" path="itemName" value="${item.itemName}"/>
						 <form:errors path="itemName" cssClass="formError"></form:errors>
					</div>
					<br>
					<div class="form-group">
						<label for="author">ITEM DESCRIPYION:</label>
						<form:input type="text" class="form-control"
							path="itemDescription" value="${item.itemDescription}"/>
							 <form:errors path="itemDescription" cssClass="formError"></form:errors>
					</div>
					<div class="form-group">
						<label for="description">ITEM PRICE:</label>
						<form:input type="number"  min="0" class="form-control" path="itemPrice" value="${item.itemPrice}"/>
						 <form:errors path="itemPrice" cssClass="formError"></form:errors>
					</div>
					<div class="form-group">
						<button type="submit" class="btn btn-primary">Submit</button>
					</div>
				</form:form>
			</div>
		</div>
	</div>
	<script type="text/javascript">
		function preventBack() {
			window.history.forward();
		}
		setTimeout("preventBack()", 0);
		window.onunload = function() {
			null
		};
	</script>
</body>
</html>