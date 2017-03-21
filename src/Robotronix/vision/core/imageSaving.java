package Robotronix.vision.core;

import org.opencv.core.*;
import org.opencv.imgcodecs.*;

public class imageSaving {
	public int m_fileNumber;
	
	public Imgcodecs m_OpenCV;
	
	public imageSaving() {
		m_OpenCV = new Imgcodecs();
		
		m_fileNumber = 0;
	}
	
	public boolean SaveImage(Mat scrImage) {
		
		boolean returnValue = Imgcodecs.imwrite("./images/imaging" + m_fileNumber + ".png", scrImage);
		m_fileNumber ++;
		return returnValue;
	}
	
}
