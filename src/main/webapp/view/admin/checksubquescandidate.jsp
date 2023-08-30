<%@ include file="../navbar.jsp"%>
<body class="bg-light">
	<div class="fluid-container px-5 table-primary">
		<div class="row py-2">
			<div class="offset-md-4 col-md-4">
				<h3 class="text-center">${CANDIDATE.exam.examName}(${CANDIDATE.examSet.items.itemName})</h3>
			</div>
			<div class="col-md-4">
				<h3 class="text-right">Time :- ${CANDIDATE.exam.localTime}</h3>
			</div>
		</div>
		<p>Description :- ${CANDIDATE.exam.description} temp this is
			Descriptiontemp this is Descriptiontemp this is Descriptiontemp this
			is Descriptiontemp this is Descriptiontemp this is Descriptiontemp
			this is Descriptiontemp this is Descriptiontemp this is Description</p>
		<div class="row text-center py-2">
			<div class="col-md-4">
				<h6>Max.Marks:- ${CANDIDATE.examSet.totalScore}</h6>
			</div>
			<div class="col-md-4">
				<h6>Min.Marks :- ${CANDIDATE.examSet.passScore}</h6>
			</div>
			
			<div class="col-md-4">
				<h6>Questions :- Objective(${CANDIDATE.examSet.objQues}), Subjective(${CANDIDATE.examSet.subQues}) </h6>
			</div>
		</div>
	</div>

	<!-- subjective list -->
	<h1 class="text-center bg-primary py-2 text-white"}>Subjective
		Questions</h1>
		<input type="hidden" id="candId" value="${CANDIDATE.candId}">
		<input type="hidden" id="examSetId" value="${CANDIDATE.examSet.examSetId}">
	<form>
		
		<c:forEach items="${QUESTION}" varStatus="status" var="ques">
			<div class="row my-2">

				<div class="col-md-2">
					<div class="form-group">
						<h4>Question :- ${status.index +1 } </h4>

					</div>

				</div>

				<div class="col-md-10">
					<div class="form-group">
						<h4>${ques[1]}</h4>
					</div>

				</div>
			</div>

			<div class="row my-2">
				<div class="col-md-2">
					<div class="form-group">
						<h4>Your Answer:</h4>
					</div>

				</div>

				<div class="col-md-10">
					<div class="form-group">
						<textarea rows="2" class="form-control" disabled> ${ques[2]} </textarea>

					</div>

				</div>
			</div>
			<div class="row my-2">
				<div class="col-md-2">
					<div class="form-group">
						<h6>Currect Answer :</h6>
					</div>

				</div>

				<div class="col-md-10">
					<div class="form-group">
						<textarea rows="2" class="form-control" disabled> ${ques[3]} </textarea>
					</div>

				</div>
			</div>
			<div class="row my-2">
				<div class="col-md-2">
					<div class="form-group">
						<h6>Give Marks :</h6>
					</div>

				</div>
				<div class="col-md-2">
					<div class="form-group">
						<select name="${ques[0]}" class="form-control">
							<option value="0">0</option>
							<option value="1">1</option>
							<option value="2">2</option>
							<option value="3">3</option>
						</select>
					</div>

				</div>
			</div>
			<hr class="border-primary">

		</c:forEach>

		<div class="text-center">
			<input type="button" value="Save" class="btn btn-outline-primary">
		</div>

	</form>
	<script type="text/javascript">
		$("input:button").on("click", function(event) {
			event.preventDefault();
			var arr = $("form").serializeArray();
			var arr1 = [];
			arr.forEach(function(value, index, arr) {
					arr1.push({
						questionId : value.name,
						marks : arr[index].value
					});
			});
			 $.post("http://localhost:8080/admin/checksubques/"+$("#candId").val()+"/"+$("#examSetId").val(), {
				temp : JSON.stringify(arr1)
			}, function(data) {
				alert(data);
				 window.location.href = "/admin/checksubques";  
			}) 

		});
	</script>

</body>
</html>