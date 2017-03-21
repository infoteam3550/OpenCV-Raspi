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
		rBS = 0;
	}
	
	/**
	 * SaveImage will save a given image after getting run multiple times. Must be in a loop!
	 * @param scrImage the source image (mat)
	 * @param runsBeforeSaving time the function runs and do nothing before it decide to save (int)
	 * @return return true if there are no errors (boolean)
	 */
	public boolean SaveImage(Mat scrImage, int runsBeforeSaving) {
		rBS++;
		boolean returnValue;
		if (rBS <= runsBeforeSaving) {
		returnValue = Imgcodecs.imwrite("./images/imaging" + m_fileNumber + ".png", scrImage);
		m_fileNumber ++;
		}
		else {
			returnValue = false;
		}
		return returnValue;
	}
	
}