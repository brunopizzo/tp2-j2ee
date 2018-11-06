<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
		 pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>tp2</title>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">

</head>
<body>
<nav class="navbar navbar-expand-lg navbar-light bg-light">

	<div class="collapse navbar-collapse" id="navbarNav">
		<ul class="navbar-nav">
			<li class="nav-item">
				<a class="nav-link" href="index.html">home</a>
			</li>
			<li class="nav-item">
				<a class="nav-link" href="agency">agency</a>
			</li>
		</ul>
	</div>
</nav>
<div class="container">

	<h4>Backlog priority: ${backlog.priority}, estimate: ${backlog.estimate} </h4>
	<h6>date:</h6>
	<p>${backlog.date}</p>
	<h6>description:</h6>
	<p>${backlog.description}</p>

	<h3>Create userstory</h3>
	<form action="userstory" method="post" class="form-group">
		<input type="hidden" name="id" value="${backlog.id}">
		title: <input type="text" name="title" class="form-control">
		<input class="btn btn-primary" type="submit" value="Create userstory">
	</form>


	<h3>Userstories list</h3>
	<table class="table">
		<thead class="thead-dark">
		<tr>
			<th>date</th>
			<th>title</th>
			<th>id</th>
			<th></th>
			<th></th>
		</tr>
		</thead>
		<c:forEach items="${backlog.userstories}" var="userstory">
			<tr>
				<td>${userstory.date}</td>
				<td>${userstory.title}</td>
				<td>${userstory.id}</td>
				<td>
					<form action="userstory" method="get" class="form-group">
						<input type="hidden" name="action" class="form-control" value="get">
						<input type="hidden" name="idUserstory" value="${userstory.id}">
						<input class="btn btn-primary" type="submit" value="info">
					</form>
				</td>
				<td>
					<form action="userstory" method="get" class="form-group">
						<input type="hidden" name="action" class="form-control" value="delete">
						<input type="hidden" name="idUserstory" value="${userstory.id}">
						<input type="hidden" name="id" value="${backlog.id}">
						<input class="btn btn-danger" type="submit" value="delete">
					</form>
				</td>
			</tr>

		</c:forEach>
	</table>





</div>




<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>

</body>
</html>