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
 		<meta name="viewport" content="width=device-width, initial-scale=1.0">

  

  <style type="text/css">
	* {
	    box-sizing: border-box;
	}
	
	.columns {
		
	    float: left;
	    width: 33.3%;
	    padding: 8px;
	    
	}
	
	.price {
	    list-style-type: none;
	    border: 1px solid #eee;
	    margin: 0;
	    padding: 0;
	    -webkit-transition: 0.3s;
	    transition: 0.3s;
	    
	}
	
	.price:hover {
	    box-shadow: 0 32px 48px 0 rgba(0,0,0,0.7)
	    
	}
	.price:hover  .header{
	    background-color: #01C91B;
	}
	.price .header {
	    background-color: #111;
	    color: white;
	    font-size: 25px;
	}
	
	
	.price li {
	    border-bottom: 1px solid #eee;
	    padding: 20px;
	    text-align: center;
	    
	}
	
	.price .grey {
	    background-color: #eee;
	    font-size: 20px;
	}
	
	.button {
	    background-color: #4CAF50;
	    border: none;
	    color: white;
	    padding: 10px 25px;
	    text-align: center;
	    text-decoration: none;
	    font-size: 18px;
	}
	
	@media only screen and (max-width: 600px) {
	    .columns {
	        width: 100%;
	    }
	}
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
	.priceHead{
		font-size: 20px;
		color: white;
		background-color: #535955;
		margin: 5px; 
	}
	.dropbtn {
    background-color: #535955;
    color: white;
    padding: 8px;
    font-size: 16px;
    border: none;
    cursor: pointer;
    
	}
	
	.dropdown {
	    position: relative;
	    display: inline-block;
	}
	
	.dropdown-content {
	    display: none;
	    position: absolute;
	    background-color: #f9f9f9;
	    min-width: 160px;
	    box-shadow: 0px 8px 16px 0px rgba(0,0,0,0.2);
	    z-index: 1;
	    
	}
	
	.dropdown-content a {
	    color: black;
	    padding: 12px 16px;
	    text-decoration: none;
	    display: block;
	}
	
	.dropdown-content a:hover {background-color: #f1f1f1}
	
	.dropdown:hover .dropdown-content {
	    display: block;
	}
	
	.dropdown:hover .dropbtn {
	    background-color: #3e8e41;
	}
	.rackDesc{
		font-weight: bold;
		font-size: 17px;
	}
	
	.popup {
	color: #111;
	border-bottom: 1px solid #93b7c3;
	width: 100%;
    position: relative;
    display: inline-block;
    cursor: pointer;
    -webkit-user-select: none;
    -moz-user-select: none;
    -ms-user-select: none;
    user-select: none;
}

/* The actual popup */
.popup .popuptext {
    visibility: hidden;
    width: 280px;
    background-color: #555;
    color: #111;
    text-align: center;
    border-radius: 6px;
    padding: 8px 0;
    position: absolute;
    z-index: 1;
    bottom: 125%;
    left: 50%;
    margin-left: -80px;
    
}

/* Popup arrow */
.popup .popuptext::after {
    content: "";
    position: absolute;
    top: 100%;
    left: 50%;
    margin-left: -5px;
    border-width: 5px;
    border-style: solid;
    border-color: #555 transparent transparent transparent;
}

.navbar-btn{
	margin-right: 20px;
}

/* Toggle this class - hide and show the popup */
.popup .show {
    visibility: visible;
    -webkit-animation: fadeIn .5s;
    animation: fadeIn .5s;
}
.popup:hover{
	background-color: #CFCFCF;
}

/* Add animation (fade in the popup) */
@-webkit-keyframes fadeIn {
    from {opacity: 0;} 
    to {opacity: 1;}
}

@keyframes fadeIn {
    from {opacity: 0;}
    to {opacity:1 ;}
}


</style> 



<script type='text/javascript'>

$(function(){
	$('.header').click(function(){
		$(this).nextUntil('.header').each(function(){	
		$(this).toggle(150);
		});
	});
});

function myFunction() {
	var id = String(arguments[0]);
    var popup = document.getElementById("myPopup"+id);
    popup.classList.toggle("show");
}

</script>



	</head>
	<body>
	<div class="container">
	<c:if test="${! empty errorMessage}">
		<div class="error">${errorMessage}</div>
	</c:if>
	<form action="${pageContext.servletContext.contextPath}/TestViewInventory" method="post">
		<script src="_view/javaScript/navbar.js"></script>
		<div> 
			<input type="Submit" name="resetInventory" value="Reset Inventory">
			<input type="Submit" name="populateTables" value="Populate Tables">
		</div>	
		<div class= "row" id= "myContainer">
			<c:forEach items="${inventories}" var="inventories" varStatus="inventoriesStatus"> 								
			<div class="columns col">
			<ul class="price">
			<li class="header">${inventories.inventoryName}</li>
			<li class="grey">
				Bin Capacity: ${inventories.binCapacity}<br>
				Remove Limit: ${inventories.userRemoveLimit}
			</li>
				<c:forEach items="${racks}" var="racks" varStatus="racksStatus">
					<c:if test="${inventories.inventory_id == racks.inventory_id}">
						<li>
							<div class= "priceHead dropdown">
								<div class="dropdown">
									<button class="dropbtn">Rack: ${racks.rack_id}</button>
									<div class="dropdown-content">
									<c:forEach items="${bins}" var="bins" varStatus="binsStatus">		
										<c:if test="${bins.rack_id == racks.rack_id}">
											<div class="popup" onclick="myFunction(${binsStatus.count})">${bins.resistance} &#x2126;<br> Count: ${bins.count}<br>
												<div class="progress">
													<div class="progress-bar progress-bar-striped active" role="progressbar" style="width:${(bins.count / inventories.binCapacity)*100}%">
														<b><font color="red">${(bins.count / inventories.binCapacity)*100}%</font></b>
													</div>
												</div>
												<input type= "hidden" name= "popup_id" value= "${binsStatus.count}">
												<div class="popuptext" id="myPopup${binsStatus.count}">
													<div>
														<input type= "hidden" name= "rack_id" value= "${bins.rack_id}">
														<span>Resistance: </span>
														<input type="number" min="1" name="resistance" size="12" value="1"/>
														<span>Count: </span>
														<input type="number" min="1" max="${binCap}"name="count" size="12" value="1"/>
														<input type="Submit" name="addBin" value="Add Bin!">
													</div>
												</div>
											</div>
										</c:if>
									</c:forEach>
									</div>
								</div>
							</div><span class="rackDesc"><br>Tolerance: ${racks.tolerance}<br>Power Rating: ${racks.wattage}</span>
						</li>
					</c:if>
				</c:forEach>
				<li class="grey">
				<button class="button" type="submit" name="deleteInventory" value="${inventories.inventory_id}">Delete</button>
				
				<div class="popup" onclick="myFunction(${inventoriesStatus.count})">Add Rack
					<input type= "hidden" name= "popup_id" value= "${inventoriesStatus.count}">
						<div class="popuptext" id="myPopup${inventoriesStatus.count}">
						<div>
								<span>Resistance: </span>
								<span>Count: </span>
						</div>
					</div>
				</div>
				</li>
		</ul>
		</div>								
		</c:forEach>
		</div>
	</form>
	</div>
	</body>
</html>

