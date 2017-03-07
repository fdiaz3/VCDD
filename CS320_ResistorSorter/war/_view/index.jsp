<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<html>
	<head>
		<title>Index</title>
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
		This is the index view jsp
		
		<c:if test="${! empty errorMessage}">
			<div class="error">${errorMessage}</div>
		</c:if>
		
		
		<form action="${pageContext.servletContext.contextPath}/index" method="post">
		
			<input type="Submit" name="add" value="Add Numbers!">
			<input type="Submit" name="multiply" value="Multiply Numbers!">
			<input type="Submit" name="guess" value="Guessing Game!">
			
		</form>
		
		
		
		
		
		
		
	</body>
</html>
