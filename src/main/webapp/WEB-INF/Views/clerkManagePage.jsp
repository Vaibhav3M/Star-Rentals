<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!doctype html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="/docs/4.1/assets/img/favicons/favicon.ico">
<title>Manage Client Operations</title>
      <link rel="canonical" href="https://getbootstrap.com/docs/4.1/examples/album/">

    <!-- Bootstrap core CSS -->
    <link href="../../dist/css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="album.css" rel="stylesheet">
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js">
// function isNumber(evt)
// {
//    var charCode = (evt.which) ? evt.which : event.keyCode
//    if (charCode > 31 && (charCode < 48 || charCode > 57))
//       return false;
//
//    return true;
// }
</script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

	<style>
	.bottom-left {
	font-weight:bold;
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
            <li><a href="#"><span class="glyphicon glyphicon-user"></span> ${loggedinusername}</a></li>
            <li><a href="logout" ><span class="glyphicon glyphicon-log-out"></span> Logout</a></li>
            
            
          </ul>
         <ul class="nav navbar-nav navbar-left">
            <li><a href="/"><font size= "6" color="Gray">Car Rentals</font></a></li>
          </ul>
        </div>
      </nav>
    </header>

    <main role="main">
	  <section class="jumbotron text-center">
	  <div class="container">
	  <h1 class="jumbotron-heading">Booking Options</h1>
	  </div>
	  </section>
	  <section class="jumbotron text-center">
	  <div class="album py-5 bg-light">  
        <div class="container">
          <div style="display:flex">
            <div style="flex:1;padding-left:5px;padding-right:5px;">
                <a href="makerental"><img src="CreateRental.jpg" alt="Search for a vehicle in the catalog"></a>
				<div class="bottom-left">Rent a vehicle</div>
            </div>
            <div style="flex:1;padding-left:5px;">
                <a href="returnvehicle"><img src="Return.jpg" alt="Manage Client"></a>
				<div class="bottom-left">Return a vehicle</div>
              </div>
			  <div style="flex:1;padding-left:5px;">
                <a href="reserve"><img src="MakeReserv.jpg" alt="Manage Client"></a>
				<div class="bottom-left">Make a reservation</div>
              </div>
			  <div style="flex:1;padding-left:5px;">
                <a href="cancelReserve"><img src="CancelReserv.jpg" alt="Manage Client"></a>
				<div class="bottom-left">Cancel Reservation</div>
              </div>
			</div>
			</div>
			</div>
			</section>
			<section class="jumbotron text-center">
	  <div class="container">
	  <h1 class="jumbotron-heading">Client Record Options</h1>
	  </div>
	  </section>
	   <section class="jumbotron text-center">
	  <div class="album py-5 bg-light">  
        <div class="container">
          <div style="display:flex">
            <div style="flex:1;padding-left:5px;padding-right:5px;">
                <a href="createclient"><img src="AddUser.jpg" alt="Search for a vehicle in the catalog"></a>
				<div class="bottom-left">Add new client</div>
            </div>
            <div style="flex:1;padding-left:5px;">
                <a href="modifyclient"><img src="ModifyUser.jpg" alt="Manage Client"></a>
				<div class="bottom-left">Modify existing client</div>
              </div>
			  <div style="flex:1;padding-left:5px;">
                <a href="deleteclient"><img src="DeleteUser.jpg" alt="Manage Client"></a>
				<div class="bottom-left">Delete client</div>
              </div>
			</div>
			</div>
			</div>
			</section>
			
    </main>

    <!-- Bootstrap core JavaScript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
    <script>window.jQuery || document.write('<script src="../../assets/js/vendor/jquery-slim.min.js"><\/script>')</script>
    <script src="../../assets/js/vendor/popper.min.js"></script>
    <script src="../../dist/js/bootstrap.min.js"></script>
    <script src="../../assets/js/vendor/holder.min.js"></script>
  </body>
</html>
