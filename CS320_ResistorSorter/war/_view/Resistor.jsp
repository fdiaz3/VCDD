<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<html>
	<head>
		<title>Resistors</title>
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
		<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
		<link href= "_view/css/styles.css" rel= "stylesheet" type= "text/css">
	</head>

	<body>
		<div class="container">
			<div class="row">
		<c:if test="${! empty errorMessage}">
			<div class="error">${errorMessage}</div>
		</c:if>
		
	<form action="${pageContext.servletContext.contextPath}/Resistor" method="post">
		<script src="_view/javaScript/navbar.js"></script>
		
			<div>
				<table class="col-md-6">
				
					<tr>
						<th>Capacity: ${capacity} </th>  <th>User Remove Limit: ${userRemoveLimit}</th>
						
					</tr>
				
					<tr>
						<th>Bin ID: </th>
						<td><input type="number" min="1" max="${max_bin}"name="bin_id" size="12" value="${bin_id}" /></td>
					</tr>
					
					<tr>
						<td><input type="Submit" name="displayBin" value="Display Bin!"></td>
						<td>Count: ${count}</td>
					</tr>
					
					<tr>
						<th>Count: </th>
						<td><input type="number" min="1" max="${max_count}"name="countChange" size="12" value="1" /></td>
					</tr>
					
					<tr>
						<td><input type="Submit" name="addResistors" value="Add"></td>
						<td><input type="Submit" name="removeResistors" value="Remove"></td>
					</tr>
					
					
				</table>
			</div>
			
			<canvas id="aResistor" width="400" height="200">
				This text is displayed if your browser does not support HTML5 Canvas.
			</canvas>
			<script type="text/javascript" src="_view/javaScript/resistor.js"></script>
			<script type="text/javascript">
				RESISTOR.init("${colorBands[0]}", "${colorBands[1]}", "${colorBands[2]}", "${colorBands[3]}");
				RESISTOR.drawResistor();
				
			</script>
		</form>
		</div>
		</div>
	</body>
</html>