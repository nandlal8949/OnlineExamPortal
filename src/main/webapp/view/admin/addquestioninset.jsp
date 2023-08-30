<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Add Question</title>
	<style>
		input[type='checkbox']{
    transform: scale(1.5);}   /* Apply some bottom margin */
}
	</style>
</head>
<body>
	<%@ include file="../navbar.jsp"%>
	<div class="fluid-container mx-3">
		<h1 class="text-center text-danger my-3">Add Question in ExamSet</h1>
		<c:if test="${SETINFO.size() > 0 }">
			<div class="row table-primary text-center p-1 border border-dark">
				<div class="col-md-3">
					ExamSet-<span class="text-primary"> ${SETINFO[0]} </span>
				</div>
				<div class="col-md-1">
					Obj.Q.-<span class="text-primary">${SETINFO[1]} </span>
				</div>
				<div class="col-md-2">
					Sub.Q.-<span class="text-primary">${SETINFO[2]} </span>
				</div>
				<div class="col-md-3">
					Pending Obj.Q.-<span class="text-danger">${EXAMSET.objQues-SETINFO[1]}</span>
				</div>
				<div class="col-md-3">
					Pending Sub.Q.-<span class="text-danger">${EXAMSET.subQues-SETINFO[2]}</span>
				</div>
			</div>
		</c:if>
		<input type="hidden" id="examSet11" value="${EXAMSET.examSetId}">
		<form action="/admin/addquestioninset/search" method="post"
			class="justify-content-center">
			<div class="row my-2">
				<div class="col-md-3">
					<div class="form-group">
						<label for="examSet" class="mb-1">Exam Set Name :</label> <select
							name="examSet" id="examSet" class="form-control">
							<option value="0">-select-</option>
							<c:forEach items="${EXAMSETLIST}" var="examSet">
								<option value="${examSet.examSetId}">
									${examSet.examSetName }</option>
							</c:forEach>

						</select>
					</div>

				</div>
				<div class="col-md-2">
					<div class="form-group">
						<label for="items" class="mb-1">Item :</label> <select
							name="items" id="items" class="form-control">
							<option value="0">-select-</option>
							<c:if test="${ITEMS.itemId lt 4}">
								<option value="${ITEMS.itemId}">${ITEMS.itemName}</option>
							</c:if>

						</select>
					</div>
				</div>


				<div class="col-md-3">
					<div class="form-group">
						<label for="subItem" class="mb-1">SubItem:</label> <select
							name="subItem" id="subItem" class="form-control">
							<option value="0">-select-</option>

						</select>
					</div>

				</div>

				<div class="col-md-2">
					<div class="form-group">
						<label for="questionType" class="mb-1">Question Type:</label> <select
							name="questionType" id="questionType" class="form-control">
						</select>
					</div>

				</div>

				<div class="col-md-2 mt-4">
					<div class="form-group">
						<button class="btn btn-success mt-1 w-100" id="btn3">Search</button>
					</div>

				</div>
			</div>



		</form>
		<c:if test="${EXAMSET.examSetName ne null}">
			<div class="display-4 text-center mb-1">${EXAMSET.examSetName}
				${EXAMSET.items.itemName} LIST</div>
			<form action="/admin/addquestioninset/update" method="post">

				<input type="hidden" name="examSet" value="${EXAMSET.examSetId}">
				<input type="hidden" name="items" value="${ITEMS.itemId}">
				<table class="table table-hover table-bordered table-striped m-auto table-sm" id="myTable">
					<thead class="text-center text-white bg-secondary">
						<tr>
							<th>##</th>
							<th>S.N.</th>
							<th>Question</th>
							<th>Status</th>
							<th>Item</th>
							<th>Question Type</th>
							<th>Sub Category</th>
						</tr>
					</thead>

					<tbody>
						<c:forEach items="${QUESTIONLIST}" var="question"
							varStatus="status">
							<tr>
								<td><input type="checkbox" name="questionId"
									value="${question.questionId }"></td>
								<td>${status.index+1}.</td>
								<td class="w-50">(${question.questionId}).
									${question.questionText}</td>
								<td>${question.questionStatus}</td>
								<td>${question.items.itemName}</td>
								<td>${question.questionType.quesTypeName}</td>
								<td>${question.subItem.subItemName}</td>

							</tr>
						</c:forEach>
					</tbody>

				</table>
				<div class="mx-auto my-3 w-25 text-center">
					<input type="button" value="Save" id="save"
						class="btn btn-success w-50">
				</div>
			</form>
		</c:if>


	</div>
	<script>
		$("#myTable tr").on("click", function(event){
				if(event.target.type !== 'checkbox'){
					$(":checkbox", this).trigger("click");
					}
			});


	
		$("#save").on("click", function(event) {
			event.preventDefault();
			var arr = $("form").serializeArray();
			var arr1 = [];
			arr.forEach(function(value, index, arr) {
				if (index > 4) {
					arr1.push({
						questionId : value.value,
						examCodeId : arr[3].value
					});
				}
			});

			$.post("http://localhost:8080/admin/addquestioninset/update", {
				examSet : $("#examSet11").val(),
				temp : JSON.stringify(arr1)
			}, function(data) {
				alert(data);
				window.location.href = "/admin/addquestioninset";
			})

		})

		var subjectives = $("#subjectives").val();
		var objectives = $("#objectives").val();

		$("#examSet").on(
				"change",
				function() {
					$.get(
							"http://localhost:8080/admin/addquestioninset/examsetid/"
									+ $(this).val(), function(data) {
								$("#items").html(data);
							})
				})

		$("#items").on(
				"change",
				function() {
					$.get(
							"http://localhost:8080/admin/addquestioninset/examsetitem/"
									+ $("#examSet").val() + "/"
									+ $("#items").val(), function(data) {
								if (data.length > 10) {
									$("#subItem").html(data);
								} else {
									$("#subItem").html("");
								}

							});
				})

		$("#subItem").on(
				"change",
				function() {
					$.get(
							"http://localhost:8080/admin/addquestioninset/questiontype/"
									+ $("#subItem").val(), function(data) {
								$("#questionType").html(data);
							});
				})

		$("#btn3").on("click", function(event) {

			var examSet = $("#examSet").val();
			var items = $("#items").val();

			if (examSet > 0 && items > 0) {
				return true;
			} else {
				alert("Fill All Field");
				return false;
			}
		})
	</script>

</body>


</html>