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
					<td class="label">inventory #: 
						<br/>
 						<c:forEach items="${inventories}" var="item" varStatus="status">
 						
   						 	${status.count}<br>
   						 	
						</c:forEach>
						<br/>
					</td>
					 
					<td class="label">BinCapacity: 
						<br/>
						<c:forEach items="${inventories}" var="item" >
   						 	${item.binCapacity}<br>
						</c:forEach>
						<br/>
					</td> 
					
					<td class="label">RemoveLimit: 
						<br/>
						<c:forEach items="${inventories}" var="item" >
   						 	${item.userRemoveLimit}<br>
						</c:forEach>
						<br/>
					</td> 
					
				</tr>
				
				<tr>
				
				</tr>
			
			</table>
			
			<input type="Submit" name="submitting" value="REFRESH THE DAMN PAGE!">
			
			
		</form>
		
	</body>
	
</html>