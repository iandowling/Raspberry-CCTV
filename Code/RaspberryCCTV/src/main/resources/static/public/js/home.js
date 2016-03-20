var video = document.querySelector("#videoElement");

// check for getUserMedia support
navigator.getUserMedia = navigator.getUserMedia || navigator.webkitGetUserMedia || navigator.mozGetUserMedia || navigator.msGetUserMedia || navigator.oGetUserMedia;
 
if (navigator.getUserMedia) {       
    // get webcam feed if available
    navigator.getUserMedia({video: true,  audio: true}, handleVideo, videoError);
}
 
function handleVideo(stream) {
    // if found attach feed to video element
    video.src = window.URL.createObjectURL(stream);
}
 
function videoError(e) {
    // no webcam found - do something
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

var clearButton = document.getElementById('clear');
clearButton.addEventListener('click', function (e) {
	context.clearRect(0, 0, canvas.width, canvas.height);
	
});

document.getElementById('photo').addEventListener('click',function(e){
    
    draw(v,context,w,h); // when save button is clicked, draw video feed to canvas
    
});

var recordButton = document.getElementById('record');
recordButton.addEventListener('click', function (e) {
	var url = canvas.toDataURL('image/webp', 1);
	var rafId;
	var frames = [];
	var CANVAS_WIDTH = canvas.width;
	var CANVAS_HEIGHT = canvas.height;

	frames = []; // clear existing frames;
	  startTime = Date.now();

	  toggleActivateRecordButton();
	  $('#stop').disabled = false;

	  function drawVideoFrame_(time) {
	    rafId = requestAnimationFrame(drawVideoFrame_);

	    ctx.drawImage(video, 0, 0, CANVAS_WIDTH, CANVAS_HEIGHT);

	    document.title = 'Recording...' + Math.round((Date.now() - startTime) / 1000) + 's';

	    // Read back canvas as webp.
	    var url = canvas.toDataURL('image/webp', 1); // image/jpeg is way faster :(
	    frames.push(url);
	  };

	  rafId = requestAnimationFrame(drawVideoFrame_);
});

var stopButton = document.getElementById('stop');
stopButton.addEventListener('click', function (e) {
	function stop() {
		 cancelAnimationFrame(rafId);
		  endTime = Date.now();
		  $('#stop').disabled = true;
		  document.title = ORIGINAL_DOC_TITLE;

		  toggleActivateRecordButton();

		  console.log('frames captured: ' + frames.length + ' => ' +
		              ((endTime - startTime) / 1000) + 's video');

		  embedVideoPreview();
	}
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



(function($) {
    "use strict";

    $('body').scrollspy({
        target: '.navbar-fixed-top',
        offset: 60
    });

    $('#topNav').affix({
        offset: {
            top: 200
        }
    });
    
    $('#toggleVideo').click(function(){
    	   $('video-background').css('/public/images/dog.gif','/public/images/window.gif');
    });
    
    new WOW().init();
    
    $('a.page-scroll').bind('click', function(event) {
        var $ele = $(this);
        $('html, body').stop().animate({
            scrollTop: ($($ele.attr('href')).offset().top - 60)
        }, 1450, 'easeInOutExpo');
        event.preventDefault();
    });
    
    $('.navbar-collapse ul li a').click(function() {
        /* always close responsive nav after click */
        $('.navbar-toggle:visible').click();
    });

    $('#galleryModal').on('show.bs.modal', function (e) {
       $('#galleryImage').attr("src",$(e.relatedTarget).data("src"));
    });
    
})(jQuery);