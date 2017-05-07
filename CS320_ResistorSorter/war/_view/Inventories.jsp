<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>


<html>
	<head>
		<title>Inventories</title>
		<meta charset="utf-8">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
		<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
		<link href= "_view/css/styles.css" rel= "stylesheet" type= "text/css">
  		
	</head>

	<body>
	<div class="container">
			<div class="row">
		
	<form action="${pageContext.servletContext.contextPath}/Inventories" method="post">
	
	<script src="_view/javaScript/navbar.js"></script>
    	<c:if test="${! empty errorMessage}">
			<div class="alert alert-danger alert-dismissable fade in">
				<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
				<strong>Error: </strong>${errorMessage}
			</div>
		</c:if>
			<div>
				<table class="col-md-6">
					<tr>
						<th>InventoryName:</th>
						<td><input type="text" name="inventoryName" size="12"/></td>
					</tr>
					<tr>
						<th>Bin Capacity:</th>
						<td><input type="number" min="1" name="binCapacity" size="12"/></td>
					</tr>
					<tr>
						<th>User Remove Limit: </th>
						<td><input type="number" min="1" name="userRemoveLimit" size="12"/></td>
					</tr>
					<tr>
						<th><th><input type="Submit" name="initializeInventory" value="Add Inventory!"></th>
					</tr>
				</table>
			</div>
			<div>
				<table class="col-md-6">
					
					<tr>
						<th>Inventory #</th> <th>Inventory name</th> <th>BinCapacity</th> 	<th>RemoveLimit</th><th>
					</tr>
						
					<c:forEach items="${inventories}" var="item" varStatus="status">
 						<tr> 
 							<td>${item.inventory_id}</td>
 							<td>${item.inventoryName}</td>
 						 	<td>${item.binCapacity}</td>
 						 	<td>${item.userRemoveLimit}</td>
 						 	<td><button type="submit" name="deleteInventory" value="${item.inventory_id}">Delete</button></td> 	
 						</tr>
					</c:forEach>

				</table>
			</div>
		</form>
		</div>
		</div>
	</body>
	
</html>