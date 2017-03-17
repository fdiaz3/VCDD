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
		
		<form action="${pageContext.servletContext.contextPath}/Bin" method="post">
		
		<input name="binInfo" type="hidden" value="${binInfo}" />
		<input name="inventoryId" type="hidden" value="${myObjectId}" />
		
			<table>
				<tr>
					<td class="label">Resistance, Count: </td>
					<td class="label">${binInfo}</td>
				</tr>
				
				<tr>
					<td class="label">Count: </td>
					<td><input type="text" name="count" size="12" /></td>
				</tr>
				
				<tr>
					<td><input type="Submit" name="addResistors" value="Add Resistors!"></td>
					<td><input type="Submit" name="subResistors" value="Subtract Resistors!"></td>
				</tr>
				
				<tr>
					<td><input type="Submit" name="return" value="Return!"></td>
				</tr>
				
			</table>
			
		</form>
		
	</body>
	
</html>