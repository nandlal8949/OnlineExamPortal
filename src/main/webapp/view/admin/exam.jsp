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
		<h1 class="text-center text-danger my-2">Exam Manage</h1>

		<form action="/admin/exam" method="post">
			<input type="hidden" name="examId" value="${EXAM.examId}" id="examId">

			<div class="row my-2">
				<div class="col-md-6">
					<div class="form-group">
						<label for="exam-name" class="mb-1">Exam Name:</label> <input
							type="text" name="examName" value="${EXAM.examName }"
							id="exam-name" class="form-control" autocomplete="off">
					</div>

				</div>
				<div class="col-md-2">
					<div class="form-group">
						<label for="hour" class="mb-1">Exam {hour 0-23} :</label>
						<input type="text" name="hour" id="hour" class="form-control"
							value="${EXAM.localTime.getHour()}">
					</div>
				</div>
				<div class="col-md-2">
					<div class="form-group">
						<label for="min" class="mb-1">Exam {mins 0-59} :</label>
						<input type="text" name="min" id="min" class="form-control"
							value="${EXAM.localTime.getMinute()}">
					</div>
				</div>
				<div class="col-md-2">
					<div class="form-group">
						<label for="sec" class="mb-1">Exam {secs 0-59} :</label>
						<input type="text" name="sec" id="sec" class="form-control"
							value="${EXAM.localTime.getSecond()}">
					</div>
				</div>
			</div>

			<div class="row my-2">
				<div class="col-md-12">
					<div class="form-group">
						<label for="description" class="mb-1">Description:</label>
						<textarea name="description" id="exam-name" rows="3"
							class="form-control">${EXAM.description} </textarea>
					</div>

				</div>
			</div>

			<div class="text-center mt-3">
				<input type="submit" class="btn btn-success" value="Submit">
			</div>
		</form>


		<h2 class="text-center mb-1">EXAM LIST</h2>
	<table class="table table-hover table-bordered table-striped m-auto table-sm" id="myTable">
		<thead class="text-center text-white bg-secondary">
				<tr class="text-center">
					<th>S.No.</th>
					<th>Exam Id</th>
					<th>Exam Name</th>
					<th>Desc.</th>
					<th>Exam Time</th>
					<th>Action</th>
				</tr>
			</thead>

			<tbody>
				<c:forEach items="${EXAMLIST}" var="exam" varStatus="status">
					<tr>
						<td>${status.index+1}</td>
						<td>${exam.examId}</td>
						<td>${exam.examName}</td>
						<td>${fn:substring(exam.description, 0, 20)}...</td>
						<td>${exam.localTime}${fn:length(exam.localTime.toString()) < 6 ? ':00' : null}</td>
						
						<td><a
							href="http://localhost:8080/admin/exam/update/${exam.examId}"
							class="btn btn-sm btn-primary">Edit</a> &nbsp;&nbsp; <a
							href="http://localhost:8080/admin/exam/delete/${exam.examId}"
							class="btn btn-sm btn-danger">Delete</a></td>
					</tr>
				</c:forEach>
			</tbody>

		</table>

	</div>

</body>

</body>
</html>