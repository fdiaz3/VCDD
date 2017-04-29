<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>


<html>
	<head>
	
		<title>View Inventory</title>
		
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
		<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
		<link href= "_view/css/styles.css" rel= "stylesheet" type= "text/css">
  		
  		<meta http-equiv="content-type" content="text/html; charset=UTF-8">
  		<meta name="robots" content="noindex, nofollow">
  		<meta name="googlebot" content="noindex, nofollow">

		<script type="text/javascript" src="//code.jquery.com/jquery-1.9.1.js"></script>
		<link rel="stylesheet" type="text/css" href="/css/result-light.css">
  

  

  <style type="text/css">
tr.header
{
    cursor:pointer;
}

tr.header1
{
	cursor:pointer;
}
tr.header2
{
	cursor:pointer;
}
  </style> 
    




<script type='text/javascript'>//<![CDATA[
$(function(){
$('.header').click(function(){


$(this).nextUntil('tr.header').each(function(){
	
	if($(this).css('display') != 'none'){
		$(this).slideUp(150);
	}
	else if($(this).hasClass('header1')){
		$('#innerHead').slideDown(150);
		$(this).slideDown(150);
		
	}
	
});



});

});//]]> 

</script>
<script type='text/javascript'>//<![CDATA[
$(function(){
$('.header1').click(function(){

$(this).nextUntil('tr.header1').slideToggle(150);

});
});//]]> 

</script>


	</head>

	<body>
	<div class="container">
			<div class="row">
		<c:if test="${! empty errorMessage}">
			<div class="error">${errorMessage}</div>
		</c:if>
		<div class="row">
		
	<form action="${pageContext.servletContext.contextPath}/Profile" method="post">
		<script src="_view/javaScript/navbar.js"></script>
		
		<div> <input type="Submit" name="resetInventory" value="Reset Inventory"></div>
		
		<div>
				<table class="col-md-12">
					<tr class= "header">
						<th> Inventory ID </th> <th>BinCapacity </th> 	<th>RemoveLimit </th>
					</tr>
					<c:forEach items="${inventories}" var="item" varStatus="status">
 						<tr class= "header"> 
 							<td>${item.inventory_id}</td>
 						 	<td>${item.binCapacity}</td>
 						 	<td>${item.userRemoveLimit}</td>
 						</tr>
 						<tr id= "innerHead">
							<th></th><th>Rack ID</th> <th>Tolerance</th> <th>Power</th>
 						</tr>
 						<c:forEach items="${racks}" var="item1" varStatus="status1">
 							<tr class= "header1">
 								
 								<c:if test="${item.inventory_id == item1.inventory_id}">
 								<th></th>
 									<td>${item1.rack_id}</td>
 						 			<td>${item1.tolerance}</td>
 						 			<td>${item1.wattage}</td>
 						 			<tr>
 						 			<th></th>
 								<th></th>
 								<th>Resistance</th> 	<th>Count</th>
 								</tr>
 						 			<c:forEach items="${bins}" var="item2" varStatus="status2">
 							<tr>
 								
 								<c:if test="${item2.rack_id == item1.rack_id}">
 								<th></th>
 								<th></th>
 								
 									<td>${item2.resistance}</td>
 						 			<td>${item2.count}</td>
 								</c:if>
 								
 							</tr>
 						</c:forEach>
 								</c:if>
 								
 							</tr>
<%--  							<c:forEach items="${bins}" var="item2" varStatus="status2"> --%>
<!--  							<tr class = "header2"> -->
 								
<%--  								<c:if test="${item2.rack_id == item1.rack_id}"> --%>
<!--  								<td></td> -->
<!--  								<td></td> -->
<%--  									<td>${item2.resistance}</td> --%>
<%--  						 			<td>${item2.count}</td> --%>
<%--  								</c:if> --%>
 								
<!--  							</tr> -->
<%--  						</c:forEach> --%>
 						</c:forEach>
 						
					</c:forEach>
				</table>
		</div>
		
<!-- 		<div> -->
<!-- 				<table class="col-md-4"> -->
<!-- 					<tr class= "header"> -->
<!-- 						<th>Rack ID</th> <th>Inventory ID</th> <th>Tolerance</th> 	<th>Power</th> -->
<!-- 					</tr> -->
<%-- 					<c:forEach items="${racks}" var="item" varStatus="status"> --%>
<!--  						<tr>  -->
<%--  							<td>${item.rack_id}</td> --%>
<%--  							<td>${item.inventory_id}</td> --%>
<%--  						 	<td>${item.tolerance}</td> --%>
<%--  						 	<td>${item.wattage}</td> --%>
<!--  						</tr> -->
<%-- 					</c:forEach> --%>
<!-- 				</table> -->
<!-- 			</div> -->
		
<!-- 		<div> -->
<!-- 				<table class="col-md-4"> -->
<!-- 					<tr> -->
<!-- 						<th>Bin ID</th> <th>Rack ID</th> <th>Resistance</th> 	<th>Count</th> -->
<!-- 					</tr> -->
<%-- 					<c:forEach items="${bins}" var="item" varStatus="status"> --%>
<!--  						<tr>  -->
<%--  							<td>${item.bin_id}</td> --%>
<%--  							<td>${item.rack_id}</td> --%>
<%--  						 	<td>${item.resistance}</td> --%>
<%--  						 	<td>${item.count}</td> --%>
<!--  						</tr> -->
<%-- 					</c:forEach> --%>
<!-- 				</table> -->
<!-- 			</div> -->

		</form>	
		
		</div>
		</div>
		</div>

	</body>
	
</html>

