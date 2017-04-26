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
		<script src="_view/javaScript/tableSort.js"></script>
		<link href= "_view/css/styles.css" rel= "stylesheet" type= "text/css">
	</head>

	<body>
		<div class="container">
		<div class="row">
			<c:if test="${! empty errorMessage}">
				<div class="error">${errorMessage}</div>
			</c:if>
			
			
	<form action="${pageContext.servletContext.contextPath}/Profile" method="post">
		<script src="_view/javaScript/navbar.js"></script>
	
	  <div class="jumbotron">
	    <h1>Welcome, ${username}</h1> 
	    <p>From here, you can view your activity</p> 
	    <p>User status: <span class="label label-info"> ${adminFlag} </span></p>
	   	<c:if test="${viewAll}">
				<p>View all user activity: <button type="submit" class="btn btn-primary active" name="viewTransactions">All Transactions</button></p>
		</c:if>
	  </div>
			<div>
				<table class="col-md-11" id="transactionTable">
					
					<tr>
						<th onclick="sortTable(0, 'transactionTable')">Transaction Time (yyyy-mm-dd hh:mm:ss)</th>
						<th onclick="sortTable(1, 'transactionTable')">Inventory ID</th>
						<th onclick="sortTable(2, 'transactionTable')">Rack ID</th>
						<th onclick="sortTable(3, 'transactionTable')">Bin ID</th>
						<th onclick="sortTable(4, 'transactionTable')">Type</th>
						<th onclick="sortTable(5, 'transactionTable')">Quantity</th>
					</tr>
						
					<c:forEach items="${transactions}" var="item" varStatus="status">
 						<tr> 
 							<td>${item.transactionTime}</td>
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