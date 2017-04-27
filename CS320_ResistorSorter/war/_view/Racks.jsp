<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<html>
	<head>
		<title>Racks</title>
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
		
		
	<form action="${pageContext.servletContext.contextPath}/Racks" method="post">
		<script src="_view/javaScript/navbar.js"></script>
			<div>
				<table class="col-md-6">
				
			
					<tr>
						<th>Inventory ID: </th>
				
					
					<td>
						<select class="form-control" name="inventory_id">
						
						<c:if test="${empty inventory_id}">
								<option selected disabled> Select an Inventory ID </option>
							</c:if>
						
						
							<c:forEach items="${inventories}" var="item">
							
							<!-- default value after post -->
							<!-- if an inventory_id comes back from servlet, display it as the selected one -->
							<!-- (value =) is what gets passed back to the servlet -->
							<c:if test="${item.inventory_id == inventory_id}">
								<option selected value = "${item.inventory_id}"> ID${item.inventory_id} Capacity=${item.binCapacity} removeLimit=${item.userRemoveLimit} </option>
							</c:if>

							<!-- test used to prevent showing selected value twice -->
							<c:if test="${item.inventory_id != inventory_id}">
								<option value = "${item.inventory_id}">ID${item.inventory_id} Capacity=${item.binCapacity} removeLimit=${item.userRemoveLimit} </option>
							</c:if>

							</c:forEach>
						</select>
					</td>
					
					</tr>
				
					<tr>
						<th><th><input type="Submit" name="displayRacks" value="Display Racks!"></th>
					</tr>
				
					<tr>
					<!-- Maximum tolerance of 25% is acceptable -->
						<th>Tolerance: </th>
						<td><input type="number" min="1" max="25" name="tolerance" size="12"/></td>
					</tr>
				
					<tr>
						<th>Power: </th> 
						<td><input type="number" min="0.05" step="0.01" name="power" size="12"/></td>
					</tr>
				
					<tr>
						<th><th><input type="Submit" name="addRack" value="Add Rack!"></th>
					</tr>
				</table>
			</div>
			
			<div>
				<table class="col-md-6">
					
					<tr>
						<th>Rack # </th> <th>Tolerance </th> 	<th>Power </th> <th>
					</tr>
						
					<c:forEach items="${racks}" var="item" varStatus="status">
 						<tr> 
 							<td>${item.rack_id}</td>
 						 	<td>${item.tolerance}</td>
 						 	<td>${item.wattage}</td>
 						 	<td><button type="submit" name="deleteRack" value="${item.rack_id}">Delete</button></td> 
 						</tr>
					</c:forEach>

				</table>
			</div>
			
		</form>
		</div>
		</div>
	</body>
	
</html>