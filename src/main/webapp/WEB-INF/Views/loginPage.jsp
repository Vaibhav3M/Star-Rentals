<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css">

<!-- Optional theme 
<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap-theme.min.css">-->
<section id="loginform" class="outer-wrapper">

  <div class="inner-wrapper">
<div class="container">
  <div class="row">
    <div class="col-sm-4 col-sm-offset-4">
      <h2 class="text-center">Welcome back.</h2>
         <form action = "userHomePagee" method = "post">
  <div class="form-group">
    <label>Username</label>
    <input type="text" class="form-control" name="userName" placeholder="Enter username">
  </div>
  <div class="form-group">
    <label>Password</label>
    <input type="password" class="form-control" name="userPass" placeholder="Password">
  </div>

  <button type="submit" class="btn btn-default">Submit</button>
</form>
    </div>
  </div>
</div>
</div>

</section>
</body>
</html>