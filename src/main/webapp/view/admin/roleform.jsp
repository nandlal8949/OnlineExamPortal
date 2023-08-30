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
	<h2 class="text-center text-primary my-2">Role Manage </h2>

		<a class="btn btn-primary"
			href="http://localhost:8080/admin/rolelist" style="width: 150px">
			List </a> <a class="btn btn-primary active"
			href="http://localhost:8080/admin/roleform" style="width: 150px">${BUTTONROLE eq 'Add' ? BUTTONROLE: 'Update'} Role </a>

		
		
		<form action="/admin/roleform" method="post" class="row justify-content-center">
		
				<div class="col-md-6 m-3 p-3 bg-light rounded border-primary" style="border:solid">
				<h4 class="text-center p-2 my-2">${BUTTONROLE eq 'Add' ? BUTTONROLE: 'Update'} Role
			Name</h4>
					<div class="form-group">
						<input type="hidden" name="roleId" value="${ROLE.roleId }"
				id="roleId"> <input type="text" name="roleName"
				value="${ROLE.roleName }" id="roleName" class="form-control"
				autocomplete="off">
					</div>
					
					<div class="text-center">
					<input type="submit" onclick="return checkRole();" class="btn btn-success w-25" value="${BUTTONROLE eq 'Add' ? BUTTONROLE: 'Update'} Role">						
					</div>
				</div>
		</form>
		
		
		
		
		

	</div>
	<script>

		function checkRole(){
				var textSize = $("#roleName").val();
				if(textSize.length < 3){
					$("#error").fadeIn(2000).text(textSize+" length is low please add more then 3 chars");
					Swal.fire({
						icon : 'error',
						title : 'Failed',
						text : 'Role Name very Small, Role Name must 3 more charactor',
						timer : 2000
					})
					return false;
					}
				$.post("http://localhost:8080/admin/roleform/checkroleName", {
					roleName : textSize
				}, function(data) {
					if(data == "failed"){
						Swal.fire({
							icon : 'error',
							title : 'Failed',
							text : 'Role Already Exists',
							timer : 2000
						});
						alert(textSize +" is already exists");
						return false;
						}
				});

			}

		
	
		$(document).ready(function() {			
			var table = $('#example').DataTable({
				lengthChange : false,
				buttons : [ 'copy', 'csv', 'excel', 'pdf', 'print' ]
			});

			table.buttons().container().insertBefore('#example_filter');
		});
	</script>
</body>
</html>