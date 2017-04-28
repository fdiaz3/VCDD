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
		<link rel='stylesheet' type='text/css' href='_view/css/stylesheet.css'/>
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
			<div align="center">
				<table class="col-md-6">
					<tr>
						<th>Here is a representation of how the resistor should be colored!</th>
					</tr>
				</table>
				<table class="col-md-6">
 					 <tr>
   						<th>Band 1</th>
    					<th>Band 2</th>
    					<th>Band 3</th>
    					<th>Band 4</th>
 					 </tr>
  					 <tr>
   						<td style="background-color:#FF0000;"></td>
   						<td bgcolor="#00FF00"></td>
   						<td bgcolor="#FF0000"></td>
   						<td bgcolor="#00FF00"></td>
 					 </tr>
				</table>
			</div>
		</form>
		</div>
		</div>
	</body>
	<script>// Set up!
	function showR(a){
	var a_canvas = document.getElementById("a");
	var context = a_canvas.getContext("2d");
	var this_js_script = $('script[src*=_view/Resistor.jsp]');

	// Draw the face
	context.fillStyle = "yellow";
	context.beginPath();
	context.arc(95, 85, 40, 0, 2*Math.PI);
	context.closePath();
	context.fill();
	context.lineWidth = 2;
	context.stroke();
	context.fillStyle = "black";

	// Draw the left eye
	context.beginPath();
	context.arc(75, 75, 5, 0, 2*Math.PI);
	context.closePath();
	context.fill();

	// Draw the right eye
	context.beginPath();
	context.arc(114, 75, 5, 0, 2*Math.PI);
	context.closePath();
	context.fill();

	// Draw the mouth
	context.beginPath();
	context.arc(95, 90, 26, Math.PI, 2*Math.PI, true);
	context.closePath();
	context.fill();

	// Write "Hello, World!"
	context.font = "30px Garamond";
	context.fillText(a,15,175);
	alert(a);
	}
	</script>
</html>