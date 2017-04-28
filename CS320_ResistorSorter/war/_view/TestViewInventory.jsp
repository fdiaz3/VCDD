<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>


<html>
	<head>
  <meta http-equiv="content-type" content="text/html; charset=UTF-8">
  <meta name="robots" content="noindex, nofollow">
  <meta name="googlebot" content="noindex, nofollow">

  
  

  
  
  

  

  <script type="text/javascript" src="//code.jquery.com/jquery-git.js"></script>

  

  
    <link rel="stylesheet" type="text/css" href="/css/normalize.css">
  

  

  
    <link rel="stylesheet" type="text/css" href="/css/result-light.css">
  

  
    
      <script type="text/javascript" src="http://mottie.github.com/tablesorter/js/jquery.tablesorter.js"></script>
    
  
    
      <script type="text/javascript" src="http://mottie.github.com/tablesorter/js/jquery.tablesorter.widgets.js"></script>
    
  
    
      <link rel="stylesheet" type="text/css" href="http://mottie.github.com/tablesorter/css/theme.blue.css">
    
  

  <style type="text/css">
    .tablesorter-childRow td {
  display: none;
}
.tablesorter-childRow.show td {
  display: table-cell;
}

/* extra css needed because there are 5 child rows */
/* no zebra striping */
.tablesorter-blue tbody > tr:hover + tr.tablesorter-childRow + tr.tablesorter-childRow + tr.tablesorter-childRow > td,
.tablesorter-blue tbody > tr:hover + tr.tablesorter-childRow + tr.tablesorter-childRow + tr.tablesorter-childRow + tr.tablesorter-childRow > td,
.tablesorter-blue tbody > tr:hover + tr.tablesorter-childRow + tr.tablesorter-childRow + tr.tablesorter-childRow + tr.tablesorter-childRow + tr.tablesorter-childRow > td,

/* with zebra striping */
.tablesorter-blue tbody > tr.even:hover + tr.tablesorter-childRow + tr.tablesorter-childRow + tr.tablesorter-childRow + tr.tablesorter-childRow > td .tablesorter-blue tbody > tr.even:hover + tr.tablesorter-childRow + tr.tablesorter-childRow + tr.tablesorter-childRow + tr.tablesorter-childRow + tr.tablesorter-childRow > td .tablesorter-blue tbody > tr.even:hover + tr.tablesorter-childRow + tr.tablesorter-childRow + tr.tablesorter-childRow + tr.tablesorter-childRow > td,
.tablesorter-blue tbody > tr.even:hover + tr.tablesorter-childRow + tr.tablesorter-childRow + tr.tablesorter-childRow + tr.tablesorter-childRow + tr.tablesorter-childRow > td {
  background: #d9d9d9;
}

.tablesorter-blue tbody > tr.odd:hover + tr.tablesorter-childRow + tr.tablesorter-childRow + tr.tablesorter-childRow > td,
.tablesorter-blue tbody > tr.odd:hover + tr.tablesorter-childRow + tr.tablesorter-childRow + tr.tablesorter-childRow + tr.tablesorter-childRow > td,
.tablesorter-blue tbody > tr.odd:hover + tr.tablesorter-childRow + tr.tablesorter-childRow + tr.tablesorter-childRow + tr.tablesorter-childRow + tr.tablesorter-childRow > td {
  background: #bfbfbf;
}

  </style>

  <title>Tablesorter demo by Mottie</title>

  
</head>

<body>
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

  




<script type='text/javascript'>//<![CDATA[

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

});

//]]> 

</script>

  <script>
  // tell the embed parent frame the height of the content
  if (window.parent && window.parent.parent){
    window.parent.parent.postMessage(["resultsFrame", {
      height: document.body.getBoundingClientRect().height,
      slug: "sdsobvp9"
    }], "*")
  }
</script>

</body>

	
</html>