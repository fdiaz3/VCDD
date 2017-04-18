<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<!-- DISCLAIMER 
All work seen in here has been copied, but modified from the Library example -->

<html>
	<head>
		<title>Resistors</title>
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
		<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
		<link href= "_view/css/styles.css" rel= "stylesheet" type= "text/css">
	</head>

	<body>
		<div class="container">
		<div class="row">
			<c:if test="${! empty errorMessage}">
				<div class="error">${errorMessage}</div>
			</c:if>
	
				<nav role="navigation" class="navbar navbar-default">
    <!-- Brand and toggle get grouped for better mobile display -->
    <div class="navbar-header">
        <button type="button" data-target="#navbarCollapse" data-toggle="collapse" class="navbar-toggle">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
        </button>
        <a href="#" class="navbar-brand">VCDD</a>
    </div>
    <!-- Collection of nav links and other content for toggling -->
    <div id="navbarCollapse" class="collapse navbar-collapse">
        <ul class="nav navbar-nav">
            <li><a href="/resistorSorter/Inventories">Home</a></li>
            <li><a href="/resistorSorter/Racks">Racks</a></li>
            <li><a href="/resistorSorter/Bins">Bins</a></li>
            <li><a href="/resistorSorter/Resistor">Resistor</a></li>
            <li><a href="/resistorSorter/TestViewInventory">Full Inventory</a></li>
        </ul>
        <ul class="nav navbar-nav navbar-right">
            <li class="active"><a href="/resistorSorter/Login">Login</a></li>
        </ul>
    </div>
</nav>
		<form action="${pageContext.servletContext.contextPath}/Login" method="post">
			<div>
				<table class="col-md-6">
					<tr>
						<th>User Name:</th>
						<td><input type="text" name="username" size="12" value="${username}" /></td>
					</tr>
					<tr>
						<th>Password:</th>
						<td><input type="text" name="password" size="12" value="${password}" /></td>
					</tr>
					<tr>
						<th><th><input type="Submit" name="login" value="Login"></th>
					</tr>
				</table>
			</div>
		</form>
		<form action="${pageContext.servletContext.contextPath}/CreateAccount" method="get">
			<div>
				<table class="col-md-6">
					<tr>
						<th>Don't have an account? Click here to get started!</th>
						<td><input type="Submit" name="createAccount" value="Create Account"></td>
					</tr>
				</table>
			</div>
		</form>
		</div>
		</div>
	</body>
</html>