window.LoginView = Backbone.View.extend ({
	
	initialize:function() {
		console.log('Initializing Login View');
	},
	
	events: {
		"click #loginButton" : "login"
	},
	
	render:function() {
		$(this.el).html(this.template());
		return this;
	},
	
	login:function(event) {
		event.preventDefault(); //don't let button submit form
		
		$('.alert-error').hide(); //hide errors on new submit
		var url = '/login';
		console.log('Logging in...');
		var formValues = {
			email: 		$('#inputEmail').val(),
			password: 	$('#inputPassword').val()
		};
		
		$ajax ({
			url:url,
			type:'POST',
			dataType: 'json',
			data: formValues,
			success:function(data) {
				console.log(["Login request details: ", data]);
				
				if(data.error) {
					$('.alert-error').text(data.error.text).show();
				}
				else {
					window.location.replace('/home');
				}
			}
		});
 	} 
});