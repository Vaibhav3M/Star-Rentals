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

<title>Browse Vehicle Catalog</title>


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

	<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">Vehicle Details</h4>
				</div>
				<div class="col-md-4 bg" id="loadingIcon">
			        <div class="loader" id="loader-1"></div>
			     </div>
				<div class="modal-body">
					<div class="vehicle">
						<div class="row">
							<div class="col-xs-12 col-md-12">
							    <img class="img-responsive center-block" alt="vehicleImage" id="modal-vehicleImage">
						    </div>
						    
					    </div>
					    
					    <div class="panel panel-primary">
					      <div class="panel-heading">
					        <h3 class="panel-title">Status</h3>
					      </div>
					      <div class="panel-body" id="modal-vehicleStatus">
					      </div>
					    </div>
					    
					    
						<div class="panel panel-primary">
					      <div class="panel-heading">
					        <h3 class="panel-title">Type</h3>
					      </div>
					      <div class="panel-body" id="modal-vehicleType">
					      </div>
					    </div>
					    <div class="panel panel-primary">
					      <div class="panel-heading">
					        <h3 class="panel-title">Model</h3>
					      </div>
					      <div class="panel-body" id="modal-vehicleModel">
					      </div>
					    </div>
					    <div class="panel panel-primary">
					      <div class="panel-heading">
					        <h3 class="panel-title">Make</h3>
					      </div>
					      <div class="panel-body" id="modal-vehicleMake">
					      </div>
					    </div>
					    <div class="panel panel-primary">
					      <div class="panel-heading">
					        <h3 class="panel-title">Year</h3>
					      </div>
					      <div class="panel-body" id="modal-vehicleYear">
					      </div>
					    </div>
					     <div class="panel panel-primary">
					      <div class="panel-heading">
					        <h3 class="panel-title">color</h3>
					      </div>
					      <div class="panel-body" id="modal-vehicleColor">
					      </div>
					    </div>
					    <div class="panel panel-primary">
					      <div class="panel-heading">
					        <h3 class="panel-title">License Plate Number</h3>
					      </div>
					      <div class="panel-body" id="modal-vehicleLicensePlate">
					      </div>
					    </div>
					
				    </div>
				</div>
				<div class="modal-footer" id="modalFooterButtons">
				</div>
			</div>
		</div>
	</div>

	<center>
		<img src="SearchCatalog.jpg" alt="Search for a vehicle in the catalog">
	</center>
	<center>
		<h3>Catalog Search</h3>
	</center>
	<div class="row">
		<form action="searchbrowsecatalog" method=post>

			<div class="col-lg-6">
				<h4>Search By</h4>
				<label class="control-label col-sm-2">Model</label>
				<div class="input-group">
					<input type="text" name="model" class="form-control"
						placeholder="Model">

				</div>
				<label class="control-label col-sm-2">Make</label>
				<div class="input-group">
					<input type="text" name="make" class="form-control"
						placeholder="Make">

				</div>
				
				<label class="control-label col-sm-2">Color</label>
				<div class="input-group">
					<input type="text" name="color" class="form-control"
						placeholder="Color">

				</div>
				<div>
				<label class="control-label col-sm-2">Status</label>
				<div class="btn-group btn-group-toggle" data-toggle="buttons">
					 <select class="form-control" name="status">
						<option></option>
						<option>Available</option>
						<option>Reserved</option>
						<option>Rented</option>
						<option>UnReserved</option>
					</select>

				</div>
				</div>
				
				<label class="control-label col-sm-2">Year</label>
				<div class="btn-group btn-group-toggle" data-toggle="buttons">
					<label class="btn btn-secondary active"> <input
						type="radio" name="condition" id="option1" value="="
						autocomplete="off" checked> =
					</label> <label class="btn btn-secondary"> <input type="radio"
						name="condition" id="option2" value=">" autocomplete="off">
						&gt;
					</label> <label class="btn btn-secondary"> <input type="radio"
						name="condition" id="option3" value="<" autocomplete="off">
						&lt;
					</label> <select class="form-control" name="year">
						<option></option>
						<option>2019</option>
						<option>2018</option>
						<option>2017</option>
						<option>2016</option>
						<option>2015</option>
						<option>2014</option>
						<option>2013</option>
						<option>2012</option>
						<option>2011</option>
						<option>2010</option>
						<option>2009</option>
						<option>2008</option>
						<option>2007</option>
						<option>2006</option>
						<option>2005</option>
						<option>2004</option>
						<option>2003</option>
						<option>2002</option>
						<option>2001</option>
						<option>2000</option>
					</select>
					<!-- /btn-group -->
				</div>
				<center>
					<button class="btn btn-default" type="submit">Search</button>
				</center>
				<!-- /input-group -->
			</div>
		</form>

	</div>
	<c:choose>
		<c:when test="${not empty browsecatalogfound}">
			<c:choose>
				<c:when test="${browsecatalogfound eq 'RESULT_FOUND'}">
					<center>
						<h3>
							Results <span class="label label-default">${browsecatalogresults.size()}</span>
						</h3>
					</center>

					<table id="clientRecord"
						class="table table-striped table-bordered table-sm"
						cellspacing="0" width="100%">
						<thead>
							<c:choose>
								<c:when test="${not empty SearchParams}">
									<tr>
										<th class="th-sm">
											<form action="searchbrowsecatalog" method=post>
												<input type="hidden" name="model" value='${SearchParams.get("model")}'>
												<input type="hidden" name="make" value='${SearchParams.get("make")}'>
												<input type="hidden" name="color" value='${SearchParams.get("color")}'>
												<input type="hidden" name="condition" value='${SearchParams.get("condition")}'>
												<input type="hidden" name="status" value='${SearchParams.get("status")}'>
												<input type="hidden" name="year" value='${SearchParams.get("year")}'>
												
												<input type="hidden" name="sortBy" value="licensePlate">
												<button class="btn btn-default" type="submit">license Plate</button>
											</form>
										</th>
										<th class="th-sm">
											<form action="searchbrowsecatalog" method=post>
												<input type="hidden" name="model" value='${SearchParams.get("model")}'>
												<input type="hidden" name="make" value='${SearchParams.get("make")}'>
												<input type="hidden" name="color" value='${SearchParams.get("color")}'>
												<input type="hidden" name="condition" value='${SearchParams.get("condition")}'>
												<input type="hidden" name="status" value='${SearchParams.get("status")}'>
												<input type="hidden" name="year" value='${SearchParams.get("year")}'>
												
												<input type="hidden" name="sortBy" value="type">
												<button class="btn btn-default" type="submit">Type</button>
											</form>
										</th>
										<th class="th-sm">
											<form action="searchbrowsecatalog" method=post>
												<input type="hidden" name="model" value='${SearchParams.get("model")}'>
												<input type="hidden" name="make" value='${SearchParams.get("make")}'>
												<input type="hidden" name="color" value='${SearchParams.get("color")}'>
												<input type="hidden" name="condition" value='${SearchParams.get("condition")}'>
												<input type="hidden" name="status" value='${SearchParams.get("status")}'>
												<input type="hidden" name="year" value='${SearchParams.get("year")}'>
												
												<input type="hidden" name="sortBy" value="model">
												<button class="btn btn-default" type="submit">Model</button>
											</form>
										</th>
										<th class="th-sm">
											<form action="searchbrowsecatalog" method=post>
												<input type="hidden" name="model" value='${SearchParams.get("model")}'>
												<input type="hidden" name="make" value='${SearchParams.get("make")}'>
												<input type="hidden" name="color" value='${SearchParams.get("color")}'>
												<input type="hidden" name="condition" value='${SearchParams.get("condition")}'>
												<input type="hidden" name="status" value='${SearchParams.get("status")}'>
												<input type="hidden" name="year" value='${SearchParams.get("year")}'>
												
												<input type="hidden" name="sortBy" value="make">
												<button class="btn btn-default" type="submit">Make</button>
											</form>
										</th>
										<th class="th-sm">
											<form action="searchbrowsecatalog" method=post>
												<input type="hidden" name="model" value='${SearchParams.get("model")}'>
												<input type="hidden" name="make" value='${SearchParams.get("make")}'>
												<input type="hidden" name="color" value='${SearchParams.get("color")}'>
												<input type="hidden" name="condition" value='${SearchParams.get("condition")}'>
												<input type="hidden" name="status" value='${SearchParams.get("status")}'>
												<input type="hidden" name="year" value='${SearchParams.get("year")}'>
												
												<input type="hidden" name="sortBy" value="color">
												<button class="btn btn-default" type="submit">Color</button>
											</form>
										</th>
										
										<th class="th-sm">
											<form action="searchbrowsecatalog" method=post>
												<input type="hidden" name="model" value='${SearchParams.get("model")}'>
												<input type="hidden" name="make" value='${SearchParams.get("make")}'>
												<input type="hidden" name="color" value='${SearchParams.get("color")}'>
												<input type="hidden" name="condition" value='${SearchParams.get("condition")}'>
												<input type="hidden" name="status" value='${SearchParams.get("status")}'>
												<input type="hidden" name="year" value='${SearchParams.get("year")}'>
													
												<input type="hidden" name="sortBy" value="status">
												<button class="btn btn-default" type="submit">Status</button>
											</form>
										</th>
										
										
										
										
										<th class="th-sm">
											<form action="searchbrowsecatalog" method=post>
												<input type="hidden" name="model" value='${SearchParams.get("model")}'>
												<input type="hidden" name="make" value='${SearchParams.get("make")}'>
												<input type="hidden" name="color" value='${SearchParams.get("color")}'>
												<input type="hidden" name="condition" value='${SearchParams.get("condition")}'>
												<input type="hidden" name="status" value='${SearchParams.get("status")}'>
												<input type="hidden" name="year" value='${SearchParams.get("year")}'>
												
												<input type="hidden" name="sortBy" value="year">
												<button class="btn btn-default" type="submit">Year</button>
											</form>
										</th>
										
							
										<th class="th-sm"></th>
									</tr>
								</c:when>
								<c:otherwise>
									<tr>
										<th class="th-sm">
											<form action="searchbrowsecatalog" method=post>
												<input type="hidden" name="model" value=''>
												<input type="hidden" name="make" value=''>
												<input type="hidden" name="color" value=''>
												<input type="hidden" name="condition" value=''>
												<input type="hidden" name="status" value=''>
												<input type="hidden" name="year" value=''>
												
												<input type="hidden" name="sortBy" value="licensePlate">
												<button class="btn btn-default" type="submit">license Plate</button>
											</form>
										</th>
										<th class="th-sm">
											<form action="searchbrowsecatalog" method=post>
												<input type="hidden" name="model" value=''>
												<input type="hidden" name="make" value=''>
												<input type="hidden" name="color" value=''>
												<input type="hidden" name="condition" value=''>
												<input type="hidden" name="status" value=''>
												<input type="hidden" name="year" value=''>
												
												<input type="hidden" name="sortBy" value="type">
												<button class="btn btn-default" type="submit">Type</button>
											</form>
										</th>
										<th class="th-sm">
											<form action="searchbrowsecatalog" method=post>
												<input type="hidden" name="model" value=''>
												<input type="hidden" name="make" value=''>
												<input type="hidden" name="color" value=''>
												<input type="hidden" name="condition" value=''>
												<input type="hidden" name="status" value=''>
												<input type="hidden" name="year" value=''>
												
												<input type="hidden" name="sortBy" value="model">
												<button class="btn btn-default" type="submit">Model</button>
											</form>
										</th>
										<th class="th-sm">
											<form action="searchbrowsecatalog" method=post>
												<input type="hidden" name="model" value=''>
												<input type="hidden" name="make" value=''>
												<input type="hidden" name="color" value=''>
												<input type="hidden" name="condition" value=''>
												<input type="hidden" name="status" value=''>
												<input type="hidden" name="year" value=''>
												
												<input type="hidden" name="sortBy" value="make">
												<button class="btn btn-default" type="submit">Make</button>
											</form>
										</th>
										<th class="th-sm">
											<form action="searchbrowsecatalog" method=post>
												<input type="hidden" name="model" value=''>
												<input type="hidden" name="make" value=''>
												<input type="hidden" name="color" value=''>
												<input type="hidden" name="condition" value=''>
												<input type="hidden" name="status" value=''>
												<input type="hidden" name="year" value=''>
												<input type="hidden" name="sortBy" value="color">
												<button class="btn btn-default" type="submit">Color</button>
											</form>
										</th>
										
										<th class="th-sm">
											<form action="searchbrowsecatalog" method=post>
												<input type="hidden" name="model" value=''>
												<input type="hidden" name="make" value=''>
												<input type="hidden" name="color" value=''>
												<input type="hidden" name="condition" value=''>
												<input type="hidden" name="status" value=''>
												<input type="hidden" name="year" value=''>
												<input type="hidden" name="sortBy" value="status">
												<button class="btn btn-default" type="submit">Status</button>
											</form>
										</th>
										
										
										
										<th class="th-sm">
											<form action="searchbrowsecatalog" method=post>
												<input type="hidden" name="model" value=''>
												<input type="hidden" name="make" value=''>
												<input type="hidden" name="color" value=''>
												<input type="hidden" name="condition" value=''>
												<input type="hidden" name="status" value=''>
												<input type="hidden" name="year" value=''>
												<input type="hidden" name="sortBy" value="year">
												<button class="btn btn-default" type="submit">Year</button>
											</form>
										</th>
										<th class="th-sm"></th>
									</tr>
								</c:otherwise>
							</c:choose>
						</thead>
						<tbody>
							<c:forEach items="${browsecatalogresults}" var="vehicle">

								<tr>
									<td>${vehicle.vehicleLicensePlate}</td>
									<td>${vehicle.type}</td>
									<td>${vehicle.model}</td>
									<td>${vehicle.make}</td>
									<td>${vehicle.color}</td>
									<td>${vehicle.status}</td>
									<td>${vehicle.year}</td>
									<td><button id="${vehicle.vehicleLicensePlate}" type="button" class="btn btn-primary btn-sm licenseButtons"
											onclick="openModal('${vehicle.vehicleLicensePlate}')">open</button></td>
								</tr>

							</c:forEach>
						</tbody>
					</table>
				</c:when>
				<c:otherwise>

					<h3>
						<p>${browsecatalogresults}
						<p>
					</h3>
				</c:otherwise>
			</c:choose>
		</c:when>
		<c:otherwise>

			<h3>
				<p>${browsecatalogresults}
				<p>
			</h3>
		</c:otherwise>
	</c:choose>
	</div>

	<form action="backtomainpage" method=get>
     
		<center> <button class="btn btn-default" type="submit">Back To Main Page</button></center>
	 </form>

</body>
</html>
