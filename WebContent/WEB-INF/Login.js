$('#Login').button().click(function(){
	var username = $('#username').value;
		var password = $('#password').value;
		 var data = "user = "+username+"; password = "+password;
		 var json = $.getJSON('./Login.action',data, function(user){
			 if(user.Out =="success"){
				 //load profile
			 } 
			 else{
				 $('#login').append("Incorrect username or password, please try again");
			 }
		 });
	    
	});
