<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<!-- DISCLAIMER 
All work seen in here has been copied, but modified from the Library example -->

<html>
	<head>
		<title>Inventories</title>
		<link href="https://necolas.github.io/normalize.css/5.0.0/normalize.css" rel="stylesheet" type="text/css">
		<link href= "_view/css/bootstrap-theme.min.css" rel= "stylesheet" type= "text/css">
		<link href= "_view/css/bootstrap.min.css" rel= "stylesheet" type= "text/css">
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
            <li><a"href="/resistorSorter/Login">Login</a></li>
        </ul>
    </div>
</nav>
		<form action="${pageContext.servletContext.contextPath}/CreateAccount" method="post">
			<div>
				<table class="col-md-6">
					<tr>
						<th class="label">User Name:</th>
						<td><input type="text" name="username" size="12" value="${username}" /></td>
					</tr>
					<tr>
						<th class="label">Password:</th>
						<td><input type="text" name="password" size="12" value="${password}" /></td>
					</tr>
					<tr>
						<th class="label">Re-Enter Password:</th>
						<td><input type="text" name="passwordCheck" size="12" value="${passwordCheck}" /></td>
					</tr>
					<tr> 
						<th class="label">First Name:</th>
						<td><input type="text" name="firstname" size="12" value="${firstname}" /></td>
					</tr>
					<tr> 
						<th class="label">Last Name:</th>
						<td><input type="text" name="lastname" size="12" value="${lastname}" /></td>
					</tr>
					<tr> 
						<th class="label">Request for Administrator Permissions?</th>
						<td><input type="checkbox" name="adminReq" size="12" value="True" /></td>
					</tr>
					<tr>
						<th><th><input type="Submit" name="createAccount" value="Create Account"></th>
					</tr>
				</table>
			</div>
		</form>
		</div>
		</div>
	</body>
</html>