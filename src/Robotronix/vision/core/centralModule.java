package Robotronix.vision.core;

import java.io.IOException;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import org.opencv.core.*;
import org.opencv.imgcodecs.*;
import org.opencv.imgproc.*;
import org.opencv.videoio.VideoCapture;
import org.opencv.videoio.Videoio;

public class centralModule {
	//setup constance
	public static final int DEBUG_TARGET = 0;
	public static final int CROCHET_GEAR = 1;
	public static final int HIGH_GOAL = 2;
	public static final int GEAR_TERRE = 3;
	public static final int BALLE_TERRE = 4;
	// setup member variables
	public int m_degree;
	public int m_targetMode;
	public boolean m_targetStop;

	Scalar m_minHSV;
	Scalar m_maxHSV;

	public int m_exposition;
	public int m_contraste;

	public int m_tailleMin;

	public Logger m_logger;
	public FileHandler m_fh;

	VideoCapture m_camera;
	double m_time;
	Mat m_srcImage;
	Mat m_hsvImage;
	Mat m_hsvOverlay;
	double m_timeSinceLastUpdate;
	//start of functions

	public centralModule() {
		//Code initialisation

		m_degree = 0;
		m_targetMode = 0;
		m_targetStop = false;

		m_minHSV = new Scalar(0, 0, 0);
		m_maxHSV = new Scalar(255, 255, 255);

		m_exposition = 0;
		m_contraste = 0;

		m_tailleMin = 0;

		createLog();

		//OpenCV initialisation
		m_camera = new VideoCapture(0);
		m_camera.open(0);
		if(!m_camera.isOpened()){
			Log("Camera Error",2);
		}
		else{
			Log("Camera OK!",1);
		}
		m_camera.set(Videoio.CAP_PROP_FRAME_COUNT, 30);

		m_srcImage = new Mat();
		m_camera.read(m_srcImage);

		m_hsvImage = Mat.zeros(m_srcImage.size(), 0);

		m_hsvOverlay = new Mat(3,3,0);

		//m_camera.read(m_hsvOverlay);

		m_time = System.nanoTime();
	}

	public void changeTargetMode(int target) {
		m_targetMode = target;
	}

	public int getTargetMode() {
		return m_targetMode;
	}

	public void runTarget(boolean liveMode)
	{


		if (liveMode) m_targetStop = true;
		do {
			switch (m_targetMode) {
				case (DEBUG_TARGET) : {
					//TODO	Montre l'image et mets le masque HSV 
					targetDebug();
					break;
				}
				case (CROCHET_GEAR) : {
					//TODO trouver le centre de deux blob vertical
					crochetGear();
					break;
				}
				case (HIGH_GOAL): {
					//TODO trouver le centre de deux blob horizontal
					highGoal();
					break;
				}
				case (GEAR_TERRE): {
					//TODO trouver le centre d'un blob par terre
					gearTerre();
					break;
				}
				case (BALLE_TERRE): {
					//TODO trouver le centre d'un blob plus petit
					balleTerre();
					break;
				}
				default : {
					//TODO Send log about this error
					Log("[runTarget] invalid target mode, change with ''changeTargetMode''",2);
				}
			}
		} while(m_targetStop);
		m_targetStop = false;
	}

	public void stopTarget () {
		m_targetStop = false;
	}

	public int getTargetResult() {
		return m_degree;
	}

	public void setHSV (int minH,int minS,int minV,int maxH,int maxS,int maxV) { // for masking
		m_minHSV = new Scalar(minH, minS, minV);

		m_minHSV = new Scalar(maxH, maxS, maxV);
	}
	public void setHSV (Scalar min, Scalar max) {
		m_minHSV = min;
		m_maxHSV = max;
	}

	public Scalar getFromHSV(){
		return m_minHSV;
	}
	public Scalar getToHSV(){
		return m_maxHSV;
	}

	public void setCamParam (int exposition, int contraste) { // for changing the image found
		m_exposition = exposition;
		m_contraste = contraste;
	}

	public int[] getCamParam() {
		int[] camParam = {m_exposition,m_contraste};
		return camParam;
	}

	public void setContourParam (int tailleMin) { // for blob finding
		m_tailleMin = tailleMin;
	}

	public int[] getContourParam() {
		int[] contourParam = {m_tailleMin};
		return contourParam;
	}


	//TargetCodes?
	public void targetDebug()
	{
		Log("[runTarget] targetDebug started",1);

		m_time = System.nanoTime() / 1000000;
		m_camera.read(m_srcImage);

		//Imgproc.blur(srcImage, srcImage, new Size(3, 3));
		
		/*
		 *  Convertis une image de BGR de la camera a HSV
		 *  
		 *  Raison: HSV permet de choisir une certaine couleur de cible
		 */
		Imgproc.cvtColor(m_srcImage, m_hsvImage, Imgproc.COLOR_BGR2HSV);
		
		/*
		 * Permet de faire le choix de l'intervale de couleur
		 * 
		 * Param1 = source, Param2 = 3 valeur minimum HSV, Param3 = 3 valeur maximum HSV, Param 4 = destination
		 *
		 */
		Core.inRange(m_hsvImage, m_minHSV , m_maxHSV, m_hsvOverlay); // Valeur pour le tape

		//Core.multiply(m_hsvOverlay, new Scalar(0.75, 0.75, 0.75), m_hsvOverlay);
		//Core.multiply(m_hsvOverlay, new Scalar(0.3, 1, 1), m_hsvOverlay);

		//Nous allons utiliser le maintenant inutile hsvImage comme Mat de swap...
		Imgproc.cvtColor(m_hsvOverlay, m_hsvImage, Imgproc.COLOR_GRAY2BGR);
		
		
		/*
		 * findContour va trouver les contours des objets de l'image
		 * 
		 */
		List<MatOfPoint> contours = new ArrayList<MatOfPoint>();
		Imgproc.findContours(m_hsvOverlay, contours, new Mat(), Imgproc.RETR_LIST, Imgproc.CHAIN_APPROX_SIMPLE);

		//Core.multiply(srcImage, new Scalar(0,0,0), srcImage);

		//Appliquer le masque...

		//Imgproc.cvtColor(m_hsvOverlay, m_hsvOverlay, Imgproc.COLOR_GRAY2BGR);
		//Core.bitwise_and(srcImage, m_hsvOverlay, srcImage);

		List<MatOfInt> convexhulls = new ArrayList<MatOfInt>(contours.size());
		List<Double> orientations = new ArrayList<Double>();
//		//Dessiner les rectangles
		for (int i = 0; i < contours.size(); i++)
		{
//			//Trier les contours qui ont une bounding box
			convexhulls.add(i, new MatOfInt(6));
			if (Imgproc.contourArea(contours.get(i)) > 2000)
			{
				Imgproc.convexHull(contours.get(i), convexhulls.get(i));
				double contourSolidity = Imgproc.contourArea(contours.get(i))/Imgproc.contourArea(convexhulls.get(i));
				Imgproc.drawContours(m_srcImage, contours, i, new Scalar(255, 255, 255), -1);
				MatOfPoint2f points = new MatOfPoint2f(contours.get(i).toArray());
				RotatedRect rRect = Imgproc.minAreaRect(points);

				Point[] vertices = new Point[4];
				rRect.points(vertices);
				for (int j = 0; j < 4; j++) //Dessiner un rectangle avec rotation..
				{
					Imgproc.line(m_srcImage, vertices[j], vertices[(j+1)%4], new Scalar(0,255,0), 10);
				}
				orientations.add(3.0);
				System.out.println(contourSolidity);
			}
		}
		//this is a test code
		m_degree = 10;
	}

	public void crochetGear() {
		Log("[runTarget] crochetGear started",1);
		//this is a test code
		m_degree = 20;
	}

	public void highGoal() {
		Log("[runTarget] highGoal started",1);
		//this is a test code
		m_degree = 30;
	}

	public void gearTerre() {
		Log("[runTarget] gearTerre started",1);
		//this is a test code
		m_degree = 40;
	}

	public void balleTerre() {
		Log("[runTarget] balleTerre started",1);
		//this is a test code
		m_degree = 50;
	}


	///		[Category] Logging
	public void createLog() { //utiliser lors de l'initialisation
		m_logger = Logger.getLogger("MyLog");
		try {
			// This block configure the logger with handler and formatter  
			//fh = new FileHandler("$HOME/JavaCodeTest/Log.log");  
			m_fh = new FileHandler("./log.log");
			m_logger.addHandler(m_fh);
			SimpleFormatter formatter = new SimpleFormatter();
			m_fh.setFormatter(formatter);
			m_logger.setLevel(Level.FINE);
		}catch (IOException e) {
			e.printStackTrace();
		}
		Log("Log created succefully", 1);
	}

	/**
	 * <p>A log creator using java.util.logging</p>
	 * @param toLog <B>:</B> the text to log
	 * @param Level <B>:</B> what level you want: <p><b>0</b> for fine <p><b>1</b> for info (pups-up in console)</p><p><B>2</B> for warning</p><p><b>3</b> for severe/crashing</p>
	 */
	public void Log(String toLog, int Level ) {


		try {

			// the following statement is used to log any messages  
			switch (Level) {
				case (0): {
					m_logger.fine(toLog);
					break;
				}
				case (1) : {
					m_logger.info(toLog);
					break;
				}
				case (2) : {
					m_logger.warning(toLog);
					break;
				}
				case (3) : {
					m_logger.severe(toLog);
					break;
				}
			}

		} catch (SecurityException e) {
			e.printStackTrace();
		}

	}


}