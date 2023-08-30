<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Exams Category</title>
</head>
<body>
	<%@ include file="../navbar.jsp"%>
	<body>
	<div class="container">
		<h1 class="text-center text-danger my-2">Check Subjective Question</h1>


		<table class="table table-hover m-auto table-sm" id="myTable">
			<thead>
				<tr>
					<th>Sr No</th>
					<th>Candidate Id</th>
					<th>Candidate Name</th>
					<th>Exam Set </th>
					<th>Status</th>
					<th>Action</th>
				</tr>
			</thead>

			<tbody>
				<c:forEach items="${CHECKSUBQUES}" var="cand" varStatus="status">
					<tr>
						<td>${status.index +1}</td>
						<td>${cand.candId}</td>
						<td>${cand.candFirstname} ${cand.candLastname }</td>
						<td>${cand.examSet.examSetName}</td>
						<td><span class="text-danger">Pending</span></td>
						<td><a href="http://localhost:8080/admin/checksubques/${cand.candId}" class="btn btn-primary">View</a> </td>
					</tr>
				</c:forEach>
			</tbody>

		</table>

	</div>

</body>

</body>
</html>