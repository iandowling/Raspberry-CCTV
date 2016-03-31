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
    canvas = document.getElementById('canvas');
    context = canvas.getContext('2d');
    w = canvas.width;
    h = canvas.height;

},false);

function draw(v,c,w,h) {

    if(v.paused || v.ended) return false; // if no video, exit here

    context.drawImage(v,0,0,w,h); // draw video feed to canvas
   
   var uri = canvas.toDataURL("image/png"); // convert canvas to data URI
      
}

document.getElementById('photo').addEventListener('click',function(e){
    
    draw(v,context,w,h); // when save button is clicked, draw video feed to canvas
    
});

var recordButton = document.getElementById('record');
recordButton.addEventListener('click', function (e) {
	var mediaConstraints = {
		    video: true
		};

		navigator.getUserMedia(mediaConstraints, onMediaSuccess, onMediaError);

		function onMediaSuccess(stream) {
		    var mediaRecorder = new MediaStreamRecorder(stream);
		    mediaRecorder.mimeType = 'video/webm';

		    // for gif recording
		    // mediaRecorder.mimeType = 'image/gif';

		    mediaRecorder.width = canvas.width;
		    mediaRecorder.height = canvas.height;

		    mediaRecorder.ondataavailable = function (blob) {
		        // POST/PUT "Blob" using FormData/XHR2
		        var blobURL = URL.createObjectURL(blob);
		        canvas = document.write('<a href="' + blobURL + '">' + blobURL + '</a>');
		    };
		    mediaRecorder.start(2000);
		}
	function onMediaError(e) {
	    console.error('media error', e);
	}
});


var stopButton = document.getElementById('stop');
stopButton.addEventListener('click', function (e) {
	this.disabled = true;
    mediaRecorder.stop();
    mediaRecorder.stream.stop();
});


var saveButton = document.getElementById('save');
saveButton.addEventListener('click', function (e) {
	var ua = window.navigator.userAgent;
	
	if(ua.indexOf("Chrome") > 0) {
		// save image as png
	    var link = document.createElement('a');
	    link.download = "image.png";
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
	
	videoRecorder.video = yourHTMLVideoElement;
	videoRecorder.onStartedDrawingNonBlankFrames = function() {
	    videoRecorder.clearOldRecordedFrames(); // clear all blank frames
	    audioRecorder.start(interval);
	};
	
});