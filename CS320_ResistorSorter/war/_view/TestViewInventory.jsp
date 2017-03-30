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

			<table>
			
				<tr>
					<td class="label">Inventory #: </td> <td class="label">BinCapacity: </td> <td class="label">RemoveLimit: </td> 
				</tr>
				
				<tr>
					<c:forEach items="${inventory.BinCapacity}" var="item">
						${item} <br />
					</c:forEach>
					
				</tr>
				
						
			
			</table>
			
		</form>
		
	</body>
	
</html>