package com.rcctv.opencv;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfRect;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.highgui.VideoCapture;
import org.opencv.objdetect.CascadeClassifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class Webcam {
	
	public static void startWebcam() throws InterruptedException {
		
		final Logger logger = LoggerFactory.getLogger(Webcam.class);
		System.loadLibrary("opencv_java249");
		
		CascadeClassifier faceDetector = new CascadeClassifier("/pubic/lib/opencv-3.1.0/data/haarcascades/haarcascade_frontalface_alt.xml");
		VideoCapture capture = new VideoCapture(0);
		Mat webcamImg = new Mat();
		
		if(capture.isOpened()) {
			Thread.sleep(1000);
			while (true) {
				capture.read(webcamImg);
				
				if(!webcamImg.empty()) {
					MatOfRect faceArea = new MatOfRect();
					faceDetector.detectMultiScale(webcamImg, faceArea);
					
					//Draw a rectangle area the face
					for (Rect rect : faceArea.toArray()) {
						Core.rectangle(webcamImg, new Point(rect.x, rect.y), new Point(rect.x + rect.width, rect.y + rect.height), new Scalar(255,0,0,255));
					}
				}
				else {
					logger.info("Found webcam: " + capture.toString());
					break;
				}
			}
		}
		capture.release();
	}
	
}
