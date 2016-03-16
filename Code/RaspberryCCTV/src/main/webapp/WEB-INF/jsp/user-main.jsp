<%@include file="includes/header.jsp"%>

<div id="user-main">
	  <ol>
		<h4>Camera view 1</h4>
    	<img src= "http://192.168.2.37:8081/?action=stream"/>
    	<!--  <img src= "/public/images/house.gif"/>-->
    	<div class="btn-group2">			
    		<button class="btn btn-default" type="button"><a class="fa fa-camera-retro" id="record-img"></a></button>
    		<button class="btn btn-default" type="button"><a class="fa fa-video-camera" id="record-video"></a></button>
		</div>
	  </ol>
	 </div>
	  
	<br />
	
	<div class="audio">
		<div class="form-group">
			<input type="text" name="text-to-speech" placeholder="Warn intruder..." id="alert" />
			<input type="submit" value="Go!"/>
		</div>
	</div>

<%@include file="includes/footer.jsp"%>