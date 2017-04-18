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
            <li class="active"><a href="/resistorSorter/Racks">Racks</a></li>
            <li><a href="/resistorSorter/Bins">Bins</a></li>
            <li><a href="/resistorSorter/Resistor">Resistor</a></li>
            <li><a href="/resistorSorter/TestViewInventory">Full Inventory</a></li>
        </ul>
        <ul class="nav navbar-nav navbar-right">
            <li><a href="/resistorSorter/Login">Login</a></li>
        </ul>
    </div>
</nav>
		<form action="${pageContext.servletContext.contextPath}/Racks" method="post">
		
			
			<div>
				<table class="col-md-6">
				
			
					<tr>
						<th>Inventory ID: </th>
				
					
					<td>
						<select class="form-control" name="inventory_id">
							<!-- default value after post -->
							<option selected> ${inventory_id} </option>
							<c:forEach items="${inventories}" var="item">
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
						<th>Tolerance: </th>
						<td><input type="text" name="tolerance" size="12"/></td>
					</tr>
				
					<tr>
						<th>Power: </th>
						<td><input type="text" name="power" size="12"/></td>
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
 						 	<td><input type="Submit" name="deleteRack${item.rack_id}" value="Delete"></td>
 						 	
 						</tr>
					</c:forEach>

				</table>
			</div>
			
		</form>
		</div>
		</div>
	</body>
	
</html>