package Robotronix.vision.ui;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.DataBuffer;
import java.awt.image.DataBufferByte;
import java.awt.image.Raster;
import java.awt.image.RasterFormatException;
import java.util.Observer;

import org.opencv.core.*;

import javax.swing.*;

public class matCanvas extends JComponent {
	BufferedImage m_Input;

	public matCanvas(Mat img){
		super();
		m_Input = matToBufferedImage(img); //Transformer l'image OpenCV img en BufferedImage et le stocker
		// dans m_Input
		this.setSize(img.cols(), img.rows());
	}
	@Override
	public void paintComponent(Graphics g){
		g.drawImage(m_Input, 0, 0, null);
	}
	public BufferedImage matToBufferedImage(Mat img){
		BufferedImage returnImage;

		int type = BufferedImage.TYPE_BYTE_GRAY; //mettre en niveaux de gris par defaut
		if (img.channels() > 1){ // si il y a plus d'un canal dans l'image, passer en couleur BGR
			type = BufferedImage.TYPE_3BYTE_BGR;
		}
		
		int sizeBuffer = img.channels() * img.cols() * img.rows(); //avoir le nombre de bytes dans notre image
		byte[] b = new byte[sizeBuffer]; // creer un array de bytes de taille 
		img.get(0, 0, b);
		returnImage = new BufferedImage(img.cols(), img.rows(), type);
		returnImage.setData(Raster.createRaster(returnImage.getSampleModel(),
				new DataBufferByte(b, b.length), new java.awt.Point(0, 0) ) );
		return returnImage;
	}
	public void updateImage(Mat img){
		m_Input = matToBufferedImage(img);
	}
}
