/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javacharts;

import java.util.Iterator;
import java.util.ArrayList;
import java.util.List;
import java.io.*;
import javax.swing.JOptionPane;
import org.apache.poi.xssf.usermodel.XSSFSheet;  
import org.apache.poi.xssf.usermodel.XSSFWorkbook; 
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Cell;


/**
 *
 * @author santi
 */
public class ExcelParser extends InputHandler{
    
    final public String EXTENSION = ".xlsx";
    
    public ExcelParser(MainFrame mainFrame){
        this.mainFrame = mainFrame;
        getLabelsFromUser();
        try{
            JOptionPane.showMessageDialog(this.mainFrame, 
                "Make sure your Excel file contains only numerical data, that the data is placed horizontally and that the extension of the file is \".xlsx\"",
                "Attention", 
                JOptionPane.WARNING_MESSAGE);
            file = openFile();
        } catch(Exception e){
            JOptionPane.showMessageDialog(this.mainFrame, "Failed to open file",
                "Error in ExcelParser", 
                JOptionPane.ERROR_MESSAGE);
            file = null;
        }
        
        boolean validFile;
        try{
            validFile = isValidFile(EXTENSION);
        } catch(Exception e){
            validFile = false;
        }
        
        if(!validFile){
            JOptionPane.showMessageDialog(this.mainFrame, "Please select an Excel (.xlsx) file",
                "Invalid file type", 
                JOptionPane.ERROR_MESSAGE);
            file = null;
        }
    }
    
    @Override
    public void parseData(int chartType) throws FileNotFoundException{
        if(chartType != 1){
            data = new float[2][];
            System.out.println("first if");
        }else{
            int size = getRowCount();
            System.out.println(size);
            data = new float[size][];
        }
        
        FileInputStream fileInputStream = new FileInputStream(file);
        
        try{
            XSSFWorkbook workbook = new XSSFWorkbook(fileInputStream);
            XSSFSheet sheet = workbook.getSheetAt(0);
            
            Iterator<Row> rowIterator = sheet.iterator();
            int i = 0;
            while(rowIterator.hasNext()){
                Row row = rowIterator.next();
                Iterator<Cell> cellIterator = row.cellIterator();
                ArrayList<Float> temp = new ArrayList<>();
                while(cellIterator.hasNext()){
                    Cell cell = cellIterator.next();
                    temp.add((float)cell.getNumericCellValue());
                }
                data[i] = listToArray(temp);
                ++i;
            }
        } catch(Exception e){
            JOptionPane.showMessageDialog(this.mainFrame, "Failed to read file",
                    "Error in ExcelParser", 
                    JOptionPane.ERROR_MESSAGE);
            System.out.println(e);
        }
        
        
    }
    
    private int getRowCount()throws FileNotFoundException{
        FileInputStream fileInputStream = new FileInputStream(file);

        int count = 0;

        try{
            XSSFWorkbook workbook = new XSSFWorkbook(fileInputStream);
            XSSFSheet sheet = workbook.getSheetAt(0);
            
            Iterator<Row> rowIterator = sheet.iterator();
            while(rowIterator.hasNext()){
                rowIterator.next();
                ++count;
            }
        }catch(IOException ex){
            JOptionPane.showMessageDialog(this.mainFrame, "Failed to read file row count",
                "Error in ExcelParser", 
                JOptionPane.ERROR_MESSAGE);
        }
        
        return count;
    }
    
    public float[] listToArray(ArrayList<Float> list){
        float[] arr = new float[list.size()];
        for(int i = 0; i < list.size(); ++i){
            arr[i] = list.get(i);
        }
        
        return arr;
    }
}
