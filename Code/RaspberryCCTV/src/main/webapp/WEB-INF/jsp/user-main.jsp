<%@include file="includes/mainheader.jsp"%>

<div id="user-main">
	<h4>RaspberryCCTV Camera Feed</h4>
		
	<div id="video-container">
	 	<div class="feed">
  			<img src="https://136.206.17.182:8100/video_feed" id="videoElement" width="500" height="375" autoplay="autoplay" ></img>
  		</div>
  		<canvas id="canvas2" width="500" height="375"></canvas>
  		<video id="recorded" width="500" height="375" autoplay="autoplay" controls="controls"></video>
		
		<div class="camera-controls">
			<div class="btn-group2">
		 		<button class="btn btn-default" type="button" id="grayscale"><span class="fa fa-delicious"></span> Toggle Filters</button>
		 		<button class="btn btn-default" type="button"  id="photo"><span class="fa fa-camera-retro"></span> Take Photo</button>
		 		<button class="btn btn-default" type="button"  id="clear"><span class="fa fa-remove"></span> Clear</button>	
		   </div>
		   <br />
		 	<div class="btn-group2">
		 		
		 		<button class="btn btn-default" type="button"  id="record"><span class="fa fa-video-camera" ></span> Start Recording</button>
		 		<button class="btn btn-default" type="button"  id="stop"><span class="fa fa-stop"></span> Stop Recording</button>
		 	</div>
		 	<div class="btn btn-group2">
		 		<button type="submit" class="btn btn-default" type="button"  id="saveImg"><span class="fa fa-save"></span> Save Image</button>
		 		<button class="btn btn-default" type="button"  id="cloud-save" data-toggle="modal" data-target="#saveMediaModal"><span class="fa fa-dropbox"></span> Upload to Dropbox</button>
		   </div>	   
	   </div>
   </div>
  	   <br/>
   <div class="text-to-speech">
		<div class="col-lg-8">
	   		<div class="input-group">
		      <input type="text" name="text" class="form-control" id="speech" placeholder="Warn intruder..."/>
		      <span class="input-group-btn">
		        <button class="btn btn-default" type="button" onclick="responsiveVoice.speak($('#speech').val());"><span class="fa fa-send"></span></button>
		      </span>
	  		</div><!-- /input-group -->
		</div><!-- /.col-lg-6 -->
	  </div>
  <br /> 
</div>

<!-- Modal -->
<div id="saveMediaModal" class="modal fade" role="dialog">
  <div class="modal-dialog">

    <!-- Modal content-->
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal">&times;</button>
        <h4 class="modal-title"><span class="fa fa-dropbox"></span>Login to dropbox account</h4>
      </div>
      <div class="modal-body">
        <form:form role="form" method="post">
			<div class="form-group">
				<label for="file-name">Username</label>
				<input type="text" id="username" name="username" class="form-control" placeholder="Username" />
			</div>
			<div class="form-group">
				<label for="description">Password</label>
				<input type="password" id="password" name="password" class="form-control" placeholder="Password" />
			</div>
		</form:form>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
        <button type="submit" class="btn btn-default pull-right">Login</button>
      </div>
    </div>

  </div>
</div>
		
<%@include file="includes/mainfooter.jsp"%>