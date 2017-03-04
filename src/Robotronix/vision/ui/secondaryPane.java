package Robotronix.vision.ui;

import javax.swing.*;
import java.awt.*;
import org.opencv.core.*;

public class secondaryPane {

    public JPanel panel1;
    private JSlider hue1Slider;
    private JSlider saturation1Slider;
    private JSlider value1Slider;
    private JSlider hue2Slider;
    private JSlider saturation2Slider;
    private JSlider value2Slider;
    private JLabel hueFrom;
    private JLabel saturationFrom;
    private JLabel valueFrom;
    private JLabel hueTo;
    private JLabel saturationTo;
    private JLabel valueTo;
    private JLabel angleValue;
    private JLabel distanceValue;
    private JSlider expostionSlider;
    private JCheckBox activerControleCheckBox;
    private JRadioButton mode1RadioButton;
    private JRadioButton mode2RadioButton;

    public Scalar getFromSliderValues() {
        return new Scalar(hue1Slider.getValue(), saturation1Slider.getValue(),
                value1Slider.getValue());
    }

    public Scalar getToSliderValues() {
        return new Scalar(hue2Slider.getValue(), saturation2Slider.getValue(),
                value2Slider.getValue());
    }
    
    public void setFromSliderValues(Scalar minValues) {
    	hue1Slider.setValue((int)(minValues.val[0]));
    	saturation1Slider.setValue((int)(minValues.val[1]));
    	value1Slider.setValue((int)(minValues.val[2]));
    }
    
    public void setToSliderValues(Scalar maxValues) {
    	hue2Slider.setValue((int)(maxValues.val[0]));
    	saturation2Slider.setValue((int)(maxValues.val[1]));
    	value2Slider.setValue((int)(maxValues.val[2]));
    }

    public boolean isControlActivated() {
        return activerControleCheckBox.isSelected();
    }

    public double getExpositionValue() {
        return expostionSlider.getValue();
    }

    public void setAngle(double i){
        angleValue.setText("Angle : " + i);
    }

    public int getMode() {
        if (mode1RadioButton.isSelected()) {
            return 0;
        }
        return 1;
    }

    private void createUIComponents() {
    }

    public secondaryPane() {
        $$$setupUI$$$();
    }


    public void $$$setupUI$$$() {
        panel1 = new JPanel();
        panel1.setLayout(new GridBagLayout());
        hue1Slider = new JSlider(0, 180);
        GridBagConstraints gbc;
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel1.add(hue1Slider, gbc);
        saturation1Slider = new JSlider(0, 255);
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel1.add(saturation1Slider, gbc);
        value1Slider = new JSlider(0, 255);
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel1.add(value1Slider, gbc);
        hue2Slider = new JSlider(0, 180);
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel1.add(hue2Slider, gbc);
        saturation2Slider = new JSlider(0, 255);
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 6;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel1.add(saturation2Slider, gbc);
        value2Slider = new JSlider(0, 255);
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 7;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel1.add(value2Slider, gbc);
        hueFrom = new JLabel();
        hueFrom.setText("H1");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.EAST;
        panel1.add(hueFrom, gbc);
        saturationFrom = new JLabel();
        saturationFrom.setText("S1");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.EAST;
        panel1.add(saturationFrom, gbc);
        valueFrom = new JLabel();
        valueFrom.setText("V1");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.anchor = GridBagConstraints.EAST;
        panel1.add(valueFrom, gbc);
        hueTo = new JLabel();
        hueTo.setText("H2");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.anchor = GridBagConstraints.EAST;
        panel1.add(hueTo, gbc);
        saturationTo = new JLabel();
        saturationTo.setText("S2");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.anchor = GridBagConstraints.EAST;
        panel1.add(saturationTo, gbc);
        expostionSlider = new JSlider();
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 8;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel1.add(expostionSlider, gbc);
        final JLabel label1 = new JLabel();
        label1.setText("Exposition");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 8;
        gbc.anchor = GridBagConstraints.WEST;
        panel1.add(label1, gbc);
        valueTo = new JLabel();
        valueTo.setText("V2");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 7;
        gbc.anchor = GridBagConstraints.EAST;
        panel1.add(valueTo, gbc);
        final JPanel spacer1 = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.VERTICAL;
        panel1.add(spacer1, gbc);
        activerControleCheckBox = new JCheckBox();
        activerControleCheckBox.setText("Activer contrôle");
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        panel1.add(activerControleCheckBox, gbc);
        final JPanel spacer2 = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 9;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.VERTICAL;
        panel1.add(spacer2, gbc);
        mode1RadioButton = new JRadioButton();
        mode1RadioButton.setText("Mode 1");
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 10;
        gbc.anchor = GridBagConstraints.WEST;
        panel1.add(mode1RadioButton, gbc);
        mode2RadioButton = new JRadioButton();
        mode2RadioButton.setText("Mode 2");
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 10;
        gbc.anchor = GridBagConstraints.WEST;
        panel1.add(mode2RadioButton, gbc);
        angleValue = new JLabel();
        angleValue.setText("Angle : ");
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 12;
        gbc.anchor = GridBagConstraints.PAGE_END;
        panel1.add(angleValue, gbc);
        ButtonGroup buttonGroup;
        buttonGroup = new ButtonGroup();
        buttonGroup.add(mode1RadioButton);
        buttonGroup.add(mode2RadioButton);
    }


    public JComponent $$$getRootComponent$$$() {
        return panel1;
    }
}
