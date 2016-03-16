<%@include file="includes/header.jsp"%>

<div class="panel panel-default">

	<div class="panel-heading">
		<h3 class="panel-title">Forgot password?</h3>
	</div>
	
	<div class="panel-body">
	
		<form:form modelAttribute="forgotPasswordForm" role="form">
		
			<form:errors />
		
			<div class="form-group">
				<form:label path="email">Email address</form:label>
				<form:input path="email" type="email" class="form-control" placeholder="Enter email" />
				<form:errors cssClass="error" path="email" />
				<p class="help-block">Please enter your email address</p>
			</div>
			
			<button type="submit" class="btn btn-default">Reset password</button>
			
		</form:form>
	</div>
</div>

<%@include file="includes/footer.jsp"%>
