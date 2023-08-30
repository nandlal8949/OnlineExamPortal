<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login Page</title>
<link href="bootstrap.min.css" rel="stylesheet">
<script src="jquery-3.6.1.min.js"></script>
<script src="bootstrap.bundle.min.js"></script>

</head>
<body>
	<div class="container">
		<h1 class="text-center text-danger my-2">Users Login</h1>
		<c:if test="${NOTEXISTS ne null}">
		<h4 class="bg-light text-center text-danger my-2">UserName or Password Not Exists  </h4>		
		</c:if>

		<form action="/" method="post"	class="row justify-content-center" >
				<div class="col-md-6">
					<div class="form-group">
						<label for="uName" class="mb-1">Username:</label>
						<input type="text" name="userName" id="uName" value="admin" class="form-control">
					</div>
					
					
					<div class="form-group">
						<label for="pWord" class="mb-1">Password:</label>
						<input type="password" name="password" id="pWord" value="1234" class="form-control" autocomplete="off">
					</div>
					
					<div class="text-right">
					<a href="forgetPassword"> forget-password</a>						
					</div>
					
					<div class="text-center">
					<input type="submit" class="btn btn-success w-25" value="Login">						
					</div>
				</div>
		</form>

	</div>

</body>
</html>