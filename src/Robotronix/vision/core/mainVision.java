package Robotronix.vision.core;
import Robotronix.vision.ui.*;
import org.opencv.core.*;
import org.opencv.imgcodecs.*;

public class mainVision {

	public static boolean willRequestImage;
	
	
	public static void main(String[] args) {
		System.out.println(System.getProperty("java.library.path"));

		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
		
		preferencesAPI prefs = new preferencesAPI();
		
		String testPrefsAPI = prefs.m_prefs.get("TestName", "Error");
		System.out.println(testPrefsAPI);
		
		prefs.m_prefs.put("TestName","Working");
		
		centralModule cmodule = new centralModule();
		
		secondaryWindow altWindow = new secondaryWindow();
		Mat image = Imgcodecs.imread("/home/pi/image.JPG");
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
		
		cmodule.m_log.info("Initilisation complete");
		cmodule.m_log.info("---------------------------");
		
		testPrefsAPI = prefs.m_prefs.get("TestName", "Error");
		cmodule.m_log.info(testPrefsAPI);
		willRequestImage = false;
		while(true){
			if(window.isLive() | window.getUpdateStatus()) willRequestImage = true;
			window.update(image);
			cmodule.setHSV(altWindow.getFromSliders(), altWindow.getToSliders());
			cmodule.setContrast(altWindow.getExpoValue());
			cmodule.setTargetMode(cmodule.DEBUG_TARGET);
			if(willRequestImage)
				cmodule.runTarget(true);
			image = cmodule.getSrcImage();
			window.setFPSRate((int)(cmodule.getFPS()));
		}

	}

}
