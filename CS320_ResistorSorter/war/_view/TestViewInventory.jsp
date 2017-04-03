<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>


<html>
	<head>
		<title>View Inventory</title>
		<style type="text/css">
		.error {
			color: red;
		}
		
		td.label {
			text-align: right;
		}
		</style>
		<meta http-equiv="refresh" content="1">
		
	</head>

	<body>
		<c:if test="${! empty errorMessage}">
			<div class="error">${errorMessage}</div>
		</c:if>
		<p>
			<a href="Inventories" class="btn">Inventories</a>
			<a href="Racks" class="btn">Racks</a>
			<a href="Bins" class="btn">Bins</a>
			<a href="Resistor" class="btn">Resistor</a>
			<a href="TestViewInventory" class="btn">TestViewInventory</a>
		</p>
		<form action="${pageContext.servletContext.contextPath}/TestViewInventory" method="post">
		
		
		
		<div style="float:left; border:solid">
				<table>
					<tr>
						<td class="label">Inventory ID: </td> <td class="label">BinCapacity: </td> 	<td class="label">RemoveLimit: </td>
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
		
		<div style="float:left; border:solid">
				<table>
					<tr>
						<td class="label">Rack ID: </td> <td class="label">Inventory ID: </td> <td class="label">Tolerance: </td> 	<td class="label">Power: </td>
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
		
		<div style="float:left; border:solid">
				<table>
					<tr>
						<td class="label">Bin ID: </td> <td class="label">Rack ID: </td> <td class="label">Resistance: </td> 	<td class="label">Count: </td>
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
		
	</body>
	
</html>