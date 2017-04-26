<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<!-- DISCLAIMER 
All work seen in here has been copied, but modified from the Library example -->

<html>
	<head>
		<title>Login</title>
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
	
	  <div class="jumbotron">
	    <h1>Login</h1> 
	    <p>Welcome to The Voltage Current Divider Divider</p> 
	  </div>


		<form action="${pageContext.servletContext.contextPath}/Login" method="post">
			<div>
				<table class="col-md-6">
					<tr>
						<th>User Name:</th>
						<td><input type="text" name="username" size="12" value="${username}" /></td>
					</tr>
					<tr>
						<th>Password:</th>
						<td><input type="password" name="password" size="12" value="${password}" /></td>
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