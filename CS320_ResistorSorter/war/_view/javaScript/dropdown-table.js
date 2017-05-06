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

});

$(function(){
$('.header1').click(function(){

$(this).nextUntil('tr.header1').slideToggle(150);


});
});