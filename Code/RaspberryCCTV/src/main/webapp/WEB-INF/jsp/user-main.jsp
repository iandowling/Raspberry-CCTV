<%@include file="includes/header.jsp"%>

<div id="user-main">
	<h4>RaspberryCCTV Camera Feed</h4>

	<!-- For devices that support getUserMedia and have a webcam we can display the feed in a video element -->
	<div id="container">
  		<video autoplay="autoplay" id="videoElement"></video>
	</div>

 	<div class="btn-group2">
 	  	<button class="btn btn-default" type="button" value="Clear" id="clear"><span class="fa fa-remove"></span> Clear</button>		
 		<button class="btn btn-default" type="button" value="Photo" id="photo"><span class="fa fa-camera-retro"></span> Take Photo</button>
 		<button class="btn btn-default" type="button" value="Record" id="record"><span class="fa fa-video-camera" ></span> Recored Video</button>
 		<button class="btn btn-default" type="button" value="Stop" id="stop"><span class="fa fa-stop" ></span> Stop Recording</button>
 		<button class="btn btn-default" type="button" value="Save" id="save"><span class="fa fa-save"></span> Save</button>
   </div>
   <br />	   
   <!-- Selected image will be draw to a canvas -->
	<canvas id="canvas" width="500" height="375"></canvas>
 
  <br />
  <div class="text-to-speech">
	<div class="col-lg-10">
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
		
<%@include file="includes/footer.jsp"%>