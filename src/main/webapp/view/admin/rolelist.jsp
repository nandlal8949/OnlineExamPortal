<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Role Manage</title>
</head>
<body>
	<%@ include file="../navbar.jsp"%>
<body>
	<div class="container">
		<h2 class="text-center text-primary my-2">Role Manage</h2>

		<c:if test="${SAVEROLE ne null}">
			<script>
				Swal.fire({
					icon : 'success',
					title : 'Successful',
					text : '${SAVEROLE} Role Saved',
					timer : 2000
				})
			</script>
		</c:if>
		<c:if test="${UPDATEROLE ne null}">
			<script>
				Swal.fire({
					icon : 'success',
					title : 'Successful',
					text : '${UPDATEROLE} Role Update',
					timer : 2000
				})
			</script>
		</c:if>

		<a class="btn btn-sm btn-primary active"
			href="http://localhost:8080/admin/rolelist" style="width: 150px">
			List </a> <a class="btn btn-sm btn-primary"
			href="http://localhost:8080/admin/roleform" style="width: 150px">Add
			Role +</a>
		<h4 class="text-center bg-secondary text-light p-2 my-2">Role
			List</h4>
		<table id="example" class="table table-sm table-bordered" style="width: 100%">
			<thead>
				<tr>
					<th>Role Id</th>
					<th>Role Name</th>
					<th>Action</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${ROLES}" var="role" varStatus="index">
					<tr>
						<td>${role.roleId}</td>
						<td>${role.roleName}</td>
						<td><a class="btn btn-sm btn-primary"
							href="http://localhost:8080/admin/roleform/update/${role.roleId}">Edit</a>
							&nbsp;&nbsp; <a
							onClick="confirmDelete(${role.roleId})"
							href="#"
							class="btn btn-sm btn-danger">Delete</a></td>
					</tr>
				</c:forEach>
			</tbody>

		</table>
	</div>
	<script>
	function confirmDelete(roleId) {
	    Swal.fire({
	    	title: 'Are you sure?',
			  text: "You won't be able to revert this!",
			  icon: 'warning',
			  showCancelButton: true,
			  confirmButtonColor: '#3085d6',
			  cancelButtonColor: '#d33',
			  confirmButtonText: 'Yes, delete it!'
	    }).then((result) => {
	          if (result.isConfirmed) {
	        	  $.get({
	  	            url: "http://localhost:8080/admin/role/delete/"+roleId,
	  	            success: function (data, status) {
	  	            	Swal.fire("Done!", "It was succesfully deleted!", "success");	  	            	
	  	            },
	  	            error: function (xhr, ajaxOptions, thrownError) {
	  	            	Swal.fire("Error deleting!", "Please try again", "error");
	  	            }
	  	        }).then((result)=>{
	        		  window.location.href = "http://localhost:8080/admin/rolelist";
		          });
	            }else if(result.isDismissed){
	            	Swal.fire('Cancelled', 'Role is not Deleted', 'error');
		            }
	          });
	}

	

	
		$(document).ready(function() {
			var table = $('#example').DataTable({
				lengthChange : false,
				/* buttons: [ 'copy', 'csv', 'excel', 'pdf', 'print' ] */
				buttons : [ {
					extend : 'copy',
					exportOptions : {
						columns : [ 0, 1 ]
					}
				}, {
					extend : 'csv',
					exportOptions : {
						columns : [ 0, 1 ]
					}
				}, {
					extend : 'excel',
					exportOptions : {
						columns : [ 0, 1 ]
					}
				}, {
					extend : 'pdf',
					exportOptions : {
						columns : [ 0, 1 ]
					}
				}, {
					extend : 'print',
					exportOptions : {
						columns : [ 0, 1 ]
					}
				},

				]
			});

			table.buttons().container().insertBefore('#example_filter');
		});
	</script>
</body>
</html>