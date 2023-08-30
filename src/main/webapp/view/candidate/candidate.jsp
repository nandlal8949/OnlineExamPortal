<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>DashBoard</title>
</head>
<body>
	<%@ include file="../navbar.jsp"%>
	<div class="container">
		<p id="candId" style="display: none">${SESSION1.candId}</p>
		<h1 class="text-center text-light bg-secondary ">Welcome
			${SESSION1.candFirstname } ${SESSION1.candLastname}</h1>
		<div class="row border border-dark px-1 mx-1 py-2">
			<div class="col-md-12 p-0">
				<div class="h1 table-secondary text-dark m-0 my-1 p-1 text-center">
					Notification</div>
				<h1>Exam Name :- ${SESSION1.exam.examName }
					(${SESSION1.examSet.items.itemName })</h1>
				<h1>Exam Date :- <span id="date">${SESSION1.candExamDate}</span></h1>
				<h1>Exam Time :- <span id="time">${SESSION1.exam.localTime}</span>(24 hours.)</h1>
				<h1>Time :- ${SESSION1.examSet.timeLimit} Mins.</h1>

				<c:if test="${ALREADYCOMPLETE eq 0 && RESULT eq 'PRESENTDATE'}">
					<h1> Remaining Time :-  <span id="timeid"></span> </h1>
					<script>
	let date = $("#date").text();
	let time = $("#time").text();
      let d1 = new Date(date.slice(0,4),date.slice(5,7)-1, date.slice(8,10),time.slice(0,2),time.slice(3,5), time.slice(6,8)).getTime();
      let d2 = new Date().getTime();
      d1 = d1/1000;
      d2 = d2/1000;
      let diff = d1 - d2;
		console.log(diff);
      setInterval(() => {
        diff--;

        if(diff > 60*45 ){
        	$("#timeid").css("color", "green");
            }
        if(diff < 60*45 && diff >=  60*15){
        	$("#timeid").css("color", "orange");
            }else
        if(diff < 60 * 15 && diff >= 2){
        	$("#timeid").css("color", "red");
            }else
        if(diff < 1){
        	window.location.href = "http://localhost:8080/candidate/test/"+$("#candId").text();
            }
        let sec = Math.floor(diff%60);
        let min = Math.floor(diff/60)%60;
        let hour =  Math.floor(diff/(60*60))%24;
        $("#timeid").text((hour < 10 ? "0"+hour : hour)+":"+(min < 10 ? "0"+min : min)+":"+(sec < 10 ? "0"+sec : sec));
      }, 1000);
    </script>
				</c:if>
				<c:if test="${RESULT eq 'PASTDATE' or ALREADYCOMPLETE eq 1 }">	
					<p> Your Exam is Finished Click to Result  <a class="btn btn-sm btn-success" href="http://localhost:8080/candidate/result/${SESSION1.candId}">Result</a> </p>
				</c:if>
				
				<c:if test="${RESULT eq 'NEXTDATE' }">	
					<p> Your Exam in Future Date </p>
				</c:if>
				<div class="h1 table-secondary text-dark m-0 my-1 p-1 text-center">
					Help</div>
			</div>
		</div>

	</div>
</body>
</html>