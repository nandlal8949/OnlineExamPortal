<style>
	.args {
    color: red;
}
.key {
    color: #00F;
}
.braces {
    color: #04A91C;
}
</style>

<%@ include file="../navbar.jsp"%>
<body class="bg-light">

		<div class="fluid-container px-1 table-light">
		<div class="row py-2">
			<div class="col-md-4">
				<h5 class="text-center">${CANDIDATE.exam.examName}(${CANDIDATE.examSet.items.itemName})</h5>
			</div>
			<div class="col-md-4">
				<h3 class="text-center">RESULT</h3>
			</div>
			<div class="col-md-4">
				<h5 class="text-right">Time :- ${CANDIDATE.exam.localTime}</h5>
			</div>
		</div>
		<p>Description :- ${CANDIDATE.exam.description} temp this is
			Descriptiontemp this is Descriptiontemp this is Descriptiontemp this
			is Descriptiontemp this is Descriptiontemp this is Descriptiontemp
			this is Descriptiontemp this is Descriptiontemp this is Description</p>
		<div class="row text-center py-2">
			<div class="col-md-4">
			<h6>Questions :- Objective(${CANDIDATE.examSet.objQues}), Subjective(${CANDIDATE.examSet.subQues}) </h6>
			</div>
			<div class="col-md-4">
			<h6>Max.Marks:- ${CANDIDATE.examSet.totalScore}</h6>
			</div>
			
			<div class="col-md-4">
				<h6>Min.Marks :- ${CANDIDATE.examSet.passScore} &nbsp;
				<c:if test="${PRINTVALID eq false}">
				<a class="btn btn-sm btn-primary" href="http://localhost:8080/candidate/resultprint/${CANDIDATE.candId }">Print</a>				
				</c:if>
				 </h6>
			</div>
		</div>
	

	<!-- question set -->
	<h1 class="text-center bg-primary py-2 text-white"
		${OBJECTLIST.size() > 0 ? 'style="display:block"' : 'style="display:none"' }>Objective Questions</h1>


	<c:forEach items="${OBJECTLIST}" varStatus="status" var="objQues">
		<div class="row my-2">

			<div class="col-md-2">
				<div class="form-group">
					<h4>Question :- ${status.index +1 }</h4>

				</div>

			</div>

			<div class="col-md-10">
				<div class="form-group">
					<h4>${objQues[0]}</h4>
				</div>

			</div>
		</div>

		<div class="row my-2"
			${fn:length(objQues[1]) > 1 ? 'style="display:flex"' : 'style="display:none"'}>
			<div class="col-md-2">
				<div class="form-group">
					<h4>Code:</h4>
				</div>

			</div>

			<div class="col-md-10">
				<div class="form-group">
					<h4 >${objQues[1]}</h4>

				</div>

			</div>
		</div>
		<div class="row my-2">
			<div class="col-md-2">
				<div class="form-group">
					<h6>Your Answer :</h6>
				</div>

			</div>

			<div
				class="col-md-10 ${objQues[2] eq objQues[3] ? 'table-success' : 'table-danger'}">
				<div class="form-group">
					<h6>${objQues[3]}</h6>
				</div>

			</div>
		</div>
		<div class="row my-2"
			${objQues[2] eq objQues[3] ? 'style="display:none"' : 'style="display:flex"'}>
			<div class="col-md-2">
				<div class="form-group">
					<h6>Currect Answer :</h6>
				</div>

			</div>

			<div class="col-md-10 table-primary">
				<div class="form-group">
					<h6>${objQues[2]}</h6>
				</div>

			</div>
		</div>
				<div class="row my-2">
			<div class="col-md-2">
				<div class="form-group">
					<h6>Get Marks :</h6>
				</div>

			</div>

			<div class="col-md-10 ${objQues[4] eq 1 ? 'table-success' : 'table-danger'}">
				<div class="form-group">
					<h6>${objQues[4]}</h6>
				</div>

			</div>
		</div>
		<hr class="border-primary">

	</c:forEach>


	<!-- subjective list -->
	<h1 class="text-center bg-primary py-2 text-white"
		${SUBJECTLIST.size() > 0 ? 'style="display:block"' : 'style="display:none"' }>Subjective Questions</h1>

	<c:forEach items="${SUBJECTLIST}" varStatus="status" var="objQues">
		<div class="row my-2">

			<div class="col-md-2">
				<div class="form-group">
					<h4>Question :- ${status.index +1 }</h4>

				</div>

			</div>

			<div class="col-md-10">
				<div class="form-group">
					<h4>${objQues[0]}</h4>
				</div>

			</div>
		</div>

		<div class="row my-2"
			${fn:length(objQues[1]) > 1 ? 'style="display:flex"' : 'style="display:none"'}>
			<div class="col-md-2">
				<div class="form-group">
					<h4>Your Answer:</h4>
				</div>

			</div>

			<div class="col-md-10">
				<div class="form-group">
					<textarea rows="2" class="form-control" disabled> ${objQues[1]} </textarea>

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
					<textarea class="form-control" disabled oninput='this.style.height = "";this.style.height = this.scrollHeight + "px"'> ${objQues[2]} </textarea>
				</div>

			</div>
		</div>
		<div class="row my-2">
			<div class="col-md-2">
				<div class="form-group">
					<h6>Get Marks :</h6>
				</div>

			</div>

			<div class="col-md-10">
				<div class="form-group">
					<h6>${objQues[3] == -1.0 ? 'Quection Not Checked' : objQues[3]}</h6>
				</div>

			</div>
		</div>
		<hr class="border-primary">

	</c:forEach>
	</div>
</body>
</html>