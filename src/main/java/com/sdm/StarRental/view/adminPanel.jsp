<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!doctype html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">
<link rel="icon" href="/docs/4.1/assets/img/favicons/favicon.ico">

<title>Administrator Panel</title>
<link rel="canonical"
	href="https://getbootstrap.com/docs/4.1/examples/album/">

<!-- Bootstrap core CSS -->
<link href="../../dist/css/bootstrap.min.css" rel="stylesheet">

<!-- Custom styles for this template -->
<link href="album.css" rel="stylesheet">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

<style>
.bottom-left {
	font-weight: bold;
	bottom: 8px;
	left: 16px;
}

.row {
	margin-top: 50px;
}
</style>
</head>


<body>

	<header>
		<nav class="navbar navbar-inverse">
			<div class="container-fluid">
				<ul class="nav navbar-nav navbar-right">
					<li><a href="#"><span class="glyphicon glyphicon-user"></span>
							${loggedinusername}</a></li>
					<li><a href="logout"><span
							class="glyphicon glyphicon-log-out"></span> Logout</a></li>


				</ul>
				<ul class="nav navbar-nav navbar-left">
					<li><font size="6" color="Gray">Car Rentals</font></li>
				</ul>
			</div>
		</nav>
	</header>


	<main role="main">
	<!---------------------		CATALOG AND TRANSACTION VIEW  --------------------->
	<section class="jumbotron text-center">
		<div class="container">
			<h1 class="jumbotron-heading">Vehicle Catalog and Vehicle Transactions</h1>
		</div>
	</section>
	<section class="jumbotron text-center">
		<div class="album py-5 bg-light">
			<div class="container">
				<div style="display: flex">
					
					<!-------- CONTAINERS ------>
					<!-- BROWSE CATALOG -->
					<div style="flex: 1; padding-left: 5px;">
						<a href="browsecatalog"><img src="SearchCatalog.jpg"
							alt="browseCatalog"></a>
						<div class="bottom-left">Browse Vehicle Catalog</div>
					</div>
					
					<!-- VIEW TRANSACTION -->	
					<div style="flex: 1; padding-left: 5px;">
						<a href="viewtransactions"><img src="ViewTransactions.jpg"

							alt="viewTransactions"></a>
						<div class="bottom-left">View Transactions</div>
					</div>
					<!-------------------------->
				
				</div>
			</div>
		</div>
	</section>
	
	<!---------------------	VEHIVLE RECORD VIEW  --------------------->
	<section class="jumbotron text-center">
		<div class="container">
			<h1 class="jumbotron-heading"> Manage Vehicle Records</h1>
		</div>
	</section>
	<section class="jumbotron text-center">
		<div class="album py-5 bg-light">
			<div class="container">
				<div style="display: flex">
					
					<!-------- CONTAINERS ------>
					<!-- ADD VEHCICLE -->
					<div style="flex: 1; padding-left: 5px; padding-right: 5px;">


						<a href="createcatalog"><img src="NewVehicle.jpg"

							alt="Add vehicle"></a>
						<div class="bottom-left">Add new Vehicle</div>
					</div>
					
					<!-- MODIFY VEHCICLE -->
					<div style="flex: 1; padding-left: 5px;">

						<a href="modifyCatalog"><img src="ModifyVehicle.jpg"

							alt="update vehicle"></a>
						<div class="bottom-left">Modify Vehicle</div>
					</div>
					
					<!-- DELETE VEHCICLE -->
					<div style="flex: 1; padding-left: 5px;">

						<a href="deletevehicle"><img src="DeleteVehicle.jpg"

							alt="Delete vehicle"></a>
						<div class="bottom-left">Delete Vehicle</div>
					</div>
					<!-------------------------->
				</div>
			</div>
		</div>
	</section>
	</main>


</body>
</html>