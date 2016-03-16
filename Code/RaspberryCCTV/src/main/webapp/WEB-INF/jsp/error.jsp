<%@include file="includes/header.jsp"%>

<div class="error">	
	<div class="alert alert-danger">
		<p>There was an unexpected error (type=${error}, status=${status}): <i>${message}</i></p>
		<p>Click <a href="/">here</a> to go to home page</a></p>
	</div>
</div>

<%@include file="includes/footer.jsp"%>