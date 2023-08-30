<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Exams Category</title>   
</head>
<body>
	<%@ include file="../navbar.jsp"%>
	<div class="container-fluid">
		<!-- Modal -->
		<div class="modal fade" id="myModal">
			<div class="modal-dialog modal-dialog-centered">
				<div class="modal-content">
					<!-- Modal Header -->
					<div class="modal-header">
						<h4 class="modal-title">Add Role</h4>
						<button type="button" class="close" data-dismiss="modal">
							&times;</button>
					</div>

					<!-- Modal Body -->
					<div class="modal-body">
						<input type="hidden" id="itemId" name="itemId"/>
						<input type="text" id="itemName" name="itemName" placeholder="Role Name"
							class="form-control" autocomplete="false"/>
					</div>

					<!-- Modal Footer -->
					<div class="modal-footer">
						<button type="button" class="btn btn-danger" data-dismiss="modal">
							Cancel</button>
						<button type="button" id="saveBtn" class="btn btn-success">Save</button>
					</div>
				</div>
			</div>
		</div>
		<div class="row h1">
			<div class="col-md-4">
				<button type="button" class="btn btn-primary w-50" id="openModalBtn">
					Add Role</button>
			</div>
			<div class="col-md-4 text-center">
				<span> Employee Data </span>
			</div>
		</div>

			<table id="example" class="display border table-bordered">
				<thead class="table-secondary">
					<tr>
						<th>Item Id</th>
						<th>Item Name</th>
						<th>Action</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${ITEMLIST}" var="item">
						<tr>
							<td>${item.itemId} </td>
							<td>${item.itemName} </td>
							<td> <span class='btn btn-sm btn-warning' onclick="editItem(${item.itemId});">Edit</span> &nbsp;&nbsp; <span class='btn btn-sm btn-danger' onclick='return deleteItem(${item.itemId});'>Delete</span></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>

	<script>

	//delete item
	function deleteItem(deleteId){
		var result = confirm("Want to delete?");
		if (result) {
			$.get("http://localhost:8080/items/delete/"+deleteId, function(response){
				if(response == 'success'){
				alert(deleteId+" deleted ");
				location.replace("http://localhost:8080/admin/items");
					}else{
				alert(deleteId+" is present in Other tables so can not deleted");
						}
			});
		}
		
		}

	//edit item
	function editItem(editId){
			$('#myModal').modal('show');
			$(".modal-title").text("Update Role");
		$.get("http://localhost:8080/items/edit/"+editId, function(response){
			$("#itemId").val(response.itemId);
			$("#itemName").val(response.itemName);
		}, "json");
		}
		
	
		$(document).ready(function() {
			//save and update
			$("#saveBtn").on("click", function(){
				$.post("http://localhost:8080/items1",{itemId : $("#itemId").val(), itemName : $("#itemName").val() }, function(response){
					if(response == 'warning'){
						$("input[name='itemName']").focus();
						alert($("#itemName").val()+" already taken");
						}else if(response == 'lengthError'){
							$("input[name='itemName']").focus();
							alert($("#itemName").val()+" greater then 2 charactors");
						}else if(response == 'failed'){
						alert("Error");
							}else{
								$('#myModal').modal('hide');
								setTimeout(myGreeting, 5000)
								alert($("#itemName").val()+" Item Saved");
								location.replace("http://localhost:8080/admin/items");
								}
					}, "text")
				})	

			//open modal
			$("#openModalBtn").click(function() {
				setTimeout(function() {
					$(".modal-title").text("Add Role");
					$("input[name='itemId']").focus().val(null);
					$("input[name='itemName']").focus().val(null);
				}, 300);

				$("#myModal").modal({
					backdrop : "static",
				});
			});

			$("#example").DataTable({
				responsive : true,
			});	

		});
	</script>
	
</body>
</html>