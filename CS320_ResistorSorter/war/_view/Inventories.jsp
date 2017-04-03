<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>


<html>
	<head>
		<title>Inventories</title>
		<style type="text/css">
		.error {
			color: red;
		}
		
		td.label {
			text-align: right;
		}
		</style>
		
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
		
		<form action="${pageContext.servletContext.contextPath}/Inventories" method="post">
		
	
		<div style="float:left;">
				<table>
					<tr>
						<td class="label">Bin Capacity:</td>
						<td><input type="text" name="binCapacity" size="12" value="${inventory.binCapacity}" /></td>
					</tr>
					
					<tr>
						
						<td class="label">User Remove Limit: </td>
						<td><input type="text" name="userRemoveLimit" size="12" value="${inventory.userRemoveLimit}" /></td>
						
					</tr>
					
					<tr>
						<td><input type="Submit" name="initializeInventory" value="Add Inventory!"></td>
					</tr>
		
				</table>
			</div>
			<div>
				<table>
					
					<tr>
						<td class="label">Inventory #: </td> <td class="label">BinCapacity: </td> 	<td class="label">RemoveLimit: </td>
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
		
	</body>
	
</html>