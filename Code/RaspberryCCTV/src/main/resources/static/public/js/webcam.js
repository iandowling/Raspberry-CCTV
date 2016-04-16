	'use strict'
	
	var video = document.querySelector("#videoElement");
	var oFilter = 0;
	var filters = ['grayscale', 'sepia', 'invert', 'none'];
	
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
	    
	    $("#recorded").hide();
	    $("#canvas2").hide();
	    $("#saveMediaModal").hide();
	
	},false);
	
	function draw(v,context,w,h) {
	
	    if(v.paused || v.ended) return false; // if no video, exit here
	
	    context.drawImage(v,0,0,w,h); // draw video feed to canvas
	   
	   var uri = canvas.toDataURL("image/png"); // convert canvas to data URI
	      
	}
	
	var toggleFilter = document.getElementById('grayscale');
	toggleFilter.addEventListener('click', function(e){
		video.className = '';
		canvas.className = '';
		var filter = filters[oFilter++ % filters.length];
		
		if(filter) {
			video.classList.add(filter);
			canvas.classList.add(filter);
		}
	});
	
	var takePhoto = document.getElementById('photo');
	takePhoto.addEventListener('click', function(e){
	   
		$("#canvas2").show();
		draw(v,context,w,h); // when take photo button is clicked, draw video feed to canvas
	   
	});
	
	var saveImage = document.getElementById('saveImg');
	saveImage.addEventListener('click', function (e) {
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
		
	});
	
	var clearButton = document.getElementById('clear');
	clearButton.addEventListener('click', function (e) {
		context.clearRect(0, 0, 500, 375);
		$("canvas2").hide();
		location.reload();
		
	});
	
	var cloudButton = document.getElementById('cloud-save');
	cloudButton.addEventListener('click', function (e) {
		$('#saveMediaModal').modal('show');
	});
	
	