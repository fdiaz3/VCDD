<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<html>
	<head>
		<title>Bins</title>
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
		
			<div style="float:left;">
				<table>
			
					<tr>
						<td class="label">Rack ID: </td>
						<td><input type="text" name="rack_id" size="12" value="${rack_id}" /></td>
					</tr>
				
					<tr>
						<td><input type="Submit" name="displayBins" value="Display Bins!"></td>
					</tr>
				
					<tr>
						<td class="label">Resistance: </td>
						<td><input type="text" name="resistance" size="12"/></td>
					</tr>
				
					<tr>
						<td class="label">Count: </td>
						<td><input type="text" name="count" size="12"/></td>
					</tr>
				
					<tr>
						<td><input type="Submit" name="addBin" value="Add Bin!"></td>
					</tr>
				</table>
			</div>
			
			<div>
				<table>
					
					<tr>
						<td class="label">Bin #: </td> <td class="label">Resistance: </td> 	<td class="label">Count: </td>
					</tr>
						
					<c:forEach items="${bins}" var="item" varStatus="status">
 						<tr> 
 							<td>${item.bin_id}</td>
 						 	<td>${item.resistance}</td>
 						 	<td>${item.count}</td>
 						 	<td><input type="Submit" name="deleteBin${item.bin_id}" value="Delete"></td>
 						 	
 						</tr>
					</c:forEach>

				</table>
			</div>
			
		</form>
		
	</body>
	
</html>