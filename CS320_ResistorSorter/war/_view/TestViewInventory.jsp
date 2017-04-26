<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>


<html>
	<head>
	
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
		<script src="_view/javaScript/navbar.js"></script>
		
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