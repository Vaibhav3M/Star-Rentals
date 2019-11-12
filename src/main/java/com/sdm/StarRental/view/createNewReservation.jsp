<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="/docs/4.1/assets/img/favicons/favicon.ico">
    <title>New Reservation Page</title>
    <link rel="canonical" href="https://getbootstrap.com/docs/4.1/examples/album/">

    <!-- Bootstrap core CSS -->
    <link href="../../dist/css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="album.css" rel="stylesheet">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

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
                    Clerk Name</a></li>
                <li><a href="#"><span class="glyphicon glyphicon-log-out"></span>
                    Logout</a></li>
            </ul>
            <ul class="nav navbar-nav navbar-left">
                <
                <li><a href="/"><font size="6" color="Gray">Car Rentals</font></a></li>
            </ul>
        </div>
    </nav>
</header>
<div class="form-group">
    <center>
        <img src="" alt="">
    </center>
    <center>
        <h3>Make Reservation</h3>
    </center>


    <div class="row">
        <table border="0" style="width: 50%; text-align: center; overflow: hidden;">
            <tr>
                <td><label>Client Details:</label></td>
                <td>
                    <form action="/searchClientMakeReservation" method="get">
                        <div class="input-group">
                            <input type="text" class="form-control" name=First_Name
                                   placeholder="First Name"/> <input type="text"
                                                                     class="form-control" name=License_Number
                                                                     placeholder="License Number"/> <span
                                class="input-group-btn">
									<button class="btn btn-default" type="submit">Search</button>
								</span>
                        </div>
                    </form>
                </td>
            </tr>

            <tr>
                <td><label>Vehicle License :</label></td>

                <td>
                    <form action="/searchCarMakeReservation" method="get">
                        <div class="input-group">
                            <input type="text" class="form-control" name=LICENSE_PLATE
                                   placeholder="Car License Plate"> <span
                                class="input-group-btn">
									<button class="btn btn-default" type="submit">Search</button>
								</span>
                        </div>
                    </form>
                </td>
            </tr>
        </table>
    </div>


    <!---------------------- 	TABLES 	 ------------------------>
    <!-- <form action = "makeRentalSubmission" method="post"> style="display: inline-block"-->
    <div class="row">
        <div class="col-md-14">
            <div class="col-xs-6" align="left">
                <c:choose>
                    <c:when test="${not empty client_found}">
                        <c:choose>
                            <c:when test="${client_found eq 'RESULT_FOUND'}">
                                <center>
                                    <h3>
                                        Clients <span class="label label-default">${client_results.size()}</span>
                                    </h3>
                                </center>
                                <!-- <div class="table-responsive"> -->
                                <div class="table">
                                    <form action="tableSelectClientMR" method="get">
                                        <table id="example" class="table table-striped">

                                            <thead>
                                            <tr id=${clients.licenseNumber}>
                                                <th class="col-md-1">First Name</th>
                                                <th class="col-md-2">Last Name</th>
                                                <th class="col-md-3">License Number</th>
                                                <th class="col-md-4">License Expiry Date</th>
                                                <th class="col-md-5">Phone Number</th>
                                                <th class="col-md-6"></th>
                                            </tr>
                                            </thead>
                                            <tbody>
                                            <c:forEach items="${client_results}" var="clients">
                                                <tr id=${clients.licenseNumber}>
                                                    <td class="col-md-1">${clients.firstName}</td>
                                                    <td class="col-md-2">${clients.lastName}</td>
                                                    <td class="col-md-3">${clients.licenseNumber}</td>
                                                    <td class="col-md-4">${clients.licenseExpiryDate}</td>
                                                    <td class="col-md-5">${clients.phoneNumber}</td>
                                                    <td class="col-md-6">
                                                        <button type="submit"
                                                                value=${clients.licenseNumber.replace(" ","_")}
                                                                        name="selectClient">Select
                                                        </button>
                                                    </td>
                                                </tr>
                                            </c:forEach>
                                            </tbody>
                                        </table>
                                    </form>
                                </div>
                            </c:when>
                            <c:otherwise>

                                <h3>
                                    <p>${client_results}</p>
                                </h3>
                            </c:otherwise>
                        </c:choose>
                    </c:when>
                    <c:otherwise>

                        <h3>
                            <p>${client_results}
                            <p>
                        </h3>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
        <div class="col-md-6">
            <div class="col-s-6" align="right">
                <c:choose>
                    <c:when test="${not empty catalog_vehicle_found}">
                        <c:choose>
                            <c:when test="${catalog_vehicle_found eq 'RESULT_FOUND'}">
                                <center>
                                    <h3>
                                        Car <span class="label label-default">${catalogVehicleResult.size()}</span>
                                    </h3>
                                </center>
                                <div class="table">
                                    <form action="tableSelectCarMR" method="get">
                                        <table class="table table-striped">

                                            <thead>
                                            <tr>
                                                <th class="col-md-1" data-sortable=>Model</th>
                                                <th class="col-md-2">Make</th>
                                                <th class="col-md-3">Color</th>
                                                <th class="col-md-4">License Plate</th>
                                                <th class="col-md-5"></th>
                                            </tr>
                                            </thead>
                                            <tbody>
                                            <c:forEach items="${catalogVehicleResult}" var="vehicle">
                                                <tr id=${vehicle.licensePlate}>
                                                    <td class="col-md-1">${vehicle.model}</td>
                                                    <td class="col-md-2">${vehicle.make}</td>
                                                    <td class="col-md-3">${vehicle.year}</td>
                                                    <td class="col-md-4">${vehicle.color}</td>
                                                    <td class="col-md-5">${vehicle.licensePlate}</td>
                                                    <td class="col-md-6">
                                                        <button type="submit"
                                                                value=${vehicle.licensePlate.replace(" ","_")}
                                                                        name="selectCar">Select
                                                        </button>
                                                    </td>
                                                </tr>

                                            </c:forEach>
                                            </tbody>
                                        </table>
                                    </form>
                                </div>
                            </c:when>
                            <c:otherwise>

                                <h3>
                                    <p>${catalogVehicleResult}</p>
                                </h3>
                            </c:otherwise>
                        </c:choose>
                    </c:when>
                    <c:otherwise>

                        <h3>
                            <p>${catalogVehicleResult}
                            <p>
                        </h3>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
    </div>


    <div class="row">
        <center>
            <label>Selected Client License : </label>${selectedClient} </br> <label>Selected
            Car License : </label>${selectedCar}
        </center>
    </div>

    <center>
        <div class="row">
            <br> <br>

            <h3 class="text">
                Date: <span class="label label-default"></span>
            </h3>

            <form action="makeReservation" method="post"
                  onsubmit="return confirm('Please confirm the reservation. You will be redirected to manage page after completion');">
                <b>From : </b> <input type="date" class="auto-style1" placeholder="From" name="fromDate" height=40px style="width: 28%" required >
                <br><br>
                <b>Till : </b><input type="date" min = " " class="auto-style1" placeholder="To" name="tillDate" height = 40px style="width: 28%" required>
                <br><br>
                <input type="submit" value="Submit" style="width: 106px; height: 38px">
                <br>
            </form>
        </div>
    </center>
</div>

<form action="backtomanagepage" method=get>

    <center>
        <button class="btn btn-default" type="submit">Back To
            Manage Page
        </button>
    </center>
</form>
</div>
</center>
<!-- </form> -->
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


<script>
    var today = new Date().toISOString().split('T')[0];
    document.getElementsByName("fromDate")[0].setAttribute('min',today);
    document.getElementsByName("tillDate")[0].setAttribute('min',today);
</script>

<%--<script>--%>
<%--    document.getElementsByName("fromDate")[0].addEventListener('change', function () {--%>
<%--        var selectedDate = document.getElementsByName("tillDate")[0].getAttribute('value');--%>
<%--        document.getElementsByName("tillDate")[0].setAttribute('min',selectedDate);--%>

<%--    })--%>
<%--</script>--%>

</body>

</html>

