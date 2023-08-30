<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Exams Category</title>
</head>
<body>
	<%@ include file="../navbar.jsp"%>
	<div class="container">
		<h1 class="text-center text-danger my-2">Candidate Manage</h1>

		<form action="/admin/candidate" method="post"
			class="justify-content-center">

			<div class="row my-2">
				<input type="hidden" name="candId" value="${CANDINFO.candId}"
					id="userId">
				<div class="col-md-4">
					<div class="form-group">
						<label for="first-name" class="mb-1">FirstName:</label> <input
							type="text" name="candFirstname" value="${CANDINFO.candFirstname}" id="first-name"
							class="form-control" autocomplete="off">
					</div>
				</div>
				<div class="col-md-4">
					<div class="form-group">
						<label for="last-name" class="mb-1">LastName:</label> <input
							type="text" name="candLastname" value="${CANDINFO.candLastname}""
							id="last-name" class="form-control" autocomplete="off">
					</div>

				</div>
				<div class="col-md-4">
					<div class="form-group">
						<label for="gender" class="mb-1">Gender:</label>
						 <select
							name="candGender" id="gender" class="form-control">
							<option value="0">-select-</option>
							<c:if test="${CANDINFO.candGender eq null }">
								<option value="Male">Male</option>
								<option value="Female">Female</option>
							</c:if>
							<c:if test="${CANDINFO.candGender eq 'Male' }">
								<option value="Male" selected>Male</option>
								<option value="Female">Female</option>
							</c:if>
							<c:if test="${CANDINFO.candGender eq 'Female' }">
								<option value="Male">Male</option>
								<option value="Female" selected>Female</option>
							</c:if>
						</select>
					</div>

				</div>


			</div>

			<div class="row my-2">
				<div class="col-md-4">
					<div class="form-group">
						<label for="mobile" class="mb-1">Mobile No:</label> <input
							type="text" name="candMobile" value="${CANDINFO.candMobile }" id="mobile"
							class="form-control" autocomplete="off">
					</div>

				</div>

				<div class="col-md-4">
					<div class="form-group">
						<label for="email" class="mb-1">Email:</label> <input type="text"
							name="candEmail" value="${CANDINFO.candEmail}" id="email" class="form-control"
							autocomplete="off">
					</div>

				</div>

				<div class="col-md-4">
					<div class="form-group">
						<label for="dob" class="mb-1">Date of Birth:</label> <input
							type="date" name="candDob" id="dob" value="${CANDINFO.candDob}" class="form-control">
					</div>

				</div>
			</div>

			<div class="row my-2">
				<div class="col-md-12">
					<div class="form-group">
						<label for="address" class="mb-1">Address:</label>
						<textarea rows="3" name="candAddress" id="address"
							class="form-control">${CANDINFO.candAddress } </textarea>
					</div>

				</div>

			</div>

			<div class="row my-2">
				<div class="col-md-4">
					<div class="form-group">
						<label for="username" class="mb-1">UserName:</label> <input
							type="text" name="candUserName" value="${CANDINFO.candUserName }" id="username"
							class="form-control" autocomplete="off">
					</div>

				</div>
				<div class="col-md-4">
					<div class="form-group">
						<label for="password" class="mb-1">Password:</label> <input
							type="text" name="candPassword" value="${CANDINFO.candPassword }" id="password"
							class="form-control" autocomplete="off">
					</div>
				</div>
				<div class="col-md-4">
					<div class="form-group">
						<label for="exam-date" class="mb-1">ExamDate:</label> <input
							type="date" name="candExamDate" value="${CANDINFO.candExamDate }" id="exam-date"
							class="form-control" autocomplete="off">
					</div>
				</div>
				
			</div>
			
			<div class="row my-2">
								<div class="col-md-4">
					<div class="form-group">
						<label for="is-delete" class="mb-1">Delete status:</label> <select
							name="isDelete" id="is-delete" class="form-control">
							<option value="0"> -select-</option>
							<c:if test="${CANDINFO.isDelete eq null }">
								<option value="No">No</option>
								<option value="Yes">Yes</option>
							</c:if>
							<c:if test="${CANDINFO.isDelete eq 'No' }">
								<option value="No" selected>No</option>
								<option value="Yes">Yes</option>
							</c:if>
							<c:if test="${CANDINFO.isDelete eq 'Yes' }">
								<option value="No">No</option>
							<option value="Yes" selected>Yes</option>
							</c:if>
							</select>
					</div>
				</div>
				<div class="col-md-4">
					<div class="form-group">
						<label for="exam-id" class="mb-1">Exam Name:</label> <select
							name="exam" id="exam-id" class="form-control">
							<option value="0"> -select- </option>
							<c:forEach items="${EXAMLIST}" var="exam" varStatus="status">
								<c:if test="${exam.examId eq CANDINFO.exam.examId }">
								<option value="${exam.examId}" selected>${exam.examName} </option>
								</c:if>
								<c:if test="${exam.examId ne CANDINFO.exam.examId }">
								<option value="${exam.examId}">${exam.examName} </option>
								</c:if>
							</c:forEach>
						</select>
					</div>

				</div>				
			
			</div>


			<div class="text-center mt-2">
				<div class="form-group">
					<input type="submit" class="btn btn-success" value="Submit">
				</div>

			</div>
		</form>
	</div>


	<c:if test="${SESSION != null }">
	<h2 class="text-center mb-1">CANDIDATE LIST</h2>


	<table class="table table-hover table-bordered table-striped m-auto table-sm" id="myTable">
		<thead class="text-center text-white bg-secondary">
			<tr>
				<th>C.Id</th>
				<th>User Name</th>
				<th>Email</th>
				<th>DOB</th>
				<th>Exam Date</th>
				<th>Deleted</th>
				<th>Exam Name</th>
				<th>Action</th>
			</tr>
		</thead>

		<tbody>
			<c:forEach items="${CANDLIST}" var="cand" varStatus="index">
				<tr>
					<td>${cand.candId}</td>
					<td>${cand.candUserName}</td>
					<td>${cand.candEmail}</td>
					<td>${cand.candDob}</td>
					<td>${cand.candExamDate}</td>
					<td>${cand.isDelete}</td>
					<td>${cand.exam.examName}</td>

					<td>
					<a
						href="http://localhost:8080/admin/candidate/assignment/${cand.candId}"
						class="btn btn-sm btn-warning">Assign</a>&nbsp;&nbsp;
					<a
						href="http://localhost:8080/admin/candidate/update/${cand.candId}"
						class="btn btn-sm btn-primary">Edit</a> &nbsp;&nbsp; <a onclick="return confirm('Are you sure?')"
						href="http://localhost:8080/admin/candidate/delete/${cand.candId}"
						class="btn btn-sm btn-danger">Delete</a></td>
				</tr>
			</c:forEach>
		</tbody>

	</table>
	</c:if>

</body>

</body>
</html>