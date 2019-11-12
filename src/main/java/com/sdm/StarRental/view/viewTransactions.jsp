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

  <title>View Transactions</title>


  <!-- Bootstrap core CSS -->

  <!-- Custom styles for this template -->
  <link href="signin.css" rel="stylesheet">
  <link rel="stylesheet"
        href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
  <link href="https://fonts.googleapis.com/css?family=Montserrat" rel="stylesheet" type="text/css">
  <link href="https://fonts.googleapis.com/css?family=Lato" rel="stylesheet" type="text/css">
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
        	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
  <link href="https://fonts.googleapis.com/css?family=Montserrat" rel="stylesheet" type="text/css">
  <link href="https://fonts.googleapis.com/css?family=Lato" rel="stylesheet" type="text/css">


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
        <li><a href="backtomainpage"><font size= "6" color="Gray">Car Rentals</font></a></li>
      </ul>
    </div>
  </nav>
</header>




<center>
  <img src="ViewTransactions.jpg" alt="View Transactions">
</center>
<center>
  <h3>View Transactions</h3>
</center>
<div class="row">
  <form action="searchTransactions" method=post>

    <div class="col-lg-6">
				<h4>Search By</h4>
				<label class="control-label col-sm-3">Vehicle License</label>
				<div class="input-group">
					<input type="text" name="licensePlate" class="form-control"
						placeholder="License Plate">

				</div>
				<label class="control-label col-sm-3">Client License</label>
				<div class="input-group">
					<input type="text" name="licenseNumber" class="form-control"
						placeholder="License Number">

				</div>
				<label class="control-label col-sm-3">Status</label>
				<div class="input-group">
					</label> <select class="form-control" name="status">
						<option></option>
						<option>Available</option>
						<option>Rented</option>
						<option>Reserved</option>
					</select>
				</div>
				<label class="control-label col-sm-3">Transaction Date</label>
				
				<div class="control-label col-sm-3">
				
							<input type="date" id="transactionDate"
												class="auto-style1" placeholder="Transaction Date"
												name="transactionDate" height=40px style="width: 100%" />
									</div>
									
									<label class="control-label col-sm-2">Valid From</label>
				
				<div class="control-label col-sm-3">
				
							<input type="date" id="validFrom"
												class="auto-style1" placeholder=""
												name="validFrom" height=40px style="width: 100%" />
									</div>
										<label class="control-label col-sm-3">Valid Till</label>
				
				<div class="control-label col-sm-3">
				
				
				
							<input type="date" id="validTill"
												class="auto-style1" placeholder=""
												name="validTill" height=40px style="width: 100%" />
									</div>
									

			
				<center>
					<button class="btn btn-default" type="submit">Search</button>
				</center>
				<!-- /input-group -->
			</div>
  </form>

  
</div>
  <c:choose>
    <c:when test="${not empty transaction_found}">
      <c:choose>
        <c:when test="${transaction_found eq 'RESULT_FOUND'}">
         <center> <h3>
            Results <span class="label label-default">${transaction_results.size()}</span>
          </h3></center>

          <table id="transactionRecord"
                 class="table table-striped table-bordered table-sm"
                 cellspacing="0" width="100%">
            <thead>
							<c:choose>
								<c:when test="${not empty SearchParams}">
									<tr>
										<th class="th-sm">
											<form action="searchTransactions" method=post>
												<input type="hidden" name="transactionID" value='${SearchParams.get("transactionID")}'>
												<input type="hidden" name="licenseNumber" value='${SearchParams.get("licenseNumber")}'>
												<input type="hidden" name="licensePlate" value='${SearchParams.get("licensePlate")}'>
												<input type="hidden" name="status" value='${SearchParams.get("status")}'>
												<input type="hidden" name="transactionDate" value='${SearchParams.get("transactionDate")}'>
												<input type="hidden" name="validFrom" value='${SearchParams.get("rentedFrom")}'>
												<input type="hidden" name="validTill" value='${SearchParams.get("rentedTill")}'>
												<input type="hidden" name="transactionBy" value='${SearchParams.get("transactionBy")}'>
												<input type="hidden" name="sortBy" value="transactionID">
												<button class="btn btn-default" type="submit">Transaction ID</button>
											</form>
										</th>
										<th class="th-sm">
											<form action="searchTransactions" method=post>
												<input type="hidden" name="transactionID" value='${SearchParams.get("transactionID")}'>
												<input type="hidden" name="licenseNumber" value='${SearchParams.get("licenseNumber")}'>
												<input type="hidden" name="licensePlate" value='${SearchParams.get("licensePlate")}'>
												<input type="hidden" name="status" value='${SearchParams.get("status")}'>
												<input type="hidden" name="transactionDate" value='${SearchParams.get("transactionDate")}'>
												<input type="hidden" name="validFrom" value='${SearchParams.get("rentedFrom")}'>
												<input type="hidden" name="validTill" value='${SearchParams.get("rentedTill")}'>
												<input type="hidden" name="transactionBy" value='${SearchParams.get("transactionBy")}'>
												<input type="hidden" name="sortBy" value="licensePlate">
												<button class="btn btn-default" type="submit">License Plate</button>
											</form>
										</th>
										<th class="th-sm">
											<form action="searchTransactions" method=post>
												<input type="hidden" name="transactionID" value='${SearchParams.get("transactionID")}'>
												<input type="hidden" name="licenseNumber" value='${SearchParams.get("licenseNumber")}'>
												<input type="hidden" name="licensePlate" value='${SearchParams.get("licensePlate")}'>
												<input type="hidden" name="status" value='${SearchParams.get("status")}'>
												<input type="hidden" name="transactionDate" value='${SearchParams.get("transactionDate")}'>
												<input type="hidden" name="validFrom" value='${SearchParams.get("rentedFrom")}'>
												<input type="hidden" name="validTill" value='${SearchParams.get("rentedTill")}'>
												<input type="hidden" name="transactionBy" value='${SearchParams.get("transactionBy")}'>
												<input type="hidden" name="sortBy" value="licenseNumber">
												<button class="btn btn-default" type="submit">License Number</button>
											</form>
										</th>
										<th class="th-sm">
											<form action="searchTransactions" method=post>
												<input type="hidden" name="transactionID" value='${SearchParams.get("transactionID")}'>
												<input type="hidden" name="licenseNumber" value='${SearchParams.get("licenseNumber")}'>
												<input type="hidden" name="licensePlate" value='${SearchParams.get("licensePlate")}'>
												<input type="hidden" name="status" value='${SearchParams.get("status")}'>
												<input type="hidden" name="transactionDate" value='${SearchParams.get("transactionDate")}'>
												<input type="hidden" name="validFrom" value='${SearchParams.get("rentedFrom")}'>
												<input type="hidden" name="validTill" value='${SearchParams.get("rentedTill")}'>
												<input type="hidden" name="transactionBy" value='${SearchParams.get("transactionBy")}'>
												<input type="hidden" name="sortBy" value="status">
												<button class="btn btn-default" type="submit">Status</button>
											</form>
										</th>
										<th class="th-sm">
											<form action="searchTransactions" method=post>
												<input type="hidden" name="transactionID" value='${SearchParams.get("transactionID")}'>
												<input type="hidden" name="licenseNumber" value='${SearchParams.get("licenseNumber")}'>
												<input type="hidden" name="licensePlate" value='${SearchParams.get("licensePlate")}'>
												<input type="hidden" name="status" value='${SearchParams.get("status")}'>
												<input type="hidden" name="transactionDate" value='${SearchParams.get("transactionDate")}'>
												<input type="hidden" name="validFrom" value='${SearchParams.get("rentedFrom")}'>
												<input type="hidden" name="validTill" value='${SearchParams.get("rentedTill")}'>
												<input type="hidden" name="transactionBy" value='${SearchParams.get("transactionBy")}'>
												<input type="hidden" name="sortBy" value="transactionDate">
												<button class="btn btn-default" type="submit">Transaction date</button>
											</form>
										</th>
										<th class="th-sm">
											<form action="searchTransactions" method=post>
												<input type="hidden" name="transactionID" value='${SearchParams.get("transactionID")}'>
												<input type="hidden" name="licenseNumber" value='${SearchParams.get("licenseNumber")}'>
												<input type="hidden" name="licensePlate" value='${SearchParams.get("licensePlate")}'>
												<input type="hidden" name="status" value='${SearchParams.get("status")}'>
												<input type="hidden" name="transactionDate" value='${SearchParams.get("transactionDate")}'>
												<input type="hidden" name="validFrom" value='${SearchParams.get("rentedFrom")}'>
												<input type="hidden" name="validTill" value='${SearchParams.get("rentedTill")}'>
												<input type="hidden" name="transactionBy" value='${SearchParams.get("transactionBy")}'>
												<input type="hidden" name="sortBy" value="rentedFrom">
												<button class="btn btn-default" type="submit">Valid From</button>
											</form>
										</th>
										<th class="th-sm">
											<form action="searchTransactions" method=post>
												<input type="hidden" name="transactionID" value='${SearchParams.get("transactionID")}'>
												<input type="hidden" name="licenseNumber" value='${SearchParams.get("licenseNumber")}'>
												<input type="hidden" name="licensePlate" value='${SearchParams.get("licensePlate")}'>
												<input type="hidden" name="status" value='${SearchParams.get("status")}'>
												<input type="hidden" name="transactionDate" value='${SearchParams.get("transactionDate")}'>
												<input type="hidden" name="validFrom" value='${SearchParams.get("rentedFrom")}'>
												<input type="hidden" name="validTill" value='${SearchParams.get("rentedTill")}'>
												<input type="hidden" name="transactionBy" value='${SearchParams.get("transactionBy")}'>
												<input type="hidden" name="sortBy" value="rentedTill">
												<button class="btn btn-default" type="submit">Valid Till</button>
											</form>
										</th>

									</tr>
								</c:when>
								<c:otherwise>
									<tr>
										<th class="th-sm">
											<form action="searchTransactions" method=post>
												<input type="hidden" name="transactionID" value=''>
												<input type="hidden" name="licenseNumber" value=''>
												<input type="hidden" name="licensePlate" value=''>
												<input type="hidden" name="status" value=''>
												<input type="hidden" name="transactionDate" value=''>
												<input type="hidden" name="validFrom" value=''>
												<input type="hidden" name="validTill" value=''>
												<input type="hidden" name="transactionBy" value=''>
												<input type="hidden" name="sortBy" value="transactionID">
												<button class="btn btn-default" type="submit">Transaction ID</button>
											</form>
										</th>
										
										<th class="th-sm">
											<form action="searchTransactions" method=post>
												<input type="hidden" name="transactionID" value=''>
												<input type="hidden" name="licenseNumber" value=''>
												<input type="hidden" name="licensePlate" value=''>
												<input type="hidden" name="status" value=''>
												<input type="hidden" name="transactionDate" value=''>
												<input type="hidden" name="validFrom" value=''>
												<input type="hidden" name="validTill" value=''>
												<input type="hidden" name="transactionBy" value=''>
												<input type="hidden" name="sortBy" value="licensePlate">
												<button class="btn btn-default" type="submit">License Plate</button>
											</form>
										</th>
					
										<th class="th-sm">
											<form action="searchTransactions" method=post>
												<input type="hidden" name="transactionID" value=''>
												<input type="hidden" name="licenseNumber" value=''>
												<input type="hidden" name="licensePlate" value=''>
												<input type="hidden" name="status" value=''>
												<input type="hidden" name="transactionDate" value=''>
												<input type="hidden" name="validFrom" value=''>
												<input type="hidden" name="validTill" value=''>
												<input type="hidden" name="transactionBy" value=''>
												<input type="hidden" name="sortBy" value="licenseNumber">
												<button class="btn btn-default" type="submit">License Number</button>
											</form>
										</th>
					
										<th class="th-sm">
											<form action="searchTransactions" method=post>
												<input type="hidden" name="transactionID" value=''>
												<input type="hidden" name="licenseNumber" value=''>
												<input type="hidden" name="licensePlate" value=''>
												<input type="hidden" name="status" value=''>
												<input type="hidden" name="transactionDate" value=''>
												<input type="hidden" name="validFrom" value=''>
												<input type="hidden" name="validTill" value=''>
												<input type="hidden" name="transactionBy" value=''>
												<input type="hidden" name="sortBy" value="status">
												<button class="btn btn-default" type="submit">Status</button>
											</form>
										</th>
										<th class="th-sm">
											<form action="searchTransactions" method=post>
												<input type="hidden" name="transactionID" value=''>
												<input type="hidden" name="licenseNumber" value=''>
												<input type="hidden" name="licensePlate" value=''>
												<input type="hidden" name="status" value=''>
												<input type="hidden" name="transactionDate" value=''>
												<input type="hidden" name="validFrom" value=''>
												<input type="hidden" name="validTill" value=''>
												<input type="hidden" name="transactionBy" value=''>
												<input type="hidden" name="sortBy" value="transactionDate">
												<button class="btn btn-default" type="submit">Transaction Date</button>
											</form>
										</th>
										<th class="th-sm">
											<form action="searchTransactions" method=post>
												<input type="hidden" name="transactionID" value=''>
												<input type="hidden" name="licenseNumber" value=''>
												<input type="hidden" name="licensePlate" value=''>
												<input type="hidden" name="status" value=''>
												<input type="hidden" name="transactionDate" value=''>
												<input type="hidden" name="validFrom" value=''>
												<input type="hidden" name="validTill" value=''>
												<input type="hidden" name="transactionBy" value=''>
												<input type="hidden" name="sortBy" value="rentedFrom">
												<button class="btn btn-default" type="submit">Valid From</button>
											</form>
										</th>
										<th class="th-sm">
											<form action="searchTransactions" method=post>
												<input type="hidden" name="transactionID" value=''>
												<input type="hidden" name="licenseNumber" value=''>
												<input type="hidden" name="licensePlate" value=''>
												<input type="hidden" name="status" value=''>
												<input type="hidden" name="transactionDate" value=''>
												<input type="hidden" name="validFrom" value=''>
												<input type="hidden" name="validTill" value=''>
												<input type="hidden" name="transactionBy" value=''>
												<input type="hidden" name="sortBy" value="rentedTill">
												<button class="btn btn-default" type="submit">Valid Till</button>
											</form>
										</th>
										<th class="th-sm">
											<form action="searchTransactions" method=post>
												<input type="hidden" name="transactionID" value=''>
												<input type="hidden" name="licenseNumber" value=''>
												<input type="hidden" name="licensePlate" value=''>
												<input type="hidden" name="status" value=''>
												<input type="hidden" name="transactionDate" value=''>
												<input type="hidden" name="validFrom" value=''>
												<input type="hidden" name="validTill" value=''>
												<input type="hidden" name="transactionBy" value=''>
												<input type="hidden" name="sortBy" value="transactionBy">
												<button class="btn btn-default" type="submit">Action By</button>
											</form>
										</th>
									</tr>
								</c:otherwise>
							</c:choose>
						</thead>
            <c:forEach items="${transaction_results}" var="transactions">
              <form action="deleteVehicleInfo" method=post onsubmit="return confirm('Please confirm deletion of  ${transactions.licenseNumber}?');">

              <tr>
			  <td>${transactions.transactionID}</td>
                <td>${transactions.licensePlate}</td>
                <td>${transactions.licenseNumber}</td>
                <td>${transactions.status}</td>
                <td>${transactions.transactionDate}</td>
                <td>${transactions.rentedFrom}</td>
				<td>${transactions.rentedTill}</td>
				<td>${transactions.transactionBy}</td>
              </tr>
              </form>
              </tbody>
            </c:forEach>
          </table>
        </c:when>
        <c:otherwise>
          <h2>${transaction_results}</h2>
        </c:otherwise>
      </c:choose>
    </c:when>
  </c:choose>

 
  </div>
  <form action="backtoadminmainpage" method=post>
     
    <center> <button class="btn btn-default" type="submit">Back To Home Page</button></center>
 </form>

</body>
</html>
