<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<!-- DISCLAIMER 
All work seen in here has been copied, but modified from the Library example -->

<html>
	<head>
		<title>Profile</title>
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
		
		<nav class="navbar navbar-inverse">
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
	            <li class="active"><a href="/resistorSorter/Profile">Your Profile</a></li>
	        </ul>
	    </div>
	</nav>
	
	  <div class="jumbotron">
	    <h1>Welcome, ${username}</h1> 
	    <p>From here, you can view your activity</p> 
	  </div>
			<div>
				<table class="col-md-6">
					
					<tr>
						<th>Transaction Time</th> <th>Type</th> <th>Quantity</th> <th>Resistance</th> 
					</tr>
						
					<c:forEach items="${inventories}" var="item" varStatus="status">
 						<tr> 
 							<td>${item.inventory_id}</td>
 						 	<td>${item.binCapacity}</td>
 						 	<td>${item.userRemoveLimit}</td>
 						 	<td><button type="submit" name="deleteInventory" value="${item.inventory_id}">Delete</button></td> 	
 						</tr>
					</c:forEach>

				</table>
			</div>
		
		<form action="${pageContext.servletContext.contextPath}/Profile" method="get">
			<div>
				<table class="col-md-6">
					<tr>
						<td><input type="Submit" name="logout" value="Logout"></td>
					</tr>
				</table>
			</div>
		</form>
		</div>
		</div>
	</body>
</html>