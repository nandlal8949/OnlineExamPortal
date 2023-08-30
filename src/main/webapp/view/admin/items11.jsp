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
		<h1 class="text-center text-danger my-2">Items Manage</h1>

		 <form action="/admin/items" method="post" class="row justify-content-center my-2" >
				<div class="col-md-6">
					
						<input type="hidden" name="itemId" value="${ITEMINFO.itemId}" id="itemId">
					
					<div class="form-group">
						<label for="itemName" class="mb-1">Add ItemName:</label>
						<input type="text" name="itemName" value="${ITEMINFO.itemName}" id="itemName" class="form-control" autocomplete="off">
					</div>
					
					<div class="text-center">
					<input type="submit" class="btn btn-success w-25" value="Submit">						
					</div>
				</div>
		</form>
		
		

		<h2 class="text-center mb-1">ITEMS LIST</h2>
	<table class="table table-hover table-bordered table-striped m-auto table-sm" id="myTable">
		<thead class="text-center text-white bg-secondary">
				<tr>
					<th>Item Id</th>
					<th>Item Name</th>
					<th>Action</th>
				</tr>
			</thead>

			<tbody>
				<c:forEach items="${ITEMSLIST}" var="items" varStatus="index">
					<tr>
						<td>${items.itemId}</td>
						<td>${items.itemName}</td>
						<td><a href="http://localhost:8080/admin/items/update/${items.itemId}" class="btn btn-sm btn-primary">Edit</a> &nbsp;&nbsp; <a href="http://localhost:8080/admin/items/delete/${items.itemId }" class="btn btn-sm btn-danger">Delete</a> </td>
					</tr>
				</c:forEach>
			</tbody>

		</table>

	</div>

</body>

</body>
</html>