<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>


<html>
	<head>
		<title>Inventories</title>
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
		<form action="${pageContext.servletContext.contextPath}/Inventory" method="post">
		
	
		<div>
				<table class="col-md-6">
					<tr>
						<th class="label">Bin Capacity:</th>
						<td><input type="text" name="binCapacity" size="12" value="${inventory.binCapacity}" /></td>
					</tr>
					
					<tr>
						
						<th class="label">User Remove Limit: </th>
						<td><input type="text" name="userRemoveLimit" size="12" value="${inventory.userRemoveLimit}" /></td>
						
					</tr>
					
					<tr>
						<th><th><input type="Submit" name="initializeInventory" value="Add Inventory!"></th>
					</tr>
					
					
						
					<!--  
					<tr>
							<td class="label">Tolerance:</td> 
							<td><input type="text" name="tolerance" size="12"/></td>
					</tr>
					
					<tr>
							<td class="label">Power: </td>
							<td><input type="text" name="power" size="12"/></td>
					</tr>
					
					<tr>
						<td>
							<input type="Submit" name="initializeInventory" value="Add Inventory!">
						</td>
						
						<td>
								<input type="Submit" name="initializeRack" value="Add Rack!">
						</td>
					</tr>
					
						<tr>
							<td class="label">Rack #:
								<br/>
								
								<c:forEach var = "i" begin = "1" end="${inventory.rackLength}">
									${i}<br/>
								</c:forEach>
							
							</td>
							<td class="label">Tolerance, Power
								<br/>
								<c:forEach items="${inventory.racks}" var="item">
									${item} <br />
								</c:forEach>
								
							</td>
						</tr>
						
						<tr>
							<td class="label">Enter Rack #:</td>
							<td><input type="text" name="rackNum" size="12"/></td>
						</tr>
						
						<tr>
						
							<td><input type="Submit" name="editRack" value="Edit Rack!"></td>
							<td><input type="Submit" name="deleteRack" value="Delete Rack!"></td>
						</tr>
					-->
					
					
				</table>
			</div>
			<div>
				<table class="col-md-6">
					
					<tr>
						<th class="label">Inventory #</th> <th class="label">BinCapacity</th> 	<th class="label">RemoveLimit</th><th>
					</tr>
						
					<c:forEach items="${inventories}" var="item" varStatus="status">
 						<tr> 
 							<td>${item.inventory_id}</td>
 						 	<td>${item.binCapacity}</td>
 						 	<td>${item.userRemoveLimit}</td>
 						 	<td><input type="Submit" name="deleteInventory${item.inventory_id}" value="Delete"></td>
 						 	
 						</tr>
					</c:forEach>

				</table>
			</div>
		</form>
		</div>
		</div>
	</body>
	
</html>