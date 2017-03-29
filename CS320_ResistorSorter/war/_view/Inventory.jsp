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
		
		<form action="${pageContext.servletContext.contextPath}/Inventory" method="post">
		
	
		
		
			<table>
			
			
			
				<tr>
					<td class="label">Bin Capacity:</td>
					<td><input type="text" name="binCapacity" size="12" value="${inventory.binCapacity}" /></td>
				</tr>
				
				<tr>
					
					<td class="label">User Remove Limit: </td>
					<td><input type="text" name="userRemoveLimit" size="12" value="${inventory.userRemoveLimit}" /></td>
					
				<tr>
					<c:if test="${! (inventory == null) }">
						<td class="label">Tolerance:</td> 
						<td><input type="text" name="tolerance" size="12"/></td>
					</c:if>
				</tr>
				
				<tr>
					<c:if test="${! (inventory == null) }">
						<td class="label">Power: </td>
						<td><input type="text" name="power" size="12"/></td>
					</c:if>
				</tr>
				
				<tr>
					<td>
						<input type="Submit" name="initializeInventory" value="Initialize Inventory!">
					</td>
					
					<td>
						<c:if test="${! (inventory== null)}">
							<input type="Submit" name="initializeRack" value="Add Rack!">
						</c:if>
					</td>
				</tr>
				
				<c:if test="${! (inventory== null)}">
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
				</c:if>
				
				
				
				
				
				
			</table>
			
		</form>
		
	</body>
	
</html>