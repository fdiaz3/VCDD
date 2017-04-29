<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<!-- DISCLAIMER 
All work seen in here has been copied, but modified from the Library example -->

<html>
	<head>
		<title>Create Account</title>
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css">
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
		<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
		<script src="https://code.jquery.com/jquery-3.2.1.min.js" defer></script>
		<link href= "_view/css/styles.css" rel= "stylesheet" type= "text/css">
	</head>

	<body>
		<div class="container">
		<div class="row">
			<c:if test="${! empty errorMessage}">
				<div class="error">${errorMessage}</div>
			</c:if>
	
	<div class="jumbotron">
	    <h1>Create Account</h1> 
	    <div class="alert alert-danger">
  			<strong>Warning:</strong> passwords are not stored securely.
		</div>
	  </div>
	  
	  
		<form action="${pageContext.servletContext.contextPath}/CreateAccount" method="post">
			<div class="form-group">
				<label for="user">User Name:</label>
				<input type="text" class="form-control" name="username" size="12" id="user" value="${username}" style="width: 200px;" />
			</div>
			<div class="form-group">
				<label for="pass">Password:</label>
				<input type="password" class="form-control" name="password" size="12" id="pass" value="${password}" style="width: 200px;" />
			</div>
			<div class="form-group">
				<label for="repass">Re-Enter Password:</label>
				<input type="password" class="form-control" name="passwordCheck" size="12" id="repass" value="${passwordCheck}" style="width: 200px;" />
			</div>		
			<div class="form-group">
				<label for="first">First Name:</label>
				<input type="text" class="form-control" name="firstname" size="12" id="first" value="${firstname}" style="width: 200px;" />
			</div>		
			<div class="form-group">
				<label for="last">Last Name:</label>
				<input type="text" class="form-control" name="lastname" size="12" id="last" value="${lastname}" style="width: 200px;" />
			</div>	
			<div class="form-group" >
				<label>
					<input type="checkbox" name="adminReq" value="True" autocomplete="off" />
					Request for Administrator Permissions?
				</label>
			</div>	
			<div>
				<input class="btn btn-primary" type="Submit" name="createAccount" value="Create Account">
			</div>			
		</form>
		<form action="${pageContext.servletContext.contextPath}/Login" method="get">
			<div class="form-group" align="right">
				<label for="login">Already have an account? Click here to login!</label>
				<div>
					<input class="btn btn-primary" type="Submit" id="login" name="login" value="Login">
				</div>
			</div>
		</form>
		</div>
		</div>
	</body>
</html>