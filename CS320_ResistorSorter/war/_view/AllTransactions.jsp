<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<!-- DISCLAIMER 
All work seen in here has been copied, but modified from the Library example -->

<html>
	<head>
		<title>Transactions</title>
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
			
			
	<form action="${pageContext.servletContext.contextPath}/AllTransactions" method="post">
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
	
	  <div class="jumbotron">
	    <h1>All Transactions </h1> 
	  </div>
			<div>
				<table class="col-md-11">
					
					<tr>
						<th>Transaction Time (yyyy-mm-dd hh:mm:ss)</th> <th>User Name:</th> <th>Inventory ID</th> <th>Rack ID</th> <th>Bin ID</th> <th>Type</th> <th>Quantity</th> <th> </th>
					</tr>
						
					<c:forEach items="${transactions}" var="item" varStatus="status">
 						<tr> 
 							<td>${item.transactionTime}</td>
 							<td>${item.username}</td>
 						 	<td>${item.inventory_id}</td>
 						 	<td>${item.rack_id}</td>
 						 	<td>${item.bin_id}</td>
 						 	<td>${item.transactionType}</td>
 						 	<td>${item.quantity}</td>
 						</tr>
					</c:forEach>

				</table>
			</div>
		</form>
		</div>
		</div>
	</body>
</html>