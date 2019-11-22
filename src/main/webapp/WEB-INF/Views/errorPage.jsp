<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!doctype html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">
<link rel="icon" href="/docs/4.1/assets/img/favicons/favicon.ico">

<title>Browse Catalog</title>


<!-- Bootstrap core CSS -->

<!-- Custom styles for this template -->
<link href="signin.css" rel="stylesheet">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script src="https://code.jquery.com/jquery-3.4.1.min.js"
	integrity="sha256-CSXorXvZcTkaix6Yvo6HppcZGetbYMGWSFlBw8HfCJo="
	crossorigin="anonymous"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script type="text/javascript" src="js/browse.js"> </script>

<style>
.bottom-left {
	font-weight: bold;
	bottom: 8px;
	left: 16px;
}

.row {
	margin-top: 50px;
}

.table {
	border-radius: 5px;
	width: 50%;
	margin: 0px auto;
	float: none;
}

body {
	padding-bottom: 50px;
}
</style>
</head>

<body>
	<header>

		<nav class="navbar navbar-inverse">
			<div class="container-fluid">
			  <ul class="nav navbar-nav navbar-right">
				<li><a href="#"><span class="glyphicon glyphicon-user"></span> ${loggedinusername}</a></li>
				<li><a href="logout" ><span class="glyphicon glyphicon-log-out"></span> Logout</a></li>
				
				
			  </ul>
			   <ul class="nav navbar-nav navbar-left">
				<li><a href="backtomainpage"><font size= "6" color="Gray">Star Rentals</font></a></li>
			  </ul>
			</div>
		  </nav>
	</header>

<body>
<h1> You Are Not Authorized to Access This Page</h1>
</body>
</html>