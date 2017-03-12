<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<html>
	<head>
		<title>Take Resistor</title>
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
		
		<form action="${pageContext.servletContext.contextPath}/InitializeInventory" method="post">
		
	
		<c:forEach items="${inventory.racks}" var="item">
		
		${item} <br />
 		 
		</c:forEach>
		
			
			<table>
			
			
			
				<tr>
					
					<td class="label">Bin Capacity:</td>
					<td><input type="text" name="binCapacity" size="12" value="${inventory.binCapacity}" /></td>
					
					<td class="label">Tolerance:</td>
					<td class="label">Power:</td>
					
					
				</tr>
				<tr>
					
					
					<td class="label">User Remove Limit:</td>
					<td><input type="text" name="userRemoveLimit" size="12" value="${inventory.userRemoveLimit}" /></td>
					
				</tr>
				
				<tr>
					<td>
						<input type="Submit" name="initializeInventory" value="Initialize Inventory!">
					</td>
				</tr>
				
				
				<c:if test="${! ((inventory.binCapacity == null) && (inventory.userRemoveLimit == null))}">
				
					
					

					
					
					<tr>
					<td class="label">Initialize Rack:</td>
					<td><input type="text" name="tolerance" size="12"/></td>
					<td><input type="text" name="power" size="12"/></td>
					
		
		
					</tr>
					
					
					<td>
					<input type="Submit" name="initializeRack" value="Initialize Rack!">
					</td>

					
					
				</c:if>
				
			</table>
			
		</form>
	</body>
</html>