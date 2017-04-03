<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>


<html>
	<head>
		<title>View Inventory</title>
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
		<div class="row">
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
            <li class="active"><a href="http://localhost:8081/resistorSorter/Inventory">Home</a></li>
            <li><a href="http://localhost:8081/resistorSorter/Bin">Bins</a></li>
            <li><a href="http://localhost:8081/resistorSorter/Rack">Racks</a></li>
            <li><a href="http://localhost:8081/resistorSorter/TestViewInventory">Full Inventory</a></li>
        </ul>
        <ul class="nav navbar-nav navbar-right">
            <li><a href="#">Login</a></li>
        </ul>
    </div>
</nav>
		</div>
		
		<form action="${pageContext.servletContext.contextPath}/TestViewInventory" method="post">
		
		
		
		<div>
				<table class="col-md-4">
					<tr>
						<th class="label">Inventory ID </th> <th class="label">BinCapacity </th> 	<th class="label">RemoveLimit </th>
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
						<th class="label">Rack ID</th> <th class="label">Inventory ID</th> <th class="label">Tolerance</th> 	<th class="label">Power</th>
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
						<th class="label">Bin ID</th> <th class="label">Rack ID</th> <th class="label">Resistance</th> 	<th class="label">Count</th>
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