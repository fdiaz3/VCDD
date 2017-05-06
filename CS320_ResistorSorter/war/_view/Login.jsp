<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<!-- DISCLAIMER 
All work seen in here has been copied, but modified from the Library example -->

<html>
	<head>
		<title>Login</title>
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<meta name="google-signin-client_id" content="486265042204-irblv200fagfgejidat6bdnqivrvonha.apps.googleusercontent.com">
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
		<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
		<script src="https://apis.google.com/js/platform.js" async defer></script>
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
	    <p>Now introducing... Sign in, with your Google account:</p>    
	    <div class="g-signin2" data-onsuccess="onSignIn"></div>
	    <form id="loginForm" action="${pageContext.servletContext.contextPath}/Login" method="post">
	    <input class="btn btn-primary" type="Submit" name="login" value="Login">
	    <input type="hidden" name="email" id="sendId"/>
	    </form>
	  </div>
	  
	  
	  <!-- 
	  <a href="#" onclick="signOut();">Sign out</a>
	  
	  
		<form id="loginForm" action="${pageContext.servletContext.contextPath}/Login" method="post">
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
			
			<input type="hidden" name="email" id="sendId"/>
				
		</form>
		 -->
		 
		 
		<!--  
		<form action="${pageContext.servletContext.contextPath}/CreateAccount" method="get">
			<div class="form-group" align="right">
				<label for="create">Don't have an account? Click here to get started!</label>
				<div>
					<input class="btn btn-primary" type="Submit" id="create" name="createAccount" value="Create Account">
				</div>
			</div>
		</form>
		-->
		
		</div>
		</div>
		
	</body>

<script type='text/javascript'>
function onSignIn(googleUser) {
  var profile = googleUser.getBasicProfile();
  console.log('ID: ' + profile.getId()); // Do not send to your backend! Use an ID token instead.
  console.log('Name: ' + profile.getName());
  console.log('Image URL: ' + profile.getImageUrl());
  console.log('Email: ' + profile.getEmail()); // This is null if the 'email' scope is not present.
  
  var anchor = document.getElementById("sendId");
  var att = document.createAttribute("value");
  att.value = profile.getEmail();
  anchor.setAttributeNode(att);
  //document.forms["loginForm"].submit();
} 

function signOut() {
    var auth2 = gapi.auth2.getAuthInstance();
    auth2.signOut().then(function () {
      console.log('User signed out.');
    });
  }

</script>
</html>