<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Add Question</title>
</head>
<body>
	<%@ include file="../navbar.jsp"%>
	<div class="fluid-container px-1">
		<h1 class="text-center text-danger my-2">Add Questions in Sets</h1>

		<form action="/admin/questions" method="post"
			class="justify-content-center">
			<input type="hidden" name="questionId" value="${QUESTION.questionId}">
			<div class="row my-2">

				<div class="col-md-4">
					<div class="form-group">
						<label for="items" class="mb-1">Item :</label> <select
							name="items" id="items" class="form-control">
							<option value="0">-select-</option>
							<c:forEach items="${ITEMLIST}" var="item">
								<c:if test="${item.itemId eq QUESTION.items.itemId }">
									<option value="${item.itemId }" selected>${item.itemName }</option>
								</c:if>
								<c:if test="${item.itemId ne QUESTION.items.itemId }">
									<option value="${item.itemId }">${item.itemName }</option>
								</c:if>

							</c:forEach>
						</select>
					</div>

				</div>
				<div class="col-md-4">
					<div class="form-group">
						<label for="subItem" class="mb-1">SubItem:</label> <select
							name="subItem" id="subItem" class="form-control">
							<option value="0">-select-</option>
							<c:forEach items="${SUBITEMLIST}" var="subItem">
								<c:if test="${subItem.subItemId eq QUESTION.subItem.subItemId}">
									<option value="${subItem.subItemId }" selected>${subItem.subItemName}</option>
								</c:if>
								<c:if test="${subItem.subItemId ne QUESTION.subItem.subItemId}">
									<option value="${subItem.subItemId}">${subItem.subItemName}</option>
								</c:if>

							</c:forEach>
						</select>
					</div>

				</div>

				<div class="col-md-4">
					<div class="form-group">
						<label for="questionType" class="mb-1">Question Type:</label> <select
							name="questionType" id="questionType" class="form-control">
							<option value="0">-select-</option>
							<c:forEach items="${TYPES}" var="type">
								<c:if
									test="${type.quesTypeId eq QUESTION.questionType.quesTypeId}">
									<option value="${type.quesTypeId}" selected>${type.quesTypeName}</option>
								</c:if>
								<c:if
									test="${type.quesTypeId ne QUESTION.questionType.quesTypeId}">
									<option value="${type.quesTypeId}">${type.quesTypeName}</option>
								</c:if>
							</c:forEach>
						</select>
					</div>

				</div>
			</div>
			<div class="row my-2 common"
				${(QUESTION.questionType.quesTypeId gt 0) ? 'style="display:flex"' : 'style="display:none"'}>

				<div class="col-md-1">
					<div class="form-group">
						<label for="questionText" class="mb-1">Question:</label>
					</div>

				</div>

				<div class="col-md-11">
					<div class="form-group">
						<textarea rows="1" name="questionText" id="questionText"
							class="form-control">${QUESTION.questionText}</textarea>
					</div>

				</div>
			</div>

			<div class="row my-2 obs"
				${QUESTION.questionType.quesTypeId eq 1 ? 'style="display:flex"' : 'style="display:none"'}>
				<div class="col-md-1">
					<div class="form-group">Code :</div>
				</div>
				<div class="col-md-11">
					<div class="form-group">
						<textarea name="questionCode" id="questionCode"
							class="form-control" rows="3">${QUESTION.questionCode }</textarea>
					</div>
				</div>
			</div>

			<div class="row my-2 common"
				${(QUESTION.questionType.quesTypeId gt 0) ? 'style="display:flex"' : 'style="display:none"'}>

				<div class="col-md-1">
					<div class="form-group">
						<label for="currectAnswer" class="mb-1">Answer:</label>
					</div>

				</div>

				<div class="col-md-11">
					<div class="form-group">
						<textarea rows="1" name="currectAnswer" id="currectAnswer"
							class="form-control">${QUESTION.currectAnswer}</textarea>
					</div>

				</div>
			</div>


			<table id="employee-table" ${QUESTION.questionType.quesTypeId eq 1 ? 'style="display:block"' : 'style="display:none"'}>

				<c:forEach begin="0"
					end="${QUESTION.answer.size() > 0 ? QUESTION.answer.size()-1 : 1}" var="ques" varStatus="status">
				<tr>
					<td width="9%">Option :</td>
					<td width="70%"><textarea rows="1" name="answerId" class="form-control">${QUESTION.answer[status.index].answerText}</textarea></td>
					<td width="10%"><input type="button" class="btn btn-sm btn-success mx-3" value="Before &plus;" onclick="addRowBefore(this)" /></td>
					<td width="10%"><input type="button" class="btn btn-sm btn-success ms-1" value="After &plus;" onclick="addRowAfter(this)" /></td>
					<td width="10%"><input type="button" class="btn btn-sm btn-danger ms-1" value="X"	onclick="deleteRow(this)" /></td>
				</tr>
				</c:forEach>
			</table>

			<div class="text-center my-2">
				<input type="button" class="btn btn-success" id="save" value="Submit">
			</div>

		</form>


<h2 class="text-center my-1">QUESTION LIST</h2>
	<table class="table table-hover table-bordered table-striped m-auto table-sm" id="myTable">
		<thead class="text-center text-white bg-secondary">
				<tr>
					<th>Q.Id</th>
					<th>Question</th>
					<th>Status</th>
					<th>Type</th>
					<th>Category</th>
					<th>S.Category</th>
					<th>Action</th>
				</tr>
			</thead>

			<tbody>
				<c:forEach items="${QUESTIONLIST}" var="question" varStatus="index">
					<tr>
						<td>${question.questionId}</td>
						<td>${fn:substring(question.questionText,0,50)}..</td>
						<td>
						<c:if test="${question.questionStatus eq 'Approved'}"> <span class="text-success"> ${question.questionStatus} </span>  </c:if>
						<c:if test="${question.questionStatus eq 'Pending'}"> <a href="http://localhost:8080/admin/questions/approve/${question.questionId}" class="text-danger"> ${question.questionStatus} </a>  </c:if>
						</td>
						<td>${question.questionType.quesTypeName}</td>
						<td>${question.items.itemName}</td>
						<td>${question.subItem.subItemName}</td>

						<td><a
							href="http://localhost:8080/admin/questions/update/${question.questionId}"
							class="btn btn-sm btn-primary">Edit</a> &nbsp;&nbsp; <a
							href="http://localhost:8080/admin/questions/delete/${question.questionId}"
							class="btn btn-sm btn-danger">Delete</a></td>
					</tr>
				</c:forEach>
			</tbody>

		</table>

	</div>
	<script>
	$("#save").on("click", function(){
		var arr = $("form").serializeArray();
		
 		$.post("http://localhost:8080/admin/questions/save", {
			temp : JSON.stringify(arr)
		}, function(data) {
			alert(data);
			window.location.href = "/admin/questions";
		}) 

		})
		
	function addRowBefore(ele){
	      var table = $('#employee-table')[0];
	      var rowCount = table.rows.length;
	              
	        var rowHtml='<tr><td width="9%">Option :</td>'
	          +'<td width="70%"><textarea rows="1" name="answerId" class="form-control" ></textarea></td>'
	          +'<td width="10%"><input type="button" class="btn btn-sm btn-success mx-3" value="Before &plus;" onclick="addRowBefore(this)" /></td>'
	          +'<td width="10%"><input type="button" class="btn btn-sm btn-success ms-1" value="After &plus;" onclick="addRowAfter(this)" /></td>'
	          +'<td width="10%"><input type="button" class="btn btn-sm btn-danger ms-1" value="X" onclick="deleteRow(this)" /></td></tr>'
	          $(ele).parent().parent().before(rowHtml);
	    }

	function addRowAfter(ele){
	      var table = $('#employee-table')[0];
	      var rowCount = table.rows.length;
	              
	      var rowHtml='<tr><td width="9%">Option :</td>'
	          +'<td width="70%"><textarea rows="1" name="answerId" class="form-control" ></textarea></td>'
	          +'<td width="10%"><input type="button" class="btn btn-sm btn-success mx-3" value="Before &plus;" onclick="addRowBefore(this)" /></td>'
	          +'<td width="10%"><input type="button" class="btn btn-sm btn-success ms-1" value="After &plus;" onclick="addRowAfter(this)" /></td>'
	          +'<td width="10%"><input type="button" class="btn btn-sm btn-danger ms-1" value="X" onclick="deleteRow(this)" /></td></tr>'
	          $(ele).parent().parent().after(rowHtml);
	    }
	     
	    function deleteRow(ele){
	        var table = $('#employee-table')[0];
	        var rowCount = table.rows.length;
	        if(rowCount <= 1){
	            alert("Single Row Can not delete!");
	            return;
	        }
	        if(ele){
	            $(ele).parent().parent().remove();
	        }
	        else{
	            table.deleteRow(rowCount-1);
	        }
	    }
	     /* Add row and delete code above */

		var subjectives = $("#subjectives").val();
		var objectives = $("#objectives").val();
		$("#items").on(
				"change",
				function() {
					$.get("http://localhost:8080/admin/questions/"
							+ $(this).val(), function(data) {
						$("#subItem").html(data);
					})
				})

		$("#questionType").on("change", function() {
			var items = $("#items").val();
			var subItem = $("#subItem").val();
			var quesType = $("#questionType").val();
			if (items != 0 && subItem != 0) {
				if (quesType == 1) {
					$(".common").css("display", "flex");
					$(".obs").css("display", "flex");
					$("#employee-table").css("display", "block");

				} else if (quesType == 2) {
					$(".common").css("display", "flex");
					$(".obs").css("display", "none");
					$("#employee-table").css("display", "none");
				} else {
					$(".common").css("display", "none");
					$(".obs").css("display", "none");
					$("#employee-table").css("display", "none");

				}
			}
		})

		$("textarea").on("focusout", function() {
			$(this).val($.trim($(this).val()));
		});
	</script>

</body>


</html>