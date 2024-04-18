/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javacharts;
import java.io.*;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author santi
 */
public abstract class InputHandler {
    float[][] data = new float[2][];
    String title, xLabel, yLabel;
    File file;
    String filePath;
    MainFrame mainFrame;
    
    public File openFile(){
        JFileChooser chooser = new JFileChooser();
        int retVal = chooser.showOpenDialog(mainFrame);
        if(retVal == JFileChooser.APPROVE_OPTION){
            this.filePath = chooser.getSelectedFile().getAbsolutePath();
            return chooser.getSelectedFile();
        } else{
            return null;
        }
    }
    
    public boolean isValidFile(final String EXTENSION){
        return filePath.endsWith(EXTENSION) ? true : false;
    }
    
    public void setMainFrame(MainFrame mainFrame){
        this.mainFrame = mainFrame;
    }
    
    public MainFrame getMainFrame(){
        return this.mainFrame;
    }
    
    public void getLabelsFromUser(){
        title = JOptionPane.showInputDialog(mainFrame, "Enter a title for your Chart");
        xLabel = JOptionPane.showInputDialog(mainFrame, "Enter the label for your X Axis");
        yLabel = JOptionPane.showInputDialog(mainFrame, "Enter the label for your Y Axis");
    }
    
    public String[] getLabels(){
        return new String[] {title, xLabel, yLabel};
    }
    
    public void convertDataToCumulative(){
        float[][] newData = new float[data.length][data[0].length];
        
        for(int i = 0; i < data[0].length; ++i){
            if(i == 0){
                newData[1][i] = 0;
            }else{
                newData[1][i] = newData[1][i - 1];
            }
            
            newData[1][i] += data[1][i];
            newData[0][i] = data[0][i];
        }
        
        data = newData;
    }
    
    abstract public void parseData(int inputType) throws FileNotFoundException;
}
