<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link href="http://localhost:8080/bootstrap.min.css" rel="stylesheet">
<script src="http://localhost:8080/jquery-3.6.1.min.js"></script>
<script src="http://localhost:8080/bootstrap.bundle.min.js"></script>




    <link rel="stylesheet" href="https://code.jquery.com/ui/1.13.2/themes/base/jquery-ui.css">
    <link rel="stylesheet" href="https://cdn.datatables.net/1.13.4/css/dataTables.jqueryui.min.css">
    <link rel="stylesheet" href="https://cdn.datatables.net/buttons/2.3.6/css/buttons.jqueryui.min.css">

    <script src="https://cdn.datatables.net/1.13.4/js/jquery.dataTables.min.js"></script>
    <script src="https://cdn.datatables.net/1.13.4/js/dataTables.jqueryui.min.js"></script>
    <script src="https://cdn.datatables.net/buttons/2.3.6/js/dataTables.buttons.min.js"></script>
    <script src="https://cdn.datatables.net/buttons/2.3.6/js/buttons.jqueryui.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jszip/3.1.3/jszip.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/pdfmake/0.1.53/pdfmake.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/pdfmake/0.1.53/vfs_fonts.js"></script>
    <script src="https://cdn.datatables.net/buttons/2.3.6/js/buttons.html5.min.js"></script>
    <script src="https://cdn.datatables.net/buttons/2.3.6/js/buttons.print.min.js"></script>
    <script src="https://cdn.datatables.net/buttons/2.3.6/js/buttons.colVis.min.js"></script>
    
    
<link href="
https://cdn.jsdelivr.net/npm/sweetalert2@11.7.12/dist/sweetalert2.min.css
" rel="stylesheet">
    <script src="
https://cdn.jsdelivr.net/npm/sweetalert2@11.7.12/dist/sweetalert2.all.min.js
"></script>
</head>
<body>

	<nav class="navbar navbar-expand-md navbar-light bg-light p-0 px-2">
		<a href="#" class="navbar-brand"> <img class="mr-2"
			src="http://localhost:8080/images/logocsm.png" alt="image" height="20"
			width="90"> Automation
		</a>
		<button class="navbar-toggler" data-toggle="collapse"
			data-target="#myNavbar">
			<span class="navbar-toggler-icon"></span>
		</button>

		<div class="collapse navbar-collapse" id="myNavbar">
			<ul class="navbar-nav mr-auto">
				<c:forEach items="${MENUS}" var="menus" begin="0">

					<c:if test="${SESSION != null}">
						<li class="nav-item"><a
							href="http://localhost:8080/${menus.iconLink}" class="nav-link">
								${menus.iconText} </a></li>
					</c:if>
					<c:if test="${SESSION1 != null}">
						<li class="nav-item"><a
							href="http://localhost:8080/${menus.iconLink}/${SESSION1.candId}"
							class="nav-link"> ${menus.iconText} </a></li>
					</c:if>
				</c:forEach>

			</ul>
			<div class="form-inline">
				<div class="nav-item dropdown">
					<c:if test="${SESSION != null}">
						<a href="" class="nav-link dropdown-toggle text-secondary mr-2"
							data-toggle="dropdown"> ${SESSION.userName} </a>
						<div class="dropdown-menu">
							<a
								href="http://localhost:8080/userprofile/${SESSION.userId}/${SESSION.role.roleId}"
								class="dropdown-item"> Edit Profile </a> <a
								href="http://localhost:8080/resetpassword/${SESSION.userId}/${SESSION.role.roleId}"
								class="dropdown-item"> Reset Password </a>
						</div>

					</c:if>

					<c:if test="${SESSION1 != null}">
						<a href="" class="nav-link dropdown-toggle text-secondary mr-2"
							data-toggle="dropdown"> ${SESSION1.candFirstname}
							${SESSION1.candLastname} </a>
						<div class="dropdown-menu">
							<a
								href="http://localhost:8080/admin/candidate/update/${SESSION1.candId}"
								class="dropdown-item"> Edit Profile </a> <a
								href="http://localhost:8080/candidate/candresetpassword/${SESSION1.candId}"
								class="dropdown-item"> Reset Password </a>
						</div>
					</c:if>
				</div>
				<a href="http://localhost:8080/logout"
					class="btn btn-sm btn-outline-danger my-2">Logout</a>
			</div>
		</div>


	</nav>
	<script>
		var size = $("#myNavbar > ul > li").length;
		if (size < 1) {
			document.location.href = "/"
		}
	</script>
</body>
</html>