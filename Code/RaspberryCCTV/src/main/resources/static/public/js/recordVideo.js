
	/*// window.isSecureContext could be used for Chrome
	var isSecureOrigin = location.protocol === 'https:' ||
	location.host === 'localhost';
	if (!isSecureOrigin) {
	  alert('getUserMedia() must be run from a secure origin: HTTPS or localhost.' +
	    '\n\nChanging protocol to HTTPS');
	  location.protocol = 'HTTPS';
	}
	
	var mediaSource = new MediaSource();
	mediaSource.addEventListener('sourceopen', handleSourceOpen, false);
	var options = {mimeType: 'video/webm,codecs=vp9'};
	var mediaRecorder;
	var recordedBlobs = [];
	var sourceBuffer;
	var video = document.querySelector("#videoElement");
	var recordedVideo = document.querySelector('video#recorded');
	var recordButton = document.querySelector('button#record');
	var playButton = document.querySelector('button#play');
	var downloadButton = document.querySelector('button#saveVideo');
	
	navigator.getUserMedia = navigator.getUserMedia ||
	  navigator.webkitGetUserMedia || navigator.mozGetUserMedia;

	var constraints = {
	  audio: true,
	  video: true
	};

	navigator.getUserMedia(constraints, successCallback, errorCallback);

	function successCallback(stream) {
	  console.log('getUserMedia() got stream: ', stream);
	  if (window.URL) {
	    video.src = window.URL.createObjectURL(stream);
	  } 
	  else {
	    video.src = stream;
	  }
	}
	
	function errorCallback(error) {
	  console.log('navigator.getUserMedia error: ', error);
	}
	
	function handleSourceOpen(event) {
	  sourceBuffer = mediaSource.addSourceBuffer('video/webm; codecs="vp8"');
	  console.log('Source buffer: ', sourceBuffer);
	}
		
	function handleDataAvailable(event) {
	  if (event.data && event.data.size > 0) {
	    recordedBlobs.push(event.data);
	  }
	}
	
	function handleStop(event) {
		console.log('Recorder stopped: ', event);
	}
	
	function toggleRecording() {
	  if (recordButton.textContent === 'Start Recording') {
		$("#recorded").show();
		startRecording();
	  } 
	  else {
	    stopRecording();
	    recordButton.textContent === 'Stop Recording';
	    playButton.disabled = false;
	    downloadButton.disabled = false;
	  }
	}
	
	function startRecording() {	
	  mediaRecorder = new MediaRecorder(stream, options);
	  console.log('Created MediaRecorder', mediaRecorder, 'with options', options);
	  recordButton.textContent = 'Stop Recording';
	  playButton.disabled = true;
	  downloadButton.disabled = true;
	  mediaRecorder.onstop = handleStop;
	  mediaRecorder.ondataavailable = handleDataAvailable;
	  mediaRecorder.start(3000); // collect 10ms of data
	  console.log('MediaRecorder started', mediaRecorder);
	}
	
	function stopRecording() {
	  mediaRecorder.stop();
	  console.log('Recorded Blobs: ', recordedBlobs);
	  recordedVideo.controls = true;
	}
	
	function play() {
	  var superBuffer = new Blob(recordedBlobs, {type: 'video/webm'});
	  recordedVideo.src = window.URL.createObjectURL(superBuffer);
	}
	
	function download() {
	  var blob = new Blob(recordedBlobs, {type: 'video/webm'});
	  var url = window.URL.createObjectURL(blob);
	  var a = document.createElement('a');
	  a.style.display = 'none';
	  a.href = url;
	  a.download = 'test.webm';
	  document.body.appendChild(a);
	  a.click();
	  setTimeout(function() {
	    document.body.removeChild(a);
	    window.URL.revokeObjectURL(url);
	  }, 100);
	}
	
	var recordButton = document.getElementById('record');
	recordButton.addEventListener('click', function (e) {
		toggleRecording();
		
	});
	
	var playButton = document.getElementById('play');
	playButton.addEventListener('click', function (e) {
		play();
		
	});
	
	var saveVideo = document.getElementById('saveImg');
	saveVideo.addEventListener('click', function (e) {
		download();
	});*/


var record = document.getElementById('record');
var stop = document.getElementById('stop');
var video = document.getElementById('videoElement');
video.setAttribute('controls', '');

var chunks = [];

var constraints = {video: true};

var onSuccess = function(stream) {
  console.log("stream", stream);
  var mediaRecorder = new MediaRecorder(stream);

  record.onclick = function() {
	$("#recorded").show();
    chunks = [];
    mediaRecorder.start();
    console.log("recorder started");
  }

  stop.onclick = function() {
    mediaRecorder.stop();
    console.log("recorder stopped");
  }

  mediaRecorder.ondataavailable = function(e) {
    console.log("data available");
    chunks.push(e.data);
  }

  mediaRecorder.onstop = function(e) {
    console.log('onstop fired');
    var blob = new Blob(chunks, { 'type' : 'video/webm; codecs=opus' });
    video.src = window.URL.createObjectURL(blob);
  };

  mediaRecorder.onwarning = function(e) {
    console.log('onwarning fired');
  };

  mediaRecorder.onerror = function(e) {
    console.log('onerror fired');
  };
};

var onError = function(err) {
  console.log('The following error occured: ' + err);
}

