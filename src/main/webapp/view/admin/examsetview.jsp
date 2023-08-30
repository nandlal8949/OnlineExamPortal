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
		<h1 class="text-center text-danger my-2">ExamSet View Questions </h1>

		<table class="table table-hover m-auto table-sm" id="myTable">
			<thead>
				<tr>
					<th>Sr no.<th>
					<th>QuesExam Id</th>
					<th>Question Id</th>
					<th>Question Name</th>
					<th>Exam Id </th>
					<th>Action</th>
				</tr>
			</thead>

			<tbody>
				<c:forEach items="${QUESTIONLIST}" var="question" varStatus="status">
					<tr>
						 <td>${status.index+1 } </td>
						 <td></td>
						<td>${question[0]}</td>
						<td>${question[1]}</td>
						<td>${question[2]}</td>
						<td>${question[3]}</td>
						
						<td> <a
							href="http://localhost:8080/admin/examset/view/delete/${question[0]}/${question[1]}"
							class="btn btn-danger">Delete</a></td>
					</tr>
				</c:forEach>
			</tbody>

		</table>

	</div>

</body>

</body>
</html>