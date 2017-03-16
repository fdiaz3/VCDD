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
		
		<form action="${pageContext.servletContext.contextPath}/Rack" method="post">
		
		<input name="rackInfo" type="hidden" value="${rackInfo}" />
		<input name="inventoryId" type="hidden" value="${myObjectId}" />
		
		
			<table>
			

				<tr>
					<td class="label">Tolerance, Power: </td>
					<td class="label">${rackInfo}</td>
					
					
				</tr>
				
				<tr>
					<td class="label">Resistance: </td>
					<td><input type="text" name="resistance" size="12" /></td>
				</tr>
				
				<tr>
					<td class="label">Count: </td>
					<td><input type="text" name="count" size="12"/></td>
				</tr>
				
				<tr>
					<td><input type="Submit" name="addBin" value="add Bin!"></td>
				</tr>
				
				
				<tr>
					<td class="label">Bin Resistance:
						<br/>
							
						<c:forEach var = "i" begin = "1" end="${rack.binLength}">
							${i}<br/>
						</c:forEach>
						
					</td>
					
					<td class="label">Bin Count:
						<br/>
						<c:forEach items="${rack.bins}" var="item">
							${item} <br />
						</c:forEach>
							
					</td>
				</tr>
					
				<tr>
					<td class="label">Enter Bin #:</td>
					<td><input type="text" name="binNum" size="12"/></td>
				</tr>
					
				<tr>
					<td><input type="Submit" name="editBin" value="Edit Bin!"></td>
					<td><input type="Submit" name="deleteBin" value="Delete Bin!"></td>
				</tr>
				
				<tr>
					<td><input type="Submit" name="return" value="Return!"></td>
				</tr>
			
				
			</table>
			
		</form>
		
	</body>
	
</html>