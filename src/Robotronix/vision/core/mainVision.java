package Robotronix.vision.core;
import Robotronix.vision.ui.*;
import org.opencv.core.*;
import org.opencv.imgcodecs.*;

public class mainVision {

	public static boolean willRequestImage;
	
	
	public static void main(String[] args) 
	{
		System.out.println(System.getProperty("java.library.path"));

		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
		
		preferencesAPI prefs = new preferencesAPI();
		
		///Prefs key initisation
		int minH = Integer.parseInt(prefs.initKey("minH", "80"));
		int minS = Integer.parseInt(prefs.initKey("minS", "100"));
		int minV = Integer.parseInt(prefs.initKey("minV", "100"));
		int maxH = Integer.parseInt(prefs.initKey("maxH", "90"));
		int maxS = Integer.parseInt(prefs.initKey("maxS", "255"));
		int maxV = Integer.parseInt(prefs.initKey("maxV", "255"));
		int targetMode = Integer.parseInt(prefs.initKey("targetMode", "0"));
		double CAMexposition = Double.parseDouble(prefs.initKey("exposition", "20"));
		double CAMcontraste = Double.parseDouble(prefs.initKey("contrast", "20"));
		int contourTailleMin = Integer.parseInt(prefs.initKey("contourTailleMin", "0"));
		//End of section
		
		
		centralModule cmodule = new centralModule();
		
		cmodule.setHSV(minH, minS, minV, maxH, maxS, maxV); //set value from prefs
		
		secondaryWindow altWindow = new secondaryWindow();
		Mat image = new Mat();
		//image = Imgcodecs.imread("/home/pi/image.JPG");
		cmodule.m_camera.read(image);
		if(image.height() == 0){
			System.out.println("Image load fail");
		}
		else {
			cmodule.m_log.info("Image load sucess");
			cmodule.m_log.info("X = "+image.cols());
			cmodule.m_log.info("Y = "+image.rows());
			cmodule.m_log.info("Channels: " + image.channels());
		}
		imageWindow window = new imageWindow(image);
		window.setFPSRate(0);
		
		//set values from prefs
		altWindow.setFromSliders(cmodule.getMinHSV());
		altWindow.setToSliders(cmodule.getMaxHSV());
		cmodule.setTargetMode(targetMode);
		cmodule.setCamParam(CAMcontraste,CAMexposition);
		cmodule.setContourParam(contourTailleMin);
		
		cmodule.m_log.info("Initilisation complete");
		cmodule.m_log.info("---------------------------");
		

		
		willRequestImage = false;
		while(true)
		{
			if(window.isLive() | window.getUpdateStatus()) willRequestImage = true;
			
			cmodule.setHSV(altWindow.getFromSliders(), altWindow.getToSliders());
			cmodule.setContrast(altWindow.getExpoValue());
			cmodule.setTargetMode(cmodule.TARGET_CROCHET);
			
			if(willRequestImage)
				cmodule.runTarget(true);
			
			image = cmodule.getSrcImage();
			window.update(image);
			altWindow.setAngle(cmodule.m_targetAngle);
			window.setFPSRate((int)(cmodule.getFPS()));
		}

	}
	
	
}
