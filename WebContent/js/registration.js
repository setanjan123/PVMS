$(document).on("submit","#register",function(e)
{
var data=$(this).serialize();
var request=
	   {
	     url : "/PVMS/register",
		 type : 'POST',
		 data: data,
		 dataType: "html",
		 async: false,
         success: function(data){
		 alert(data);
		 window.location.href = "/PVMS/html/login.html";
		 }
								
	  };
$.ajax(request);
e.preventDefault();	
});