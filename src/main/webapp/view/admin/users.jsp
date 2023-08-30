<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Exams Category</title>
</head>
<body>
	<%@ include file="../navbar.jsp"%>
	<div class="container">
		<h1 class="text-center text-danger my-2">User Manage</h1>

		<form action="/admin/users" method="post"
			class="justify-content-center">

			<div class="row my-2">
				<input type="hidden" name="userId" value="${USER.userId}" id="userId">
				<div class="col-md-3">
					<div class="form-group">
						<label for="name" class="mb-1">Name:</label> <input type="text"
							name="name" value="${USER.name}" id="name" class="form-control"
							autocomplete="off">
					</div>

				</div>
				<div class="col-md-3">
					<div class="form-group">
						<label for="gender" class="mb-1">Gender:</label> <select
							name="gender" id="gender" class="form-control">
							<option value="0">-select-</option>
							<c:if test="${USER.gender eq null }">
							<option value="Male">Male</option>
							<option value="Female">Female</option>
							</c:if>
							<c:if test="${USER.gender eq 'Male' }">
							<option value="Male" selected>Male</option>
							<option value="Female">Female</option>
							</c:if>
							<c:if test="${USER.gender eq 'Female' }">
							<option value="Male">Male</option>
							<option value="Female" selected>Female</option>
							</c:if>
							
							
						</select>
					</div>

				</div>

				<div class="col-md-3">
					<div class="form-group">
						<label for="email" class="mb-1">Email:</label> <input type="text"
							name="email" value="${USER.email }" id="email" class="form-control"
							autocomplete="off">
					</div>

				</div>
				<div class="col-md-3">
					<div class="form-group">
						<label for="mobileNo" class="mb-1">Mobile No:</label> <input type="text"
							name="mobileNo" value="${USER.mobileNo}" id="mobileNo" class="form-control"
							autocomplete="off">
					</div>

				</div>
			</div>

			<div class="row my-2">
				<div class="col-md-3">
					<div class="form-group">
						<label for="userName" class="mb-1">UserName:</label> <input type="text"
							name="userName" value="${USER.userName}" id="userName" class="form-control"
							autocomplete="off">
					</div>

				</div>

				<div class="col-md-3">
					<div class="form-group">
						<label for="password" class="mb-1">PassWord:</label> <input type="text"
							name="password" value="${USER.password}" id="password" class="form-control"
							autocomplete="off">
					</div>

				</div>

				<div class="col-md-3">
					<div class="form-group">
						<label for="role" class="mb-1">Role:</label> 
						<select
							name="role" id="role" class="form-control">
							<option value="0">-select-</option>
							<c:forEach items="${ROLES }" var="role">
							<c:if test="${USER.role.roleId eq role.roleId }">
								<option value="${role.roleId }" selected>${role.roleName }</option>	
							</c:if>
							
							<c:if test="${USER.role.roleId ne role.roleId }">
								<option value="${role.roleId }">${role.roleName }</option>	
							</c:if>
							</c:forEach>
						</select>
					</div>

				</div>
				<div class="col-md-3">
					<div class="form-group">
						<label for="status" class="mb-1">Status:</label> <input type="text"
							name="status" value="${USER.status}" id="status" class="form-control"
							autocomplete="off">
					</div>

				</div>
			</div>
			
			<div class="row my-2">
				<div class="col-md-6">
					<div class="form-group">
						<label for="userAddress" class="mb-1">Address:</label> <input type="text"
							name="userAddress" value="${USER.userAddress}" id="userAddress" class="form-control"
							autocomplete="off">
					</div>

				</div>

				<div class="col-md-3">
					<div class="form-group">
						<label for="isDelete" class="mb-1">Delete Status:</label>
						<select
							name="isDelete" id="isDelete" class="form-control">
							<option value="0">-select-</option>
							<c:if test="${USER.isDelete eq null }">
								<option value="Yes">Yes</option>
							<option value="No">No</option>
							</c:if>
							<c:if test="${USER.isDelete eq 'No' }">
								<option value="Yes">Yes</option>
							<option value="No" selected>No</option>
							</c:if>
							<c:if test="${USER.isDelete eq 'Yes' }">
								<option value="Yes" selected>Yes</option>
							<option value="No">No</option>
							</c:if>
						</select>
					</div>

				</div>

				<div class="col-md-3 mt-4">
					<div class="form-group">
						<input type="submit" class="btn btn-success w-75" value="Submit">
					</div>

				</div>

			</div>
			
		</form>


		<h2 class="text-center mb-1">USERS LIST</h2>
	<table class="table table-hover table-bordered table-striped m-auto table-sm" id="myTable">
		<thead class="text-center text-white bg-secondary">
				<tr class="text-center">
					<th>User Id</th>
					<th>User Name</th>
					<th>Email</th>
					<th>Gender</th>
					<th>Delete</th>
					<th>Mobile No</th>
					<th>Status</th>
					<th>Role</th>
					<th>Action</th>
				</tr>
			</thead>

			<tbody>
				<c:forEach items="${USERSLIST}" var="user" varStatus="index">
					<tr>
						<td>${user.userId}</td>
						<td>${user.userName }</td>
						<td>${user.email }</td>
						<td>${user.gender }</td>
						<td>${user.isDelete }</td>
						<td>${user.mobileNo }</td>
						<td>${user.status }</td>
						<td>${user.role.roleName }</td>
						<td><a
							href="http://localhost:8080/admin/users/update/${user.userId }"
							class="btn btn-sm btn-primary">Edit</a> &nbsp;&nbsp; <a
							href="http://localhost:8080/admin/users/delete/${user.userId }"
							class="btn btn-sm btn-danger">Delete</a></td>
					</tr>
				</c:forEach>
			</tbody>

		</table>

	</div>

</body>

</body>
</html>