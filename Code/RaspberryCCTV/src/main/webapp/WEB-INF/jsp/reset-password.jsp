<%@include file="includes/header.jsp"%>

<div class="reset-pass">
  <div class="panel panel-default">
	<div class="panel-heading">
		<h3 class="panel-title">Reset password</h3>
	</div>
	<div class="panel-body">
	
		<form:form modelAttribute="resetPasswordForm" role="form">
			<form:errors />
			<div class="form-group">
				<form:label path="password">Type new password</form:label>
				<form:password path="password" class="form-control" placeholder="Password" />
				<form:errors cssClass="error" path="password" />
			</div>
			<div class="form-group">
				<form:label path="retypePassword">Retype new password</form:label>
				<form:password path="retypePassword" class="form-control" placeholder="Retype password" />
				<form:errors cssClass="error" path="retypePassword" />
			</div>
			<button type="submit" class="btn btn-default">Submit</button>
		</form:form>
	</div>
  </div>
</div>

<%@include file="includes/footer.jsp"%>
