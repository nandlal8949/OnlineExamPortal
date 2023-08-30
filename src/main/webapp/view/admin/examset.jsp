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
		<h1 class="text-center text-danger my-2">ExamSet Manage</h1>

		<form action="/admin/examset" method="post">
			<div class="row">
				<input type="hidden" name="examSetId" value="${EXAMSET.examSetId}"
					id="itemId">


				<div class="col-md-3">
					<div class="form-group">
						<label for="items" class="mb-1">Item :</label> <select
							name="items" id="items" class="form-control">
							<option value="0">-select-</option>
							<option value="0">All</option>
							<c:forEach items="${ITEMLIST}" var="item">
								<c:if test="${item.itemId eq EXAMSET.items.itemId}">
									<option value="${item.itemId }" selected>${item.itemName }</option>
								</c:if>
								<c:if test="${item.itemId ne EXAMSET.items.itemId }">
									<option value="${item.itemId }">${item.itemName }</option>
								</c:if>

							</c:forEach>
						</select>
					</div>

				</div>
				<div class="col-md-6">
					<div class="form-group">
						<label for="itemName" class="mb-1">Add ExamSet Name:</label> <input
							type="text" name="examSetName" value="${EXAMSET.examSetName}"
							id="itemName" class="form-control" autocomplete="off">
					</div>

				</div>
				<div class="col-md-3">
					<div class="form-group">
						<label for="timeLimit" class="mb-1">TimeLimits in Minutes:</label> <input
							type="text" name="timeLimit" value="${EXAMSET.timeLimit}"
							id="timeLimit" class="form-control" autocomplete="off">
					</div>

				</div>
			</div>


			<div class="row">
				<div class="col-md-3">
					<div class="form-group">
						<label for="objQty" class="mb-1">Objective Qty:</label> <input
							type="text" name="objQues" value="${EXAMSET.objQues}" id="objQty"
							class="form-control" autocomplete="off" onchange="validate()">
					</div>
				</div>
				<div class="col-md-3">
					<div class="form-group">
						<label for="subQty" class="mb-1">Subjective Qty:</label> <input
							type="text" name="subQues" value="${EXAMSET.subQues}" id="subQty"
							class="form-control" autocomplete="off" onchange="validate()">
					</div>
				</div>
				<div class="col-md-3">
					<div class="form-group">
						<label for="maxmark" class="mb-1">Max Marks:</label> <input
							type="text" name="totalScore" value="${EXAMSET.totalScore}"
							id="maxmark" class="form-control" autocomplete="off">
					</div>
				</div>
				<div class="col-md-3">
					<div class="form-group">
						<label for="minmark" class="mb-1">Min Marks:</label> <input
							type="text" name="passScore" value="${EXAMSET.passScore}"
							id="minmark" class="form-control" autocomplete="off">
					</div>
				</div>

			</div>


			<div class="text-center">
				<div class="form-group">
					<button onclick="return myFunction()" type="submit" id="btn" class="btn btn-success mt-1 px-5"
						> Submit </button>
				</div>
			</div>

		</form>


<h2 class="text-center mb-1">EXAMSET LIST</h2>
	<table class="table table-hover table-bordered table-striped m-auto table-sm" id="myTable">
		<thead class="text-center text-white bg-secondary">
				<tr class="text-center">
					<th>SN</th>
					<th>Id</th>
					<th>Name</th>
					<th>Category</th>
					<th>Obj</th>
					<th>Sub</th>
					<th>Pending</th>
					<th>Max.Marks</th>
					<th>Min.Marks</th>
					<th>Obj.Qty</th>
					<th>Sub.Qty</th>
					<th>Action</th>
				</tr>
			</thead>

			<tbody>
				<c:forEach items="${EXAMSETLIST}" var="examset" varStatus="status">
					<tr>
						<td>${status.index+1}</td>
						<td>${examset[0]}</td>
						<td>${examset[1]}</td>
						<td>${examset[2] ne null ? examset[2] : 'All Type' }</td>
						<td><p ${examset[3] > 0  ? 'style="color:red"' : 'style="font-size:15px;color:green;"'}> ${examset[3] > 0 ? examset[3] : '&#9989;'} </p></td>
						<td> <p ${examset[4] > 0  ? 'style="color:red"' : 'style="font-size:15px;color:green;"'}>${examset[4] > 0  ? examset[4] : '&#9989;'} </p></td>
						<td> <p ${examset[5] > 0  ? 'style="color:red"' : 'style="font-size:15px;color:green;"'}>${examset[5] > 0  ? examset[5] : '&#9989;'} </p></td>
						<td>${examset[6]}</td>
						<td>${examset[7]}</td>
						<td>${examset[8]}</td>
						<td>${examset[9]}</td>
						<td><a
							href="http://localhost:8080/admin/examset/view/${examset[0]}"
							class="btn btn-sm btn-success">View</a> &nbsp;&nbsp; <a
							href="http://localhost:8080/admin/examset/update/${examset[0]}"
							class="btn btn-sm btn-primary">Edit</a> &nbsp;&nbsp; <a
							onclick="return confirm('Are you sure ? related users will be delete')"
							href="http://localhost:8080/admin/examset/delete/${examset[0]}"
							class="btn btn-sm btn-danger">Delete</a></td>
					</tr>
				</c:forEach>
			</tbody>

		</table>

	</div>
		<script type="text/javascript">
		
		function validate() {
			let obj = $("#objQty").val();
	        let sub = $("#subQty").val();
	        let minmark = $("#minmark").val();
	        let maxmark = $("#maxmark").val();
	        let total = parseInt(sub)*3 + parseInt(obj);
	        $("#maxmark").val(total);
	        $("#minmark").val(Math.floor(total / 2));
	      }

	      function myFunction(){
	    	  let obj = $("#objQty").val();
	          let sub = $("#subQty").val();
	          let minmark = $("#minmark").val();
	          let maxmark = $("#maxmark").val();
				if(obj < 1 || sub < 1 || minmark < 1 || maxmark < 1){
					alert("please fill all fields")
					return false;
					}
		      }

		</script>

</body>
</html>