<head>
	<style>
		input[type='radio']{
    transform: scale(1.5);
    margin-bottom: 15px;}   /* Apply some bottom margin */
	.args { color: red;}
.key { color: #00F;}
.braces { color: #04A91C;}

}
	</style>
</head>

<%@ include file="../navbar.jsp"%>
<body class="bg-light">

	<div class="fluid-container px-1 table-success">
		<div class="row py-2">
			<div class="offset-md-2 col-md-6">
				<h5 class="text-center">${CANDIDATE.exam.examName}(${CANDIDATE.examSet.items.itemName})</h5>
			</div>
			<div class="col-md-4">
				<span id="candId" style="display: none">${CANDIDATE.candId}</span>
				<span id="date" style="display: none">${CANDIDATE.candExamDate}</span>
				<span id="time" style="display: none">${CANDIDATE.exam.localTime}</span>
				<span id="localtime" style="display: none">${CANDIDATE.examSet.timeLimit}</span>
				<h5 class="text-right">Remaining Time :-  <span id="timeid"></span></h5>
    <script>
      let date = $("#date").text();
      let time = $("#time").text();
      let localtime = $("#localtime").text();
      let d1 = new Date(date.slice(0,4),date.slice(5,7)-1, date.slice(8,10),time.slice(0,2),time.slice(3,5), time.slice(6,8)).getTime();
      let d2 = new Date().getTime();
      d1 = d1/1000;
      d2 = d2/1000;
      d1 = d1 + (localtime *60);
      let diff = d1 - d2;
          setInterval(() => {
            diff--;
            let sec = Math.floor(diff%60);
            let min = Math.floor(diff/60)%60;
            let hour =  Math.floor(diff/(60*60))%24;
            
            if(diff < 1){
              window.location.href = "http://localhost:8080/logout";
                }
            if(diff > (localtime/2)*60){
            	$("#timeid").css("color","green");
                }
            else if(diff <= (localtime/2)*60 && diff > (localtime/4)*60 ){
            	$("#timeid").css("color","orange");
                }else if(diff <= (time/4)*60){
            $("#timeid").css("color","red");
             }else{
            $("#timeid").css("color","green");
                 }
            $("#timeid").text((hour < 10 ? "0"+hour : hour)+":"+(min < 10 ? "0"+min : min)+":"+(sec < 10 ? "0"+sec : sec));
          }, 1000);
        </script>
			</div>
		</div>
		<p>Description :- ${CANDIDATE.exam.description}</p>
		<div class="row text-center py-2">
			<div class="col-md-2">
				<h6>Max.Marks:- ${CANDIDATE.examSet.totalScore}</h6>
			</div>
			<div class="col-md-2">
				<h6>Min.Marks :- ${CANDIDATE.examSet.passScore}</h6>
			</div>
			
			<div class="col-md-4">
				<h6>Questions :- Objective(${CANDIDATE.examSet.objQues}), Subjective(${CANDIDATE.examSet.subQues}) </h6>
			</div>

			<div class="col-md-3">
				<h6>Type :- ${QUESTION.subItem.subItemName}(${QUESTION.questionType.quesTypeName})</h6>
			</div>
			<div class="col-md-1">
				<h6>Marks:-${QUESTION.questionType.quesTypeId eq 1 ? 1.0 : 3.0 }
				</h6>
			</div>
		</div>

	<!-- question set -->
	<form action="/candidate/test" method="post"
		class="table-secondary pt-3">
		<input type="hidden" name="questionId" value="${QUESTION.questionId}">
		<input type="hidden" name="candidateId" value="${SESSION1.candId}">
		<div class="row my-2"
			${(QUESTION.questionType.quesTypeId eq 2 or QUESTION.questionType.quesTypeId eq 1) ? 'style="display:flex"' : 'style="display:none"'}>

			<div class="col-md-1">
				<div class="form-group">
					<h4>Q. ${QUESTIONNO}.</h4>

				</div>

			</div>

			<div class="col-md-9">
				<div class="form-group">
					<h4>${QUESTION.questionText}</h4>
				</div>

			</div>
			<div class="col-md-1">
				<div class="form-group">
					<a href="http://localhost:8080/candidate/test/${CANDIDATE.candId}" class="btn btn-secondary btn-sm"> Refresh </a>
				</div>

			</div>
		</div>

		<div class="row my-2"
			${QUESTION.questionType.quesTypeId eq 2 ? 'style="display:flex"' : 'style="display:none"'}>

			<div class="col-md-2">
				<div class="form-group">
					<h4>Answer :</h4>

				</div>

			</div>

			<div class="col-md-10">
				<div class="form-group">
					<textarea name="candidateAnswer1" rows="1" class="form-control"> </textarea>
				</div>

			</div>
		</div>


		<div class="row my-2"
			${QUESTION.questionType.quesTypeId eq 1 && fn:length(QUESTION.questionCode) > 1 ? 'style="display:flex"' : 'style="display:none"'}>
			<div class="col-md-2">
				<div class="form-group">
					<h4>Code:</h4>
				</div>

			</div>

			<div class="col-md-10">
				<div class="form-group">
					 ${QUESTION.questionCode}

				</div>

			</div>
		</div>

		<div class="row my-2 px-5"
			${QUESTION.questionType.quesTypeId eq 1 ? 'style="display:flex"' : 'style="display:none"'}>
			<c:forEach items="${QUESTION.answer}" var="option" varStatus="status">
				<div class="offset-md-1 col-md-5 py-3">
					<input type="radio" name="candidateAnswer" id="answerText${status.index }"
						value="${option.answerText}"> <label class="h5 mx-1" for="answerText${status.index }">
						${option.answerText}</label>
				</div>
			</c:forEach>
		</div>
		<div class="text-center pb-5">
			<button class="btn btn-success" id="save">${FINISHBUTTON eq null ? 'Save & Next' : FINISHBUTTON}</button>
		</div>


	</form>
	</div>
	
	<c:if test="${EXAMFINISHED ne null }">
			<c:redirect url = "http://localhost:8080/candidate/result/${CANDIDATE.candId }"/>
	</c:if>

	<script type="text/javascript">
		$("textarea").on("input", function() {
			$(this).outerHeight(38).outerHeight(this.scrollHeight);
		});
	</script>
</body>
</html>