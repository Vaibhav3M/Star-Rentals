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

  <title>Delete Client</title>


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
  <img src="DeleteUser.jpg" alt="Delete a Client">
</center>
<center>
  <h3>Delete Client</h3>
</center>
<div class="row">
  <form action="searchDelClientForm" method=post>

    <div class="col-lg-6">
      <h4>Search By</h4>
      <label class="control-label col-sm-2">First Name</label>
      <div class="input-group">
        <input type="text" name = "First_Name" class="form-control" placeholder="First Name">
      </div>
      <label class="control-label col-sm-2">Last Name</label>
      <div class="input-group">
        <input type="text" name = "Last_Name" class="form-control" placeholder="Last Name">

      </div>
      <label class="control-label col-sm-2">License Number</label>
      <div class="input-group">
        <input type="text" name = "License_Number" class="form-control" placeholder="License Number">
      </div>
      <button class="btn btn-default" type="submit">Search</button>
    </div>
  </form>

  
</div>
  <c:choose>
    <c:when test="${not empty client_found}">
      <c:choose>
        <c:when test="${client_found eq 'RESULT_FOUND'}">
          <h3>
            Results <span class="label label-default">${client_results.size()}</span>
          </h3>

          <table id="clientRecord"
                 class="table table-striped table-bordered table-sm"
                 cellspacing="0" width="100%">
            <thead>
            <tr>
              <th class="th-sm">First Name</th>
              <th class="th-sm">Last Name</th>
              <th class="th-sm">License Number</th>
              <th class="th-sm">License Expiry Date</th>
              <th class="th-sm">Phone Number</th>
              <th class="th-sm"></th>
            </tr>
            </thead>
            <c:forEach items="${client_results}" var="clients">
              <form action="deleteClientInfo" method=post onsubmit="return confirm('Please confirm deletion of  ${clients.licenseNumber}?');">

              <tr id = ${clients.licenseNumber}>
                <td>${clients.firstName}</td>
                <td>${clients.lastName}</td>
                <td>${clients.licenseNumber}</td>
                <td>${clients.licenseExpiryDate}</td>
                <td>${clients.phoneNumber}</td>
                <td><button  name ="licenseNumber" value=${clients.licenseNumber} type="submit">Delete</button></td>
              </tr>
              </form>
              </tbody>
            </c:forEach>
          </table>
        </c:when>
        <c:otherwise>
          <h2>${client_results}</h2>
        </c:otherwise>
      </c:choose>
    </c:when>
  </c:choose>

 
  </div>
  <form action="backtomanagepage" method=get>
     
    <center> <button class="btn btn-default" type="submit">Back To Manage Page</button></center>
 </form>

</body>
</html>
