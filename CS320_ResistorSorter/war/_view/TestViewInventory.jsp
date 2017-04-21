<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>


<html>
	<head>
	
	<!-- UNUSED
		<link href= "_view/css/bootstrap-theme.min.css" rel= "stylesheet" type= "text/css">
		<link href= "_view/css/bootstrap.min.css" rel= "stylesheet" type= "text/css">
		<link href= "_view/css/styles.css" rel= "stylesheet" type= "text/css">
	
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
  		<script src="_view/bootstrap.min.js"></script>
	 -->
	
		<title>View Inventory</title>
		
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
		<div class="row">
		
	<form action="${pageContext.servletContext.contextPath}/Profile" method="post">
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
	        	<li><a href="/resistorSorter/Profile"><span class="glyphicon glyphicon-user"></span>Your Profile</a></li>
	        	<li><button class="btn btn-danger navbar-btn" type="submit" name="logout">Logout</button></li>
	        </ul>
	       		
	    </div>
	</nav>
		
		<div> <input type="Submit" name="resetInventory" value="Reset Inventory"></div>
		
		<div>
				<table class="col-md-4">
					<tr>
						<th> Inventory ID </th> <th>BinCapacity </th> 	<th>RemoveLimit </th>
					</tr>
					<c:forEach items="${inventories}" var="item" varStatus="status">
 						<tr> 
 							<td>${item.inventory_id}</td>
 						 	<td>${item.binCapacity}</td>
 						 	<td>${item.userRemoveLimit}</td>
 						</tr>
					</c:forEach>
				</table>
		</div>
		
		<div>
				<table class="col-md-4">
					<tr>
						<th>Rack ID</th> <th>Inventory ID</th> <th>Tolerance</th> 	<th>Power</th>
					</tr>
					<c:forEach items="${racks}" var="item" varStatus="status">
 						<tr> 
 							<td>${item.rack_id}</td>
 							<td>${item.inventory_id}</td>
 						 	<td>${item.tolerance}</td>
 						 	<td>${item.wattage}</td>
 						</tr>
					</c:forEach>
				</table>
			</div>
		
		<div>
				<table class="col-md-4">
					<tr>
						<th>Bin ID</th> <th>Rack ID</th> <th>Resistance</th> 	<th>Count</th>
					</tr>
					<c:forEach items="${bins}" var="item" varStatus="status">
 						<tr> 
 							<td>${item.bin_id}</td>
 							<td>${item.rack_id}</td>
 						 	<td>${item.resistance}</td>
 						 	<td>${item.count}</td>
 						</tr>
					</c:forEach>
				</table>
			</div>

		</form>	
		
		</div>
		</div>


	</body>
	
</html>