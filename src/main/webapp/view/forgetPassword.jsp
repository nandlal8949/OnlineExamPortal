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

</head>
<body>
	<div class="container">
		<h1 class="text-center text-danger my-2">Forget Password</h1>
		<c:if test="${NOTEXISTS ne null}">
		<h4 class="bg-light text-center text-danger my-2">UserName and Email Not Exists, Plz Try Again  </h4>		
		</c:if>

		<form action="./forgetPassword" method="post" class="row justify-content-center"	autocomplete="off" >
				<div class="col-md-6">
					<div class="form-group">
						<label for="uName" class="mb-1">Enter Your User Name:</label>
						<input type="text" name="userName" id="uName" class="form-control">
					</div>
					
					
					<div class="form-group">
						<label for="email" class="mb-1">Enter Your Email Id:</label>
						<input type="text" name="email" id="email" class="form-control">
					</div>
					
					<div class="text-center">
					<input type="submit" class="btn btn-success w-25" value="Submit">						
					</div>
				</div>
		</form>

	</div>

</body>
</html>