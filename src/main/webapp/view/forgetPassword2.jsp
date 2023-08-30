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
		<h1 class="text-center text-danger my-2">Change Password</h1>
		
		<c:if test="${SUCCESS eq 'failed'}">
		<h4 class="bg-light text-center text-danger my-2">Error Plz Try Again  </h4>		
		</c:if>
		
		<c:if test="${SUCCESS eq 'success'}">
			<script type="text/javascript">
				alert("password successful changed");
				window.location.href = "/";
			</script>		
		</c:if>

		<form action="./forgetPassword2" method="post" class="row justify-content-center"	autocomplete="off" >
				<div class="col-md-6">
					<input type="hidden" name="userId" value="${USER.userId}">
					<input type="hidden" name="candId" value="${CANDIDATE.candId}">
					<div class="form-group">
						<label for="uName" class="mb-1">User Name:</label>
						<c:if test="${USER ne null}">
						<input type="text" name="userName" id="uName" value="${USER.name}" class="form-control" disabled="disabled">
						</c:if>
						<c:if test="${CANDIDATE ne null}">
						<input type="text" name="userName" id="uName" value="${CANDIDATE.candFirstname} ${CANDIDATE.candLastname}" class="form-control" disabled="disabled">
						</c:if>
					</div>
					
					
					<div class="form-group">
						<label for="email" class="mb-1">New Password:</label>
						<input type="text" name="password" id="email" class="form-control">
					</div>
					
					<div class="text-center">
					<input type="submit" class="btn btn-success w-25" value="Submit">						
					</div>
				</div>
		</form>

	</div>

</body>
</html>