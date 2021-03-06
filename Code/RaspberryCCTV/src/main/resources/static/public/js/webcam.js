	/*
	 * webcam javascript file
	 * specifies the on click functionality of the buttons and
	 * the drawing to the canvas functionality.
	 */


	'use strict'
	var video = document.querySelector("#videoElement");
	var oFilter = 0;
	var filters = ['grayscale', 'sepia', 'invert', 'none'];
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
	
		
	},false);