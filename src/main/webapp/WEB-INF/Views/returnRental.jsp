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

   <title>Return Vehicle</title>


   <!-- Bootstrap core CSS -->

   <!-- Custom styles for this template -->
   <link href="signin.css" rel="stylesheet">
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
            <li><<font size= "6" color="Gray">Star Rentals</font></a></li>
         </ul>
      </div>
   </nav>
</header>


<center>
   <img src="Return.jpg" alt="Return a Vehicle">
</center>
<center>
   <h3>Return Vehicle</h3>
</center>
<div class="row">
   <form action="searchreturnclient" method=post>

      <div class="col-lg-6">
         <h4>Search By</h4>
         <label class="control-label col-sm-3">Client License </label>
         <div class="input-group">
            <input type="text" name = "client_license" class="form-control" placeholder="">
         </div>
         <label class="control-label col-sm-3">Vehicle License</label>
         <div class="input-group">
            <input type="text" name = "vehicle_licensePlate" class="form-control" placeholder="">
         </div>
         <button class="btn btn-default" type="submit">Search</button>
      </div>
   </form>

</div>
<c:choose>
   <c:when test="${not empty vehiclecatalogfound}">
      <c:choose>
         <c:when test="${vehiclecatalogfound eq 'RESULT_FOUND'}">
            <h3>
               Results <span class="label label-default">${transactionsResults.size()}</span>
            </h3>

            <table id="clientRecord"
                  class="table table-striped table-bordered table-sm"
                  cellspacing="0" width="100%">
               <thead>
               <tr>
                  <th class="th-sm">Transaction ID</th>
                  <th class="th-sm">Client License Number</th>
                  <th class="th-sm">Vehicle License Plate</th>
                  <th class="th-sm">Booking From Date</th>
                  <th class="th-sm">Booking Till Date</th>
                  <th class="th-sm"></th>
               </tr>
               </thead>
               <c:forEach items="${transactionsResults}" var="transaction">
                  <form action="returnClientForm" method=post onsubmit="return confirm('Please confirm return of ${transaction.vehicleLicensePlate}');">

                     <tr id = ${transaction.vehicleLicensePlate}>
                        <td>${transaction.transactionID}</td>
                        <td>${transaction.clientLicenseNumber}</td>
                        <td>${transaction.vehicleLicensePlate}</td>
                        <td>${transaction.bookingFrom}</td>
                        <td>${transaction.bookingTill}</td>

                        <td><button  class="btn btn-primary" name ="transactionID" value=${transaction.transactionID} type="submit" onclick="myFunction()">Return</button></td>
                     </tr>
                  </form>
                  </tbody>
               </c:forEach>
            </table>
         </c:when>
         <c:otherwise>
            <h2>${transactionsResults}</h2>
         </c:otherwise>
      </c:choose>
   </c:when>
</c:choose>

</div>
<form action="backToManagePageRR" method=post>
   <center> <button class="btn btn-default" type="submit">Back To Manage Page</button></center>
</form>
</body>
</html>