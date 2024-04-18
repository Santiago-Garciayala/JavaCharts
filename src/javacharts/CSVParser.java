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
public class CSVParser extends InputHandler{
    
    final public String EXTENSION = ".csv";
    
    public CSVParser(MainFrame mainFrame) throws FileNotFoundException{
        this.mainFrame = mainFrame;
        getLabelsFromUser();
        try{
            JOptionPane.showMessageDialog(this.mainFrame, "Make sure your CSV file contains only numerical data (no titles/labels)",
                "Attention", 
                JOptionPane.WARNING_MESSAGE);
            file = openFile();
        } catch(Exception e){
            JOptionPane.showMessageDialog(this.mainFrame, "Failed to open file",
                "Error in CSVParser", 
                JOptionPane.ERROR_MESSAGE);
            file = null;
        }
        
        boolean validFile = false;
        try{
            validFile = isValidFile(EXTENSION);
        } catch(Exception e){
            validFile = false;
        }
        
        if(!validFile){
            JOptionPane.showMessageDialog(this.mainFrame, "Please select a CSV file",
                "Invalid file type", 
                JOptionPane.ERROR_MESSAGE);
            file = null;
        }
    }
    
    @Override
    public void parseData(int chartType) throws FileNotFoundException{
        if(chartType != 1){
            data = new float[2][];
        }else{
            int size = getRowCount();
            data = new float[size][];
        }
        BufferedReader fileReader = new BufferedReader(new FileReader(file));
        
        try{
            final String DELIMITER = ",";
            
            String line;
            int i = 0;
            while((line = fileReader.readLine()) != null){
                String[] lineData = line.split(DELIMITER);
                //printArrayToConsole(lineData);
                data[i] = convertToFloatArray(lineData);
                //printArrayToConsole(data[i]);
                ++i;
            }
        } catch(Exception e){
            JOptionPane.showMessageDialog(this.mainFrame, "Failed to read file",
                    "Error in CSVParser", 
                    JOptionPane.ERROR_MESSAGE);
            System.out.println(e);
        }
    }
    
    private int getRowCount()throws FileNotFoundException{
        BufferedReader fileReader = new BufferedReader(new FileReader(file));
        int count = 0;

        try{
            while(fileReader.readLine() != null){
                ++count;
            }
        }catch(IOException ex){
            JOptionPane.showMessageDialog(this.mainFrame, "Failed to read file row count",
                "Error in CSVParser", 
                JOptionPane.ERROR_MESSAGE);
        }
        
        return count;
    }
    
    public float[] convertToFloatArray(String[] arrIn){
        float[] arrOut = new float[arrIn.length];
        
        try{
            for(int i = 0; i < arrIn.length; ++i){
                arrOut[i] = Float.parseFloat(arrIn[i]);
            }
        } catch(Exception e){
            JOptionPane.showMessageDialog(this.mainFrame, "Failed to convert all values to floats",
                    "Error in CSVParser", 
                    JOptionPane.ERROR_MESSAGE);
            System.out.println(e);
            return null;
        }
        return arrOut;
    }
    
    public void printArrayToConsole(String[] arr){
        System.out.println("String array");
        for(int i = 0; i < arr.length; ++i){
            System.out.println(arr[i]);
        }
    }
    
    public static void printArrayToConsole(float[] arr){
        System.out.println("Float Array");
        for(int i = 0; i < arr.length; ++i){
            System.out.println(arr[i]);
        }
    }
}
