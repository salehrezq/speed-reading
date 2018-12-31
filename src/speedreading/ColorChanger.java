/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package speedreading;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.JFileChooser;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 *
 * @author S
 */
public class ColorChanger implements ActionListener, ChangeListener
{

    JColorChooser colorChoBackground;
    JColorChooser colorChoText;
    JDialog dialogBackground;
    JDialog dialogText;
    Color backgroundColor;
    Color textColor;

    public ColorChanger()
    {
        backgroundColor = new Color(255, 255, 255);
        textColor = new Color(0, 0, 0);
        SpeadReading.label.setBackground(backgroundColor);
        SpeadReading.label.setForeground(textColor);
        ////
        SpeadReading.backGroundColorItem.addActionListener(this);
        SpeadReading.backGroundColorItem.addChangeListener(this);
        //////////
        SpeadReading.textColorItem.addActionListener(this);
        SpeadReading.textColorItem.addChangeListener(this);

        colorChoBackground = new JColorChooser(SpeadReading.label.getBackground());
        colorChoText = new JColorChooser(SpeadReading.label.getForeground());

        dialogBackground = JColorChooser.createDialog(SpeadReading.backGroundColorItem,
                "Pick a Color",
                true, //modal
                colorChoBackground,
                this, //OK button handler
                null); //no CANCEL button handler

        dialogText = JColorChooser.createDialog(SpeadReading.textColorItem,
                "Pick a Color",
                true, //modal
                colorChoText,
                this, //OK button handler
                null); //no CANCEL button handler
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        Object source = e.getSource();
        if (source == SpeadReading.backGroundColorItem)
        {
            System.out.println("background color");
            dialogBackground.setVisible(true);
        }
        if (source == SpeadReading.textColorItem)
        {
            System.out.println("Text color");
            dialogText.setVisible(true);
        }
    }

    @Override
    public void stateChanged(ChangeEvent e)
    {
        Object source = e.getSource();
        if (source == SpeadReading.backGroundColorItem)
        {
            backgroundColor = colorChoBackground.getColor();
            SpeadReading.label.setBackground(backgroundColor);
        }
        if (source == SpeadReading.textColorItem)
        {
            textColor = colorChoText.getColor();
            SpeadReading.label.setForeground(textColor);
        }
    }
}
