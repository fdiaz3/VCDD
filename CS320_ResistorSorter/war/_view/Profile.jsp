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
		<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
		<link href= "_view/css/styles.css" rel= "stylesheet" type= "text/css">
		
		<script type="text/javascript" src="_view/javaScript/jquery-latest.js"></script> 
 		<script type="text/javascript" src="_view/javaScript/jquery.tablesorter.min.js"></script>
  		<link rel="stylesheet" href="_view/css/tableThemes/blue/style.css" type="text/css" media="print, projection, screen" />
		
		
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
	    <p><button type="submit" class="btn btn-primary active" name="requestAdmin">Click to request admin</button></p>
	   	<c:if test="${viewAll}">
				<p>View all user activity: <button type="submit" class="btn btn-primary active" name="viewTransactions">All Transactions</button></p>
		</c:if>
	  </div>
			<div>
				<table class="tablesorter" id="transactionTable">
				
					<thead>
						<tr>
							<th>Transaction Time (mm/dd/yyyy hh:mm)</th>
							<th>Resistance</th>
							<th>Wattage</th>
							<th>Tolerance</th>
							<th>Quantity</th>
							<th>transactionType</th>
							<th>remaining</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${transactions}" var="item" varStatus="status">
	 						<tr> 
	 							<td>${item.transactionTime}</td>
	 						 	<td>${item.resistance}</td>
	 						 	<td>${item.wattage}</td>
	 						 	<td>${item.tolerance}</td>
	 						 	<td>${item.quantity}</td>
	 						 	<td>${item.transactionType}</td>
	 						 	<td>${item.remaining}</td>
	 						</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</form>
		</div>
		</div>
		
	<script>
    $(document).ready(function() 
        { 
            $("#transactionTable").tablesorter(); 
        } 
    );
    </script>
		
	</body>
	
	
	




</html>