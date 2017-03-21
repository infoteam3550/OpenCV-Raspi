package Robotronix.vision.core;

import org.opencv.core.*;
import org.opencv.imgcodecs.*;

public class imageSaving {
	public int m_fileNumber;
	public int rBS; //runs before Saving
	
	public Imgcodecs m_OpenCV;
	
	public imageSaving() {
		m_OpenCV = new Imgcodecs();
		
		m_fileNumber = 0;
		rBS
	}
	
	public boolean SaveImage(Mat scrImage, int runsBeforeSaving) {
		rBS++;
		if !(rBS > runsBeforeSaving) {
		boolean returnValue = Imgcodecs.imwrite("./images/imaging" + m_fileNumber + ".png", scrImage);
		m_fileNumber ++;
		}
		else {
			returnValue = false;
		}
		return returnValue;
	}
	
}
