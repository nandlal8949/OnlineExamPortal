<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
	<title>DashBoard</title>
</head>
<body>
	<%@ include file="../navbar.jsp" %>
	
	<div class="row justify-content-center py-5">
			<div class="col-md-3 table-primary p-0 mx-3 border border-secondory">
				<h5 class="bg-secondary text-white text-center py-2">Important All Links</h5>
				<div class="row">
					<c:forEach items="${OTHERMENUS}" var="menus" begin="5">
						<div class="col-md-12">
							<span class="p-2"><a
								class="text-decoration-none color-primary"
								href="http://localhost:8080/${menus.iconLink}"> *
									${menus.iconText} </a></span>
						</div>
						<hr>
					</c:forEach>

				</div>

			</div>


						<div class="col-md-3 bg-light p-0 mx-3 border border-secondory">
				<h5 class="bg-secondary text-white text-center py-2">Pending
					Task</h5>
				<div class="row">
					<c:if test="${SUBJECTIVEPENDING > 0}">
						<div class="col-md-12">
							<span class="p-2"><a
								class="text-decoration-none text-danger"
								href="http://localhost:8080/${OTHERMENUS[6].iconLink}"> *
									${OTHERMENUS[6].iconText} (${SUBJECTIVEPENDING}) </a></span>
						</div>
					</c:if>
					<hr>
					
					<c:if test="${PENDINGQUESTIONS > 0}">
						<div class="col-md-12">
							<span class="p-2"><a
								class="text-decoration-none text-danger"
								href="http://localhost:8080/${OTHERMENUS[9].iconLink}"> *
									${OTHERMENUS[9].iconText}(${PENDINGQUESTIONS}) </a></span>
						</div>
					</c:if>
					<hr>
				
					<c:if test="${UNCOMPLETECANDIDATESET > 0}">
						<div class="col-md-12">
							<span class="p-2"><a
								class="text-decoration-none text-danger"
								href="http://localhost:8080/${OTHERMENUS[7].iconLink}"> *
									${OTHERMENUS[7].iconText} (${UNCOMPLETECANDIDATESET}) </a></span>
						</div>
					</c:if>
					<hr>
				
				
					<c:if test="${UNCOMPLEEXAMSET > 0 }">
						<div class="col-md-12">
							<span class="p-2"><a
								class="text-decoration-none text-danger"
								href="http://localhost:8080/${OTHERMENUS[8].iconLink}"> *
									${OTHERMENUS[8].iconText} (${UNCOMPLEEXAMSET}) </a></span>
						</div>
					</c:if>
					<hr>
					
					<c:if test="${UNCOMPLEEXAMSET > 0 }">
						<div class="col-md-12">
							<span class="p-2"><a
								class="text-decoration-none text-danger"
								href="http://localhost:8080/${OTHERMENUS[9].iconLink}"> *
									${OTHERMENUS[9].iconText} (${UNCOMPLETEQUESTION}) </a></span>
						</div>
					</c:if>
					<hr>

				</div>

			</div>


			

			<div class="col-md-3 bg-light p-0 mx-3 border border-secondory">
				<h5 class="bg-secondary text-white text-center py-2">
					Informations</h5>
				<div class="row">
						<div class="col-md-12">
							<span class="p-2"><a
								class="text-decoration-none text-primary"
								href="http://localhost:8080/${OTHERMENUS[10].iconLink}"> *
									${OTHERMENUS[10].iconText} (${TOTALCANDIDATE}) </a></span>
						</div>
						<hr>
						<div class="col-md-12">
							<span class="p-2"><a
								class="text-decoration-none text-primary"
								href="http://localhost:8080/${OTHERMENUS[11].iconLink}"> *
									${OTHERMENUS[11].iconText} (${TOTALQUESTIONS}) </a></span>
						</div>
						<hr>

				</div>

			</div>

		</div>


</body>
</html>