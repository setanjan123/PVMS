/**
 * 
 */

$(document).on("submit","#display",function(e)
{
	e.preventDefault();
	$('.inputDisabled').removeAttr("disabled");
	$("#button1").attr("value","Update");
	$(this).removeAttr("id");
})