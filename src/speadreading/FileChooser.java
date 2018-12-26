/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package speadreading;

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

/**
 *
 * @author S
 */
public class FileChooser implements ActionListener
{

    SpeadReading speedReading;
    JFileChooser fileChooser;
    File file;
    FileReader fileReader;
    BufferedReader bufferedFileReader;

    public FileChooser()
    {
        speedReading.openFileItem.setToolTipText("Shortcut 'B'");
        speedReading.openFileItem.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0, false), "none");
        speedReading.openFileItem.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("B"), "Open File");
        speedReading.openFileItem.getActionMap().put("Open File", openFileAction);
        speedReading.openFileItem.addActionListener(this);
        fileChooser = new JFileChooser();
        /////
        TextFilter textFiler = new TextFilter();
        fileChooser.setFileFilter(textFiler);
    }
    
    AbstractAction openFileAction = new AbstractAction()
    {
        @Override
        public void actionPerformed(ActionEvent ae)
        {
            speedReading.openFileItem.doClick();
        }
    };

    @Override
    public void actionPerformed(ActionEvent e)
    {
        if (e.getSource() == speedReading.openFileItem)
        {
            int reternedValue = fileChooser.showOpenDialog(SpeadReading.frame);

            if (reternedValue == JFileChooser.APPROVE_OPTION)
            {
                try
                {
                    file = fileChooser.getSelectedFile();
                    fileReader = new FileReader(file);
                    bufferedFileReader = new BufferedReader(fileReader);

                    String currentLine = bufferedFileReader.readLine();
                    while (currentLine != null)
                    {
                        SpeadReading.textArea.append(currentLine);
                        SpeadReading.textArea.append("\n");
                        currentLine = bufferedFileReader.readLine();
                    }

                } catch (FileNotFoundException ex)
                {
                    System.out.println("File not found " + ex);
                } catch (IOException ex)
                {
                    Logger.getLogger(FileChooser.class.getName()).log(Level.SEVERE, null, ex);
                } finally
                {
                    try
                    {
                        bufferedFileReader.close();
                    } catch (IOException ex)
                    {
                        Logger.getLogger(FileChooser.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        }
    }
}
