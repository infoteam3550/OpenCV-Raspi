package Robotronix.vision.ui;

import javax.swing.*;
import org.opencv.core.*;

/**
 * Created by sysgen on 02/02/17.
 */
public class secondaryWindow extends JFrame {
    secondaryPane m_Pane;
    public secondaryWindow(){
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setBounds(100, 100, 300, 500);
        m_Pane = new secondaryPane();
        this.setContentPane(m_Pane.panel1);
        m_Pane.panel1.setVisible(true);
        this.setVisible(true);
    }
    public Scalar getFromSliders(){
        return m_Pane.getFromSliderValues();
    }
    public Scalar getToSliders(){
        return m_Pane.getToSliderValues();
    }
    public boolean isActivated(){
        return m_Pane.isControlActivated();
    }
    public double getExpoValue(){
        return m_Pane.getExpositionValue();
    }
    public int getMode(){
        return m_Pane.getMode();
    }
}
