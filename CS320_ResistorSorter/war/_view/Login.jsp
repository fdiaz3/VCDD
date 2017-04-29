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
			<div class="form-group">
				<label for="user">User Name:</label>
  				<input type="text" class="form-control" name="username" size="12" id="user" value="${username}" style="width: 200px;">
			</div>
			<div class="form-group">	
				<label for="pass">Password:</label>
  				<input type="password" class="form-control" name="password" size="12" id="pass" value="${password}" style="width: 200px;">
  			</div>
			<div>
				<input class="btn btn-primary" type="Submit" name="login" value="Login">
			</div>		
		</form>
		<form action="${pageContext.servletContext.contextPath}/CreateAccount" method="get">
			<div class="form-group" align="right">
				<label for="create">Don't have an account? Click here to get started!</label>
				<div>
					<input class="btn btn-primary" type="Submit" id="create" name="createAccount" value="Create Account">
				</div>
			</div>
		</form>
		</div>
		</div>
	</body>
</html>