
	var video = document.querySelector("#videoElement");
	
	// check for getUserMedia support
	navigator.getUserMedia = navigator.getUserMedia || navigator.webkitGetUserMedia || navigator.mozGetUserMedia || navigator.msGetUserMedia || navigator.oGetUserMedia;
	 
	if (navigator.getUserMedia) {       
	    // get webcam feed if available
	    navigator.getUserMedia({video: true}, handleVideo, videoError);
	}
	 
	function handleVideo(stream) {
	    // if found attach feed to video element
	    video.src = window.URL.createObjectURL(stream);
	}
	 
	function videoError(e) {
	    console.log(e);
	}
	
	var v,canvas,context,w,h;
	document.addEventListener('DOMContentLoaded', function(){
	    // when DOM loaded, get canvas 2D context and store width and height of element
	    v = document.getElementById('videoElement');
	    canvas = document.getElementById('canvas2');
	    context = canvas.getContext('2d');
	    w = canvas.width;
	    h = canvas.height;
	    
	    $("#canvas2").hide();
	    $("#saveMediaModal").hide();
	
	},false);
	
	function draw(v,context,w,h) {
	
	    if(v.paused || v.ended) return false; // if no video, exit here
	
	    context.drawImage(v,0,0,w,h); // draw video feed to canvas
	   
	   var uri = canvas.toDataURL("image/png"); // convert canvas to data URI
	      
	}
	
	var takePhoto = document.getElementById('photo');
	takePhoto.addEventListener('click', function(e){
	   
		$("#canvas2").show();
		draw(v,context,w,h); // when take photo button is clicked, draw video feed to canvas
	   
	});

	
	var recordButton = document.getElementById('record');
	recordButton.addEventListener('click', function (e) {
		$("#canvas2").show();
		
		// not working
		/*var video = document.getElementById('videoElement');
		var streamRecorder;
		var webcamstream;

		if (navigator.getUserMedia) {
		  navigator.getUserMedia({video: true}, function(stream) {
		    video.src = window.URL.createObjectURL(stream);
		    webcamstream = stream;
		  });
		} 
		else {
		   alert ('failed');
		}

		function startRecording() {
		    streamRecorder = webcamstream.record();
		    setTimeout(stopRecording, 15000);
		}
		
		function stopRecording() {
		    streamRecorder.getRecordedData(postVideoToServer);
		}
		
		function postVideoToServer(videoblob) {

		    var data = {};
		    data.video = videoblob;
		    data.metadata = 'Raspberry CCTV video stream';
		    data.action = "save_video";
		   // jQuery.post("http://", data, onUploadSuccess);
		}*/
		
	});
	
	
	var stopButton = document.getElementById('stop');
	stopButton.addEventListener('click', function (e) {
		
		stopRecording();
	});
	
	
	var saveButton = document.getElementById('save');
	saveButton.addEventListener('click', function (e) {
		var ua = window.navigator.userAgent;
		var date = new Date();
		
		
		if(ua.indexOf("Chrome") > 0) {
			// save image as png
		    var link = document.createElement('a');
		    link.download = date + ".png";
		    link.href = canvas.toDataURL("image/png").replace("image/png", "image/octet-stream");
		    link.click();
		}
		else {
			alert("Please use Chrome browser to save images.");
		}
		
		/**
		 * Upload the file sending it via Ajax at the Spring Boot server.
		 */
		 
		//$('#saveMediaModal').modal('show');
	});
	
	var clearButton = document.getElementById('clear');
	clearButton.addEventListener('click', function (e) {
		context.clearRect(0, 0, 500, 375);
		$("canvas2").hide();
		location.reload();
		
	});
	
	
	
	