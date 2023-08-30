<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Exams Category</title>
</head>
<body>
	<%@ include file="../navbar.jsp"%>
	<div class="container">
		<h1 class="text-center text-danger my-2">Candidate Assignment
			Manage</h1>

		<form action="/admin/candidate/assignment" method="post">

			<div class="row my-2">
				<input type="hidden" name="candId" value="${CANDINFO.candId}"
					id="userId">
				<div class="col-md-4">
					<div class="form-group">${CANDINFO.candFirstname}
						${CANDINFO.candLastname}</div>
				</div>
				<div class="col-md-4">
					<div class="form-group">
						<select name="examSet" id="exam-set-id" class="form-control">
							<option value="0">-select-</option>
							<c:forEach items="${EXAMSETLIST}" var="examset"
								varStatus="status">
								<c:if test="${examset.examSetId eq CANDINFO.examSet.examSetId }">
									<option value="${examset.examSetId}" selected>${examset.examSetName}
									</option>
								</c:if>
								<c:if test="${examset.examSetId ne CANDINFO.examSet.examSetId}">
									<option value="${examset.examSetId}">${examset.examSetName}
									</option>
								</c:if>
							</c:forEach>
						</select>
					</div>

				</div>
				<div class="col-md-4">
					<div class="form-group">
						<input type="submit" class="btn btn-success" value="Submit">
					</div>

				</div>


			</div>

		</form>
	</div>


</body>

</body>
</html>