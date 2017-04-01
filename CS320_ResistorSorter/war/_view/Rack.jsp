<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<html>
	<head>
		<title>Racks</title>
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
		
		<form action="${pageContext.servletContext.contextPath}/Rack" method="post">
		
			
			<div style="float:left;">
				<table>
			
					<tr>
						<td class="label">Inventory ID: </td>
						<td><input type="text" name="inventory_id" size="12" value="${inventory_id}" /></td>
					</tr>
				
					<tr>
						<td><input type="Submit" name="displayRacks" value="Display Racks!"></td>
					</tr>
				
					<tr>
						<td class="label">Tolerance: </td>
						<td><input type="text" name="tolerance" size="12"/></td>
					</tr>
				
					<tr>
						<td class="label">Power: </td>
						<td><input type="text" name="power" size="12"/></td>
					</tr>
				
					<tr>
						<td><input type="Submit" name="addRack" value="Add Rack!"></td>
					</tr>
				</table>
			</div>
			
			<div>
				<table>
					
					<tr>
						<td class="label">Rack #: </td> <td class="label">Tolerance: </td> 	<td class="label">Power: </td>
					</tr>
						
					<c:forEach items="${racks}" var="item" varStatus="status">
 						<tr> 
 							<td>${status.index+1}</td>
 						 	<td>${item.tolerance}</td>
 						 	<td>${item.wattage}</td>
 						 	<td><input type="Submit" name="deleteRack${item.rack_id}" value="Delete"></td>
 						 	
 						</tr>
					</c:forEach>

				</table>
			</div>
			
		</form>
		
	</body>
	
</html>