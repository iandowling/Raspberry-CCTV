
$( document ).ready(function() {
		
	// face recognition stream 
	var video   = document.getElementById('videoElement');
	var canvas  = document.getElementById('canvas');
	var context = canvas.getContext('2d');
	var tracker = new tracking.ObjectTracker('face');
	
	tracker.setInitialScale(4);
	tracker.setStepSize(2);
	tracker.setEdgesDensity(0.1);

	tracker.on('track', function(event){
		context.clearRect(0, 0, canvas.width, canvas.height);
		
		event.data.forEach(function(rect) {
			context.strokeStyle = '#a64ceb';
			context.strokeRect(rect.x, rect.y, rect.width, rect.height);
			context.font = '12px Questrial';
			context.fillText('x: ' + rect.x + 'px', rect.x + rect.width + 10, rect.y + 22);
	        context.fillText('y: ' + rect.y + 'px', rect.x + rect.width + 10, rect.y + 44);
		});
	});
	
	var trackingThread = tracking.track(video, tracker, {camera: true});
	
});
	
	/*function loadSettings() {
		var checkboxes = document.querySelectorAll('input[type=checkbox]');
		
		for (i = 0; i < checkboxes.length; i++) {
			checkboxes[i].checked = localStorage.getItem(checkboxes[i].value) === 'true' ? true : false;
		}
	}
	var saveSettings = document.getElementById('save');
	save.addEventListener('click', function saveSettings() {
		var checkboxes = document.querySelectorAll('input[type=checkbox]');
		
		for (i = 0; i < checkboxes.length; i++) {
	        localStorage.setItem(checkboxes[i].value, checkboxes[i].checked); 
	    }
	});
	
	var facialCheck = document.getElementById("facial-recognition");
	facialCheck.onclick = function() {
		if (this.checked) {
			trackingThread.run();
		}
		else {
			trackingThread.stop();
		}
	}*/

	
	