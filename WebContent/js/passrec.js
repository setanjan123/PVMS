$(document).on("click","#b1",function(e){
	var data=$("#t1").val();
	var request=
	   {
	     url : "../passrec",
		 type : 'POST',
		 data: {uname:data,type:"getHQues"},
		 dataType: "html",
		 async: false,
      success: function(data){
      if (!$.trim(data))
      alert("Invalid Username");
      else
      {
      $('#d1').text('');
      $('#d1').append(data);
      }
		}
	  };
$.ajax(request);
e.preventDefault();
	})
	
$(document).on("click","#b2",function(e){
	var data=$("#t2").val();
	var request=
	   {
	     url : "../passrec",
		 type : 'POST',
		 data: {hans:data,type:"valHans"},
		 dataType: "html",
		 async: false,
      success: function(data){
      alert(data);
      $("")
		}
	  };
$.ajax(request);
e.preventDefault();
	})