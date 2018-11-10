$(document).on("submit","#loginform",function(e)
{
var data=$(this).serialize();
var request=
	   {
	     url : "/PVMS/login",
		 type : 'POST',
		 data: data,
		 dataType: "html",
		 async: false,
         success: function(data){
		 alert(data);
		 $(location).attr('href', '/PVMS/HomeController');
								}
	  };
$.ajax(request);
e.preventDefault();
});