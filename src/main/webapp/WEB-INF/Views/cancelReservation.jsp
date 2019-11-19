<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="/docs/4.1/assets/img/favicons/favicon.ico">

    <title>Cancel Reservation</title>

    <link rel="canonical" href="https://getbootstrap.com/docs/4.1/examples/sign-in/">

    <!-- Bootstrap core CSS -->

    <!-- Custom styles for this template -->
    <link href="signin.css" rel="stylesheet">
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

<div class="text-center">
    <header>

        <nav class="navbar navbar-inverse">
            <div class="container-fluid">
                <ul class="nav navbar-nav navbar-right">
                    <li><a href="#"><span class="glyphicon glyphicon-user"></span> ${loggedinusername}</a></li>
                    <li><a href="#"><span class="glyphicon glyphicon-log-out"></span> Logout</a></li>
                </ul>
                <ul class="nav navbar-nav navbar-left">
                    <li><font size="6" color="Gray">Car Rentals</font></li>
                </ul>
            </div>
        </nav>
    </header>
    <center><img src="CancelReserv.jpg" alt="Search for a vehicle in the catalog"></center>
    <center><h3>Cancel Reservation</h3></center>


</div>
<c:choose>
    <c:when test="${not empty ReservationsFound}">
        <c:choose>
            <c:when test="${ReservationsFound eq 'RESULT_FOUND'}">
                <h3>
                    Results <span class="label label-default">${ReservationsResults.size()}</span>
                </h3>

                <table id="clientRecord"
                       class="table table-striped table-bordered table-sm"
                       cellspacing="0" width="100%">
                    <thead>
                    <tr>
                        <th class="th-sm">Client License Number</th>
                        <th class="th-sm">Vehicle License Plate</th>
                        <th class="th-sm">Transaction Date</th>
                        <th class="th-sm"></th>
                    </tr>
                    </thead>
                    <c:forEach items="${ReservationsResults}" var="reservations">
                        <form action="cancelreservationForm" method=post onsubmit="return confirm('Please confirm cancellation of  ${reservations.licensePlate}?');">

                            <tr>
                                <td>${reservations.clientLicenseNumber}</td>
                                <td>${reservations.vehicleLicensePlate}</td>
                                <td>${reservations.bookingFrom}</td>
                                <td><button name ="licensePlate" value=${reservations.licensePlate.replace(" ","_")} type="submit">Cancel</button></td>
                            </tr>
                        </form>
                        </tbody>
                    </c:forEach>
                </table>
            </c:when>
            <c:otherwise>
                <h2>${ReservationsResults}</h2>
            </c:otherwise>
        </c:choose>
    </c:when>
</c:choose>

<form action="backtomanagepage" method=get>

    <center>
        <button class="btn btn-default" type="submit">Back To Manage Page</button>
    </center>
</form>

</body>
</html>
