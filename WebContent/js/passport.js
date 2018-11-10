/**
 * 
 */
$(document).on("change", "#dropdown1", function()
		{
	     var stateid = $(this).val();
	     var request = {
	    		 url:"/PVMS/PassportController",
	    		 type : 'POST',
	    		 data: {stateid:stateid,opt:"opt2"},
	    		 dataType: "html",
	    		 async: false,
	             success: function(data){
	             $("#dropdown2").text("");
	             $("#dropdown2").append("<option>Select</option>");
	             $("#dropdown2").append(data);
	             }
	     }
	     $.ajax(request);
		}
		)
		
		$(document).on("change", "#dropdown2", function()
		{
	     var cityid = $(this).val();
	     var request = {
	    		 url:"/PVMS/PassportController",
	    		 type : 'POST',
	    		 data: {cityid:cityid,opt:"opt4"},
	    		 dataType: "html",
	    		 async: false,
	             success: function(data){
	             $("#dropdown3").text("");
	             $("#dropdown3").append(data);
	             }
	     }
	     $.ajax(request);
		}
		)