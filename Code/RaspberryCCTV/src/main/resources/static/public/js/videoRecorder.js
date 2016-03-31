function captureUserMedia(mediaConstraints, successCallback, errorCallback) {
 
    var mediaConstraints = {
        audio: !IsChrome && !IsOpera && !IsEdge, // record both audio/video in Firefox
        video: true
    };
    document.querySelector('record').onclick = function() {
        this.disabled = true;
        captureUserMedia(mediaConstraints, onMediaSuccess, onMediaError);
    };
    document.querySelector('stop').onclick = function() {
        this.disabled = true;
        mediaRecorder.stop();
        mediaRecorder.stream.stop();
    };
    
    document.querySelector('save').onclick = function() {
        this.disabled = true;
        mediaRecorder.save();
    };
    var mediaRecorder;
    function onMediaSuccess(stream) {
        var video = document.createElement('video');
        var videoWidth = document.getElementById('video-width').value || 320;
        var videoHeight = document.getElementById('video-height').value || 240;
        video = mergeProps(video, {
            controls: true,
            muted: true,
            width: videoWidth,
            height: videoHeight,
            src: URL.createObjectURL(stream)
        });
        video.play();
        videosContainer.appendChild(video);
        videosContainer.appendChild(document.createElement('hr'));
        mediaRecorder = new MediaStreamRecorder(stream);
        mediaRecorder.stream = stream;
        mediaRecorder.mimeType = 'video/webm'; // this line is mandatory
        mediaRecorder.videoWidth = videoWidth;
        mediaRecorder.videoHeight = videoHeight;
        mediaRecorder.ondataavailable = function(blob) {
            var a = document.createElement('a');
            a.target = '_blank';
            a.innerHTML = 'Open Recorded Video No. ' + (index++) + ' (Size: ' + bytesToSize(blob.size) + ') Time Length: ' + getTimeLength(timeInterval);
            a.href = URL.createObjectURL(blob);
            videosContainer.appendChild(a);
            videosContainer.appendChild(document.createElement('hr'));
        };
        var timeInterval = document.querySelector('#time-interval').value;
        if (timeInterval) timeInterval = parseInt(timeInterval);
        else timeInterval = 5 * 1000;
        // get blob after specific time interval
        mediaRecorder.start(timeInterval);
        document.querySelector('#stop').disabled = false;
        document.querySelector('#save').disabled = false;
    }
    function onMediaError(e) {
        console.error('media error', e);
    }
    var videosContainer = document.getElementById('container');
    var index = 1;
    // below function via: http://goo.gl/B3ae8c
    function bytesToSize(bytes) {
        var k = 1000;
        var sizes = ['Bytes', 'KB', 'MB', 'GB', 'TB'];
        if (bytes === 0) return '0 Bytes';
        var i = parseInt(Math.floor(Math.log(bytes) / Math.log(k)), 10);
        return (bytes / Math.pow(k, i)).toPrecision(3) + ' ' + sizes[i];
    }
    // below function via: http://goo.gl/6QNDcI
    function getTimeLength(milliseconds) {
        var data = new Date(milliseconds);
        return data.getUTCHours() + " hours, " + data.getUTCMinutes() + " minutes and " + data.getUTCSeconds() + " second(s)";
    }
    window.onbeforeunload = function() {
        document.querySelector('record').disabled = false;
    }
};