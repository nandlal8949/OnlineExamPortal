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

		 <form action="/admin/subitems" method="post" class="row justify-content-center" >
						<input type="hidden" name="subItemId" value="${SUBITEM.subItemId}" id="itemId">
				<div class="col-md-2">
					<div class="form-group">
						<label for="itemName" class="mb-1">Category:</label>
						<select id="itemName" name="items" class="form-control">
							<c:forEach items="${ITEMS}" var="item">
								<c:if test="${item.itemId eq SUBITEM.items.itemId }">
									<option value="${item.itemId }" selected> ${item.itemName } </option>
								</c:if>
								<c:if test="${item.itemId ne SUBITEM.items.itemId }">
									<option value="${item.itemId }"> ${item.itemName } </option>
								</c:if>
							</c:forEach>
						</select>
					</div>
				</div>
				<div class="col-md-4">	
					<div class="form-group">
						<label for="subItemName" class="mb-1">Add SubItemName:</label>
						<input type="text" name="subItemName" value="${SUBITEM.subItemName}" id="subItemName" class="form-control" autocomplete="off">
					</div>
					
				</div>
				<div class="col-md-4 mt-4">
					<input type="submit" class="btn btn-success" value="${SUBMITBUTTON}">
				</div>
		</form>
		
		<h2 class="text-center mb-1">ITEMS LIST</h2>
	<table class="table table-hover table-bordered table-striped m-auto table-sm" id="myTable">
		<thead class="text-center text-white bg-secondary">
				<tr>
					<th>SubItem Id</th>
					<th>SubItem Name </th>
					<th>Main Item </th>
					<th>Action</th>
				</tr>
			</thead>

			<tbody>
				<c:forEach items="${SUBITEMLIST}" var="subitem" varStatus="index">
					<tr>
						<td>${subitem.subItemId}</td>
						<td>${subitem.subItemName}</td>
						<td>${subitem.items.itemName}</td>
						<td><a href="http://localhost:8080/admin/subitems/update/${subitem.subItemId}" class="btn btn-sm btn-primary">Edit</a> &nbsp;&nbsp; <a href="http://localhost:8080/admin/subitems/delete/${subitem.subItemId}" class="btn btn-sm btn-danger">Delete</a> </td>
					</tr>
				</c:forEach>
			</tbody>

		</table>

	</div>

</body>

</body>
</html>