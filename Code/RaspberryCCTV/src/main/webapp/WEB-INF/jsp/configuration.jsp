<%@include file="includes/mainheader.jsp"%>

	<div class="configuration">	
  		<div class="panel panel-default">
			<div class="panel-heading">
				<h3 class="panel-title">Raspberry CCTV Configuration</h3>
			</div>
			<div class="panel-body">
				<form:form role="form" method="post">
					<div class="form-group-inline">
						<p class="help-block">Toggle facial recognition on or off.</p>
						<input id="facial-recognition" name="facial-recognition" type="checkbox" class="form-control" checked="checked"/>
					</div>
					<div class="form-group">
						<p class="help-block">Toggle facial features recognition on or off.</p>
						<input id="facial-features" name="facial-features" type="checkbox" class="form-control" checked="checked" />
					</div>
					<div class="form-group">
						<p class="help-block">Toggle friend recognition on or off.</p>
						<input id="tag-friend" name="tag-friend" type="checkbox" class="form-control" checked="checked"/>
					</div>
					<div class="form-group">
						<p class="help-block">Toggle grayscale on or off.</p>
						<input id="grayscale" name="grayscale" type="checkbox" class="form-control" />
					</div>
					<div class="form-group">
						<p class="help-block">Toggle object detection on or off.</p>
						<input id="object-detection" name="object-detection" type="checkbox" class="form-control" />
					</div>
					<div class="form-group">
						<p class="help-block">Toggle grid on or off.</p>
						<input id="grid" name="grid" type="checkbox" class="form-control" />
					</div>
					<div class="btn-group pull-right">
		 	  			<button class="btn btn-default" type="button" value="Save Changes" id="save"> Save changes</button>
		 	  		</div>	
				</form:form>
	  		</div>
	  	 </div>
	</div>

<%@include file="includes/mainfooter.jsp"%>