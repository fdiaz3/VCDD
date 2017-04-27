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
		<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
		
  		
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
		

				<table class="col-md-4">
					<tr>
						<th> Inventory ID </th> <th>BinCapacity </th> 	<th>RemoveLimit </th>
					</tr>
					<c:forEach items="${inventories}" var="item" varStatus="status">
 						<tr class= "header"> 
 							<td>${item.inventory_id}</td>
 						 	<td>${item.binCapacity}</td>
 						 	<td>${item.userRemoveLimit}</td>
 						</tr>
 						<c:forEach items="${racks}" var="item1" varStatus="status">
 						
 							<td>${item1.rack_id}</td>
 						 	<td>${item1.tolerance}</td>
 						 	<td>${item1.wattage}</td>
 						
 						</c:forEach>
					</c:forEach>
				</table>
		</div>
		
		<div>
				<table class="col-md-4">
					<tr>
						<th>Rack ID</th> <th>Inventory ID</th> <th>Tolerance</th> 	<th>Power</th>
					</tr>
					<c:forEach items="${racks}" var="item" varStatus="status">
 						<tr> 
 							<td>${item.rack_id}</td>
 							<td>${item.inventory_id}</td>
 						 	<td>${item.tolerance}</td>
 						 	<td>${item.wattage}</td>
 						</tr>
					</c:forEach>
				</table>
			</div>
		
		<div>
				<table class="col-md-4">
					<tr>
						<th>Bin ID</th> <th>Rack ID</th> <th>Resistance</th> 	<th>Count</th>
					</tr>
					<c:forEach items="${bins}" var="item" varStatus="status">
 						<tr> 
 							<td>${item.bin_id}</td>
 							<td>${item.rack_id}</td>
 						 	<td>${item.resistance}</td>
 						 	<td>${item.count}</td>
 						</tr>
					</c:forEach>
				</table>
			</div>
			<div>
			<table class="tablesorter">
  <thead>
    <tr>
      <th>Company</th>
      <th>Order</th>
      <th>Status</th>
      <th>Priority</th>
      <th>Price</th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <td rowspan="3"><a href="#" class="toggle">Good Toys</a>
      </td>
      <td>USA</td>
      <td>80%</td>
      <td></td>
      <td></td>
    </tr>
    <tr class="tablesorter-childRow">
      <td>1561651</td>
      <td>finish</td>
      <td>0</td>
      <td>$5</td>
    </tr>
    <tr class="tablesorter-childRow">
      <td>558815</td>
      <td>finish</td>
      <td>0</td>
      <td>$55</td>
    </tr>
    <tr>
      <td rowspan="3"><a href="#" class="toggle">Cycle Clearance</a></td>
      <td>FRANCE</td>
      <td>25%</td>
      <td></td>
      <td></td>
    </tr>
    <tr class="tablesorter-childRow">
      <td>21485213</td>
      <td>in progress</td>
      <td>2</td>
      <td>$7</td>
    </tr>
    <tr class="tablesorter-childRow">
      <td>12547854</td>
      <td>finish</td>
      <td>0</td>
      <td>$18</td>
    </tr>
    <tr>
      <td rowspan="5"><a href="#" class="toggle">Cycle Initial Bike Company</a></td>
      <td>USA</td>
      <td>36%</td>
      <td></td>
      <td></td>
    </tr>
    <tr class="tablesorter-childRow">
      <td>12574521</td>
      <td>in progress</td>
      <td>1</td>
      <td>$5</td>
    </tr>
    <tr class="tablesorter-childRow">
      <td>7895452</td>
      <td>in progress</td>
      <td>2</td>
      <td>$78</td>
    </tr>
    <tr class="tablesorter-childRow">
      <td>1542021</td>
      <td>finish</td>
      <td>0</td>
      <td>$28</td>
    </tr>
    <tr class="tablesorter-childRow">
      <td>4489885</td>
      <td>finish</td>
      <td>0</td>
      <td>$18</td>
    </tr>
    <tr>
      <td rowspan="5"><a href="#" class="toggle">Sports Store</a></td>
      <td>USA</td>
      <td>90%</td>
      <td></td>
      <td></td>
    </tr>
    <tr class="tablesorter-childRow">
      <td>18915</td>
      <td>in progress</td>
      <td>2</td>
      <td>$5</td>
    </tr>
    <tr class="tablesorter-childRow">
      <td>5402574</td>
      <td>in progress</td>
      <td>2</td>
      <td>$78</td>
    </tr>
    <tr class="tablesorter-childRow">
      <td>26478</td>
      <td>finish</td>
      <td>0</td>
      <td>$28</td>
    </tr>
    <tr class="tablesorter-childRow">
      <td>25682</td>
      <td>finish</td>
      <td>0</td>
      <td>$18</td>
    </tr>
    <tr>
      <td rowspan="5"><a href="#" class="toggle">Locks Company</a></td>
      <td>UK</td>
      <td>24%</td>
      <td></td>
      <td></td>
    </tr>
    <tr class="tablesorter-childRow">
      <td>18915</td>
      <td>in progress</td>
      <td>2</td>
      <td>$5</td>
    </tr>
    <tr class="tablesorter-childRow">
      <td>5402574</td>
      <td>in progress</td>
      <td>1</td>
      <td>$78</td>
    </tr>
    <tr class="tablesorter-childRow">
      <td>26478</td>
      <td>finish</td>
      <td>0</td>
      <td>$28</td>
    </tr>
    <tr class="tablesorter-childRow">
      <td>25682</td>
      <td>finish</td>
      <td>0</td>
      <td>$18</td>
    </tr>
    <tr>
      <td rowspan="3"><a href="#" class="toggle">Famous Bike Shop</a></td>
      <td>UK</td>
      <td>12%</td>
      <td></td>
      <td></td>
    </tr>
    <tr class="tablesorter-childRow">
      <td>185406</td>
      <td>in progress</td>
      <td>2</td>
      <td>$5</td>
    </tr>
    <tr class="tablesorter-childRow">
      <td>541265</td>
      <td>in progress</td>
      <td>2</td>
      <td>$78</td>
    </tr>
  </tbody>
</table>
			</div>

		</form>	
		
		</div>
		</div>
	</div>

	</body>
	<script>
  		$(function() {

  		  var $table = $('.tablesorter');

  		  $table.tablesorter({
  		    theme: 'blue',
  		    sortList: [[1, 0]],
  		    widgets: ['filter', 'zebra'],
  		    widgetOptions: {
  		      filter_childRows: true,
  		    }
  		  });

  		  // make toggles clickable
  		  $table.on('click', '.toggle', function() {
  		    $(this)
  		      .closest('tr')
  		      .nextUntil('tr:not(.tablesorter-childRow)')
  		      .toggleClass('show');
  		    return false;
  		  });

  		}
  		</script>
</html>