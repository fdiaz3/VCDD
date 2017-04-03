<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<html>
	<head>
		<title>Resistor</title>
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
		<form action="${pageContext.servletContext.contextPath}/Resistor" method="post">
		
			<div style="float:left;">
				<table>
			
					<tr>
						<td class="label">Bin ID: </td>
						<td><input type="text" name="bin_id" size="12" value="${bin_id}" /></td>
					</tr>
					
					<tr>
						<td><input type="Submit" name="displayBin" value="Display Bin!"></td>
						<td class="label">Count: ${count}</td>
					</tr>
					
					<tr>
						<td class="label">Count: </td>
						<td><input type="text" name="count" size="12" value="0" /></td>
					</tr>
					
					<tr>
						<td><input type="Submit" name="addResistors" value="Add"></td>
						<td><input type="Submit" name="removeResistors" value="Remove"></td>
					</tr>
					
					
				</table>
			</div>

		</form>
		
	</body>
	
</html>