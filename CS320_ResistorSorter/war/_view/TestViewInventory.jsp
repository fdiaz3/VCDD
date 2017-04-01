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
		
		<form action="${pageContext.servletContext.contextPath}/TestViewInventory" method="post">
		
		
		
		<div>
				<table>
					
					<tr>
						<td class="label">Inventory #: </td> <td class="label">BinCapacity: </td> 	<td class="label">RemoveLimit: </td>
					</tr>
						
					<c:forEach items="${inventories}" var="item" varStatus="status">
 						<tr> 
 							<td>${status.index+1}</td>
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