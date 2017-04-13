<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<html>
	<head>
		<title>Racks</title>
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
            <li class="active"><a href="http://localhost:8081/resistorSorter/Inventories">Home</a></li>
            <li><a href="http://localhost:8081/resistorSorter/Racks">Racks</a></li>
            <li><a href="http://localhost:8081/resistorSorter/Bins">Bins</a></li>
            <li><a href="http://localhost:8081/resistorSorter/Bins">Resistor</a></li>
            <li><a href="http://localhost:8081/resistorSorter/TestViewInventory">Full Inventory</a></li>
        </ul>
        <ul class="nav navbar-nav navbar-right">
            <li><a href="#">Login</a></li>
        </ul>
    </div>
</nav>
		<form action="${pageContext.servletContext.contextPath}/Racks" method="post">
		
			
			<div>
				<table class="col-md-6">
			
					<tr>
						<th class="label">Inventory ID: </th>
						<td><input type="text" name="inventory_id" size="12" value="${inventory_id}" /></td>
					</tr>
				
					<tr>
						<th><th><input type="Submit" name="displayRacks" value="Display Racks!"></th>
					</tr>
				
					<tr>
						<th class="label">Tolerance: </th>
						<td><input type="text" name="tolerance" size="12"/></td>
					</tr>
				
					<tr>
						<th class="label">Power: </th>
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