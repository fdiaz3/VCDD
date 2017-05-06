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
    color: #fff;
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

/* Toggle this class - hide and show the popup */
.popup .show {
    visibility: visible;
    -webkit-animation: fadeIn .5s;
    animation: fadeIn .5s;
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
    




<script type='text/javascript'>//<![CDATA[
$(function(){
$('.header').click(function(){


$(this).nextUntil('.header').each(function(){
	
	
		$(this).toggle(150);
	
	
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

function myFunction() {
    var popup = document.getElementById("myPopup");
    popup.classList.toggle("show");
}

</script>


	</head>

	<body>
	<div class="container">
	<script src="_view/javaScript/navbar.js"></script>
			
		
 			
		<div class= "row" id= "myContainer">
			
			<c:forEach items="${inventories}" var="item" varStatus="status"> 								
 				<div class="columns">
			  <ul class="price">
			    <li class="header">${item.inventoryName}</li>
			    <li class="grey"> Bin Capacity: ${item.binCapacity} 
			    				<br>Remove Limit: ${item.userRemoveLimit}</li>
			    <c:forEach items="${racks}" var="item1" varStatus="status1">
			    	<c:if test="${item.inventory_id == item1.inventory_id}">
				    <li><div class= "priceHead dropdown">
				    <div class="dropdown">
					  <button class="dropbtn">Rack: ${item1.rack_id}</button>
					  <div class="dropdown-content">
					  <c:forEach items="${bins}" var="item2" varStatus="status2">		
 						<c:if test="${item2.rack_id == item1.rack_id}">
						    <div class="popup" onclick="myFunction()">${item2.resistance} &#x2126; Bin <br> Count: ${item2.count}
							  <div class="popuptext" id="myPopup">
							  	<form action="${pageContext.servletContext.contextPath}/Bins" method="post">
									<div>
					
										
											
										<span>Resistance: </span>
										<input type="number" min="1" name="resistance" size="12" value="1"/>
										
										<span>Count: </span>
										<input type="number" min="1" max="${binCap}"name="count" size="12" value="1"/>
										
										<input type="Submit" name="addBin" value="Add Bin!">
										
										</div>
										</form>
							  </div>
							</div>
							  
					 	</c:if>
					  </c:forEach>
					  </div>
					  
					</div>
				    </div> <span class= "rackDesc"><br>Tolerance: ${item1.tolerance} <br>Power Rating: ${item1.wattage}</span></li>

			    </c:if>
			    </c:forEach>
			    <li class="grey"><a href="#" class="button">View Transactions</a></li>
			  </ul>
		</div>
 												
 		</c:forEach>
			



		</div>
		</div>

	</body>
	
</html>

