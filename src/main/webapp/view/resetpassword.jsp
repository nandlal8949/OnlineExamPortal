<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login Page</title>

</head>
<body>
	<%@ include file="navbar.jsp"%>
	<div class="container">
		<h1 class="text-center text-danger my-2">Change Password</h1>

		<form action="/resetpassword" method="post"
			class="row justify-content-center" autocomplete="off">
			<div class="col-md-6">
				<div class="form-group">
					<label for="uName" class="mb-1">User Name:</label> 
					<input type="hidden" name="userId" value="${USER.userId }">
					 <input	type="text" name="userName" value="${USER.userName}"
						class="form-control" readonly>
				</div>


				<div class="form-group">
					<label for="oldpassword" class="mb-1">Old Password:</label> <input
						type="text" name="oldpassword" id="oldpassword" class="form-control">
				</div>
				
				<div class="form-group">
					<label for="newpassword" class="mb-1">New Password:</label> <input
						type="text" name="newpassword" id="newpassword" class="form-control">
				</div>

				<div class="text-center">
					<input type="submit" class="btn btn-success w-50"
						value="Change Password">
				</div>
			</div>
		</form>

	</div>

</body>
</html>