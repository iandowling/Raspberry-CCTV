<%@include file="includes/header.jsp"%>

	<div class="configuration">	
  		<div class="panel panel-default">
			<div class="panel-heading">
				<h3 class="panel-title">Raspberry CCTV Configuration</h3>
			</div>
			<div class="panel-body">
				<form:form role="form" method="post">
					<div class="form-group-inline">
						<p class="help-block">Toggle facial recognition off or on.</p>
						<input id="facial-recognition" name="facial-recognition" type="checkbox" class="form-control" />
					</div>
					<div class="form-group">
						<p class="help-block">Toggle ground mapping off or on.</p>
						<input id="facial-recognition" name="facial-recognition" type="checkbox" class="form-control" />
					</div>
					<div class="form-group">
						<p class="help-block">Toggle object recognition off or on.</p>
						<input id="facial-recognition" name="facial-recognition" type="checkbox" class="form-control" />
					</div>
				</form:form>
	  		</div>
	  	 </div>
	</div>

<%@include file="includes/footer.jsp"%>