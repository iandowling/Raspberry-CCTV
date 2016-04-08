<%@include file="includes/mainheader.jsp"%>

<div id="user-main">
	<h4>RaspberryCCTV Camera Feed</h4>
	
	<!-- For devices that support getUserMedia and have a webcam we can display the feed in a video element -->
	<div id="video-container">
  		<video id="videoElement" width="500" height="375" autoplay="autoplay" controls = "controls" ></video>
  		<canvas id="canvas" width="500" height="375"></canvas>
  		<canvas id="canvas2" width="500" height="375"></canvas>

		<div class="camera-controls">
		 	<div class="btn-group2">
		 	  	<button class="btn btn-default" type="button" value="Clear" id="clear"><span class="fa fa-remove"></span> Clear</button>		
		 		<button class="btn btn-default" type="button" value="Photo" id="photo"><span class="fa fa-camera-retro"></span> Take Photo</button>
		 		<button class="btn btn-default" type="button" value="Record" id="record"><span class="fa fa-video-camera" ></span> Recored Video</button>
		 		<button class="btn btn-default" type="button" value="Stop" id="stop"><span class="fa fa-stop" ></span> Stop Recording</button>
		 		<button class="btn btn-default" type="button" value="Save" id="save" data-toggle="modal" data-target="#saveMediaModal"><span class="fa fa-save"></span> Save</button>
		   </div>
		   <br />	   
	   </div>
   
	   <div class="text-to-speech">
		<div class="col-lg-8">
	   		<div class="input-group">
		      <input type="text" name="text" class="form-control" placeholder="Warn intruder..."/>
		      <span class="input-group-btn">
		        <button class="btn btn-default" type="button" id="speech" value=""><span class="fa fa-send"></span></button>
		        <audio src="" class="speech" hidden="hidden" /></audio>
		      </span>
	  		</div><!-- /input-group -->
		</div><!-- /.col-lg-6 -->
	  </div>
	  <br /> 
	</div>
	
	<%-- <!-- Save media modal -->
	<div class="saveMediaModal" tabindex="-1" role="dialog" aria-labelledby="save media modal">
  		<div class="modal-dialog modal-sm">
    		<div class="modal-content">
    			<div class="panel panel-default">
					<div class="panel-heading">
						<h3 class="panel-title">Save media as</h3>
					</div>
					<div class="panel-body">
						<form:form role="form" method="post">
							<div class="form-group">
								<label for="file-name">File name</label>
								<input type="text" id="file-name" name="file-name" class="form-control" placeholder="File name" />
							</div>
							<div class="form-group">
								<label for="description">Description</label>
								<input type="text" id="desc" name="desc" class="form-control" placeholder="Description" />
							</div>
							
							<button type="submit" class="btn btn-default pull-right">Save media</button>
						</form:form>
					</div>
  				</div>
			</div>
    	</div>
  	</div> --%>
	

	<!-- Modal -->
	<div id="saveMediaModal" class="modal fade" role="dialog">
	  <div class="modal-dialog">
	
	    <!-- Modal content-->
	    <div class="modal-content">
	      <div class="modal-header">
	        <button type="button" class="close" data-dismiss="modal">&times;</button>
	        <h4 class="modal-title">Save media as</h4>
	      </div>
	      <div class="modal-body">
	        <form:form role="form" method="post">
				<div class="form-group">
					<label for="file-name">File name</label>
					<input type="text" id="file-name" name="file-name" class="form-control" placeholder="File name" />
				</div>
				<div class="form-group">
					<label for="description">Description</label>
					<input type="text" id="desc" name="desc" class="form-control" placeholder="Description" />
				</div>
			</form:form>
	      </div>
	      <div class="modal-footer">
	        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
	        <button type="submit" class="btn btn-default pull-right">Save media</button>
	      </div>
	    </div>
	
	  </div>
	</div>
</div>
		
<%@include file="includes/mainfooter.jsp"%>