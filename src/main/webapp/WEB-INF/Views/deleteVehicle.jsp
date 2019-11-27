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

  <title>Delete Vehicle</title>


  <!-- Bootstrap core CSS -->

  <!-- Custom styles for this template -->
  <link href="signin.css" rel="stylesheet">
  <link rel="stylesheet"
        href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script
          src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
  <script
          src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
          <script>

        	$('#submit').click(function(){
        	     /* when the submit button in the modal is clicked, submit the form */
        	    alert('submitting');
        	    $('#formfield').submit();
        	});
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
        <li><font size= "6" color="Gray">Star Rentals</font></a></li>
      </ul>
    </div>
  </nav>
</header>




<center>
  <img src="DeleteVehicle.jpg" alt="Delete a Vehicle">

</center>
<center>
  <h3>Delete Vehicle</h3>
</center>
<div class="row">
  <form action="searchDelVehicleForm" method=post>

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
    <c:when test="${not empty vehicle_found}">
      <c:choose>
        <c:when test="${vehicle_found eq 'RESULT_FOUND'}">
          <center><h3>
            Results <span class="label label-default">${vehicle_results.size()}</span>
          </h3></center>

          <table id="vehicleRecord"
                 class="table table-striped table-bordered table-sm"
                 cellspacing="0" width="100%">
            <thead>
            <tr>
              <th class="th-sm">License Plate</th>
              <th class="th-sm">Type</th>
              <th class="th-sm">Model</th>
              <th class="th-sm">Make</th>
              <th class="th-sm">Year</th>
			  <th class="th-sm">Status</th>
              <th class="th-sm"></th>
            </tr>
            </thead>
            <c:forEach items="${vehicle_results}" var="vehicles">
              <form action="deleteVehicleInfo" method=post onsubmit="return confirm('Vehicle deletion  of  ${vehicles.vehicleLicensePlate} done ');">

              <tr id = ${vehicles.vehicleLicensePlate}>
                <td>${vehicles.vehicleLicensePlate}</td>
                <td>${vehicles.type}</td>
                <td>${vehicles.model}</td>
                <td>${vehicles.make}</td>
                <td>${vehicles.year}</td>
				<td>${vehicles.status}</td>
                <td><button  name ="licensePlate" value=${vehicles.vehicleLicensePlate.replace(" ","_")} type="submit">Delete</button></td> 
              </tr>
              </form>
              </tbody>
            </c:forEach>
          </table>
        </c:when>
        <c:otherwise>
          <h2>${vehicle_results}</h2>
        </c:otherwise>
      </c:choose>
    </c:when>
  </c:choose>

 
  </div>
  <form action="/backtoadminmainpage" method=GET>
     
    <center> <button class="btn btn-default" type="submit">Back To Main Page</button></center>
 </form>

</body>
</html>
