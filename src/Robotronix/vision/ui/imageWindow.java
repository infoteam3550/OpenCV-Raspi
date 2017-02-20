package Robotronix.vision.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.awt.image.WritableRaster;

import javax.swing.*;

import org.opencv.core.Mat;
 public class imageWindow {
	private static BufferedImage output;
	JFrame guiFrame;
	matCanvas pane;
	mainWindow window;
	//UI elements

	JLabel fpsLabel;
	JCheckBox liveUpdateCheckBox;
	JButton refreshButton;

	JCheckBox contourToggle;
	JCheckBox centerToggle;
	JCheckBox positionToggle;
	JCheckBox rotatedRectToggle;

	public static BufferedImage createBufferedImage(Mat mat) {
		BufferedImage image = new BufferedImage(mat.width(), mat.height(), BufferedImage.TYPE_3BYTE_BGR);
		WritableRaster raster = image.getRaster();
		DataBufferByte dataBuffer = (DataBufferByte) raster.getDataBuffer();
		byte[] data = dataBuffer.getData();
		mat.get(0, 0, data);
		return image;
	}

	public imageWindow(Mat image){
		guiFrame = new JFrame("OpenCV GUI");

		//Initialiser les composantes
		pane = new matCanvas(image);
		pane.updateImage(image);
		pane.setLayout(null);
		pane.setBounds(0, 30, 800, 630);
		pane.setSize(800, 600);

		fpsLabel = new JLabel("FPS: ");
		liveUpdateCheckBox = new JCheckBox("Live");
		refreshButton = new JButton("Refresh");

		contourToggle = new JCheckBox("Contours");
		centerToggle = new JCheckBox("Centre");
		positionToggle = new JCheckBox("Position");
		rotatedRectToggle = new JCheckBox("Rectangle");

		fpsLabel.setBounds(0, 0, 100, 30);
		fpsLabel.setSize(100, 30);
		liveUpdateCheckBox.setBounds(590, 0 , 710, 30);
		liveUpdateCheckBox.setSize(120, 30);
		refreshButton.setBounds(720, 0, 800, 30);
		refreshButton.setSize(80, 30);


		contourToggle.setBounds(0, 630, 180, 685);
		centerToggle.setBounds(200, 630, 380, 685);
		positionToggle.setBounds(400, 630, 580, 685);
		rotatedRectToggle.setBounds(600, 630, 780, 685);

		contourToggle.setSize(180, 50);
		centerToggle.setSize(180, 50);
		positionToggle.setSize(180, 50);
		rotatedRectToggle.setSize(180, 50);

		//Initialiser le JPanel et ajouter les composantes
		JPanel imagePane;
		imagePane = new JPanel();
		imagePane.setLayout(null);
		imagePane.add(pane);
		imagePane.add(fpsLabel);
		imagePane.add(liveUpdateCheckBox);
		imagePane.add(refreshButton);
		imagePane.add(contourToggle);
		imagePane.add(centerToggle);
		imagePane.add(positionToggle);
		imagePane.add(rotatedRectToggle);

		guiFrame.setContentPane(imagePane);
		guiFrame.pack();
		guiFrame.setSize(800, 685);
		guiFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		guiFrame.setVisible(true);

		refreshButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				//Run code to request refresh
			}
		});
	}
	//Getters et settters des elements de l'UI
	public void setFPSRate(int fpsRate){
		fpsLabel.setText("FPS: " + fpsRate);
	};
	public boolean isContourRequested(){
		return contourToggle.isSelected();
	}
	public boolean isRotatedRectRequested(){
		return rotatedRectToggle.isSelected();
	}
	public boolean isPositionRequested(){
		return positionToggle.isSelected();
	}
	public boolean isLive(){
		return liveUpdateCheckBox.isSelected();
	}

	//Fonction d'update d'imagePane
	public void update(Mat image){
		pane.updateImage(image);
		pane.setSize(image.cols(), image.rows());
		guiFrame.repaint();
	}
}