<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
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

    <title>Cancel Car Reservation</title>

    <link rel="canonical" href="https://getbootstrap.com/docs/4.1/examples/sign-in/">

    <!-- Bootstrap core CSS -->
   
    <!-- Custom styles for this template -->
    <link href="signin.css" rel="stylesheet">
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
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
    <h2>Submitted Information</h2>
    <table>
        <tr>
            <td><h1>Status: </h1></td>
            <td><h1>${lp}</h1></td>
        </tr>
        
    </table>
</body>
</html>

<script>
  setTimeout(function() {
      document.location = "/";}, 1000);
</script>