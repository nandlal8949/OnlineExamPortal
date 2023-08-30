<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Exams Category</title>
</head>
<body>
	<%@ include file="../navbar.jsp"%>
	<div class="container">
		<h1 class="text-center text-danger my-2">Candidate Assignment List </h1>
				<table id="example" class="display table-sm table-bordered" style="width: 100%">
			<thead>
				<tr>
					<th>Sr. No.</th>
					<th>Candidate Id</th>
					<th>Candidate Name </th>
					<th>Email </th>
					<th>UserName</th>
					<th>ExamSet</th>
					<th>Action</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${CANDIDATELIST}" var="candidate" varStatus="status">
					<tr>
						<td>${status.index+1}</td>
						<td>${candidate.candId}</td>
						<td>${candidate.candFirstname} ${candidate.candLastname}</td>
						<td>${candidate.candEmail}</td>
						<td>${candidate.candUserName}</td>
						<td>${candidate.examSet.examSetQuestion.size() > 0 ? candidate.examSet.examSetName : '<span style="color:red;"> Pending </span>'}</td>
						<td><a class="btn btn-sm btn-primary"
							href="http://localhost:8080/admin/candidate/assignment/${candidate.candId}">Edit</a></td>
					</tr>
				</c:forEach>
			</tbody>

		</table>
		
	</div>
</body>

</body>
</html>