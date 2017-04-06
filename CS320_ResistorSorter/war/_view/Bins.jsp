<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<html>
	<head>
		<title>Bins</title>
		<link href="https://necolas.github.io/normalize.css/5.0.0/normalize.css" rel="stylesheet" type="text/css">
		<link href= "_view/css/bootstrap-theme.min.css" rel= "stylesheet" type= "text/css">
		<link href= "_view/css/bootstrap.min.css" rel= "stylesheet" type= "text/css">
		<link href= "_view/css/styles.css" rel= "stylesheet" type= "text/css">
	</head>

	<body>
		<div class="container">
			<div class="row">
					<c:if test="${! empty errorMessage}">
						<div class="error">${errorMessage}</div>
					</c:if>
					<nav role="navigation" class="navbar navbar-default">
    <!-- Brand and toggle get grouped for better mobile display -->
    <div class="navbar-header">
        <button type="button" data-target="#navbarCollapse" data-toggle="collapse" class="navbar-toggle">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
        </button>
        <a href="#" class="navbar-brand">VCDD</a>
    </div>
    <!-- Collection of nav links and other content for toggling -->
    <div id="navbarCollapse" class="collapse navbar-collapse">
        <ul class="nav navbar-nav">
            <li class="active"><a href="/resistorSorter/Inventories">Home</a></li>
            <li><a href="/resistorSorter/Racks">Racks</a></li>
            <li><a href="/resistorSorter/Bins">Bins</a></li>
            <li><a href="/resistorSorter/Resistor">Resistor</a></li>
            <li><a href="/resistorSorter/TestViewInventory">Full Inventory</a></li>
        </ul>
        <ul class="nav navbar-nav navbar-right">
            <li><a href="#">Login</a></li>
        </ul>
    </div>
</nav>
					<form action="${pageContext.servletContext.contextPath}/Bins" method="post">
					
						<div>
							<table class="col-md-6">
								
								<tr>
									<th class="label"> Rack ID: </th>
									<td><input type="text" name="rack_id" size="12" value="${rack_id}" /></td>
								</tr>
							
								<tr>
									<th><th><input type="Submit" name="displayBins" value="Display Bins!"></th>
								</tr>
								
								<tr>
									<th class="label">Resistance: </th>
									<td><input type="text" name="resistance" size="12"/></td>
								</tr>
							
								<tr>
									<th class="label">Count: </th>
									<td><input type="text" name="count" size="12"/></td>
								</tr>
							
								<tr>
									<th><th><input type="Submit" name="addBin" value="Add Bin!"></th>
								</tr>
							</table>
						</div>
						
						<div>
							<table class="col-md-6">
								
								<tr>
									<th>Bin # </th> <th>Resistance </th> 	<th>Count </th><th>
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
				</div>
			</div>
		
	</body>
	
</html>