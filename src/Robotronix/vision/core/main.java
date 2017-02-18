package Robotronix.vision.core;
import Robotronix.vision.ui.*;
import org.opencv.core.*;
import org.opencv.imgcodecs.*;

public class main {

	public static void main(String[] args) {
		System.out.println(System.getProperty("java.library.path"));

		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);

		// TODO Auto-generated method stub
		secondaryWindow altWindow = new secondaryWindow();
		Mat image = Imgcodecs.imread("/home/sysgen/image.png");
		if(image.height() == 0){
			System.out.println("Image load fail");
		}
		else {
			System.out.println("Image load sucess");
			System.out.println("X = "+image.cols());
			System.out.println("Y = "+image.rows());
			System.out.println("Channels: " + image.channels());
		}
		imageWindow window = new imageWindow(image);
		window.setFPSRate(0);
		centralModule cmodule = new centralModule();
		while(true){
			window.update(image);
			cmodule.setHSV(altWindow.getFromSliders(), altWindow.getToSliders());
		}

	}

}
