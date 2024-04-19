/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javacharts;
import java.io.*;
import javax.swing.JOptionPane;

/**
 *
 * @author santi
 */
public class ChartBuilder {
    int chartType;
    int inputType;
    File fileIn;
    InputHandler inputHandler;
    ChartManager chartManager;
    //required for InputHandler
    MainFrame mainFrame;
    
    public ChartBuilder(int chartType, int inputType, MainFrame mainFrame)throws FileNotFoundException{
        //System.out.println(inputType);
        
        this.mainFrame = mainFrame;
        
        switch(inputType){
            case 0:
                inputHandler = new ExcelParser(this.mainFrame);
                if(inputHandler.file == null){
                    JOptionPane.showMessageDialog(this.mainFrame, "File is null",
                    "Error in ChartBuilder", 
                    JOptionPane.ERROR_MESSAGE);
                    break;
                }
                
                inputHandler.parseData(chartType);
                if(inputHandler.data == null){
                    JOptionPane.showMessageDialog(this.mainFrame, "ExcelParser.parseData() failed to return valid array",
                    "Error in ExcelParser", 
                    JOptionPane.ERROR_MESSAGE);
                    break;
                }
                if(inputHandler.data[0] == null){
                    JOptionPane.showMessageDialog(this.mainFrame, "ExcelParser.convertToFloarArray() failed to return valid array",
                    "Error in ExcelParser", 
                    JOptionPane.ERROR_MESSAGE);
                    break;
                }
                break;
            case 1:
                inputHandler = new CSVParser(this.mainFrame);
                if(inputHandler.file == null){
                    JOptionPane.showMessageDialog(this.mainFrame, "File is null",
                    "Error in ChartBuilder", 
                    JOptionPane.ERROR_MESSAGE);
                    break;
                }
                
                inputHandler.parseData(chartType);
                if(inputHandler.data == null){
                    JOptionPane.showMessageDialog(this.mainFrame, "CSVParser.parseData() failed to return valid array",
                    "Error in CSVParser", 
                    JOptionPane.ERROR_MESSAGE);
                    break;
                }
                if(inputHandler.data[0] == null){
                    JOptionPane.showMessageDialog(this.mainFrame, "CSVParser.convertToFloarArray() failed to return valid array",
                    "Error in CSVParser", 
                    JOptionPane.ERROR_MESSAGE);
                    break;
                }
                break;
            case 2:
                inputHandler = new ManualInputHandler(this.mainFrame);
                inputHandler.parseData(chartType);
                break;
        }
        
        switch(chartType){
            case 0:
                chartManager = new ScatterPlot(inputHandler.getLabels(), inputHandler.data);
                break;
            case 1:
                chartManager = new BoxPlot(inputHandler.getLabels(), inputHandler.data);
                break;
            case 2:
                boolean cumulative = confirmDialogAsBool("Is your data already cumulative?");
                if(!cumulative){
                    inputHandler.convertDataToCumulative();
                }
                chartManager = new LineChart(inputHandler.getLabels(), inputHandler.data);
                break;
        }
    }
    
    public void setMainFrame(MainFrame mainFrame){
        this.mainFrame = mainFrame;
    }
    
    public MainFrame getMainFrame(){
        return this.mainFrame;
    }
    
    public boolean confirmDialogAsBool(String display){
        boolean choiceBool = true;
        for(int i = 0; i < 1; i++){
                int choice = JOptionPane.showConfirmDialog(mainFrame, display, "Choose Yes or No", JOptionPane.YES_NO_OPTION);

            switch (choice) {
                case 0:
                    choiceBool = true;
                    break;
                case 1:
                    choiceBool = false;
                    break;
                default:
                    JOptionPane.showMessageDialog(mainFrame, "Enter yes or no", "Invalid input", JOptionPane.ERROR_MESSAGE);
                    --i;
                    break;
            }
        }
        return choiceBool;
    }
}
