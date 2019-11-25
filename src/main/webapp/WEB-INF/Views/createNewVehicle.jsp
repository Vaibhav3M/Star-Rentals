
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">
<link rel="icon" href="/docs/4.1/assets/img/favicons/favicon.ico">
<title>New Vehicle Form</title>
<link rel="canonical"
	href="https://getbootstrap.com/docs/4.1/examples/album/">

<!-- Bootstrap core CSS -->
<link href="../../dist/css/bootstrap.min.css" rel="stylesheet">

<!-- Custom styles for this template -->
<link href="album.css" rel="stylesheet">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js">
function isNumber(evt)
{
	 var year = /^\d{10}$/;
	  if((evt.value.match(year) )
	        {
	      return true;
	        }
	      else
	        {
	        return false;
	        }

   return true;
}
</script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script type="text/javascript">
	$(document).ready(function(){
 	$('#datepicker').datepicker(); });
</script>
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
					<li><font size="6" color="Gray">Star Rentals</font></a></li>
				</ul>
			</div>
		</nav>
	</header>

	<main role="main">
	<section class="jumbotron text-center">
		<section class="jumbotron text-center">
			<div class="album py-5 bg-light">
				<div class="container">
					<div style="display: flex">
						<div style="flex: 1; padding-left: 5px; padding-right: 5px;">
							<div class="form-group"
								style="flex: 1; padding-left: 5px; padding-right: 5px;">

								<form class="form-horizontal" action="createVehicleForm"
									method="post" enctype="multipart/form-data">
									<div class="form-group">
										<a href="#"><img src="NewVehicle.jpg"
											alt="Search for a vehicle in the catalog"></a>
										<h3>
											<center>New Vehicle</center>
										</h3>
										<label class="control-label col-sm-2">Type</label>

										<!-- email input  -->
										<div class="col-sm-10">
											<input type="text" class="form-control" id="type"
												name="type" placeholder="Type" required maxlength="50">
										</div>
									</div>
									<!--  -->
									<div class="form-group">
										<label class="control-label col-sm-2">Make</label>
										<div class="col-sm-10">
											<input type="text" class="form-control" id="make"
												name="make" placeholder="Make" required maxlength="50">
										</div>
									</div>
									<div class="form-group">
										<label class="control-label col-sm-2">Model</label>
										<div class="col-sm-10">
											<input type="text" class="form-control" id="model"
												name="model" placeholder="Model" required maxlength="50">
										</div>
									</div>
									<div class="form-group">
										<label class="control-label col-sm-2">Color</label>
										<div class="col-sm-10">
											<input type="text" class="form-control" id="model"
												name="color" placeholder="Color" required maxlength="50">
										</div>
									</div>


									<div class="form-group">
										<label class="control-label col-sm-2">Year</label>
										<div class="col-sm-10">
											<input type="number" class="form-control" id="year"
												name="year" placeholder="Year" maxlength="4">
										</div>

									</div>
									
									<div class="form-group">
										<label class="control-label col-sm-2">License plate</label>
										<div class="col-sm-10">
											<input type="text" class="form-control" id="licensePlate"
												name="licensePlate" placeholder="License Number" required>
										</div>
									</div>
									
									 



									<!-- ************************************** -->
									<button type="submit" class="btn btn-primary" value="Login">Create
										New Vehicle</button>
								</form>
							</div>

							<form action="backtoadminmainpage" method=get>


								<center>
									<button class="btn btn-default" type="submit">Back To
										Main Page</button>
								</center>
							</form>
						</div>
					</div>


					<!-- Bootstrap core JavaScript
    ================================================== -->
					<!-- Placed at the end of the document so the pages load faster -->
					<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
						integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
						crossorigin="anonymous"></script>
					<script>window.jQuery || document.write('<script src="../../assets/js/vendor/jquery-slim.min.js"><\/script>')</script>
					<script src="../../assets/js/vendor/popper.min.js"></script>
					<script src="../../dist/js/bootstrap.min.js"></script>
					<script src="../../assets/js/vendor/holder.min.js"></script>
</body>

</html>