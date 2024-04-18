/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javacharts;
import javax.swing.JOptionPane;

/**
 *
 * @author santi
 */
public class ManualInputHandler extends InputHandler{
    
    public ManualInputHandler(MainFrame mainFrame){
        this.mainFrame = mainFrame;
        getLabelsFromUser();
    }
    
    @Override
    public void parseData(int chartType){
        if(chartType != 1){
            int size = getInt("How many datapoints would you like to enter?");
            data = new float[2][size];

            for(int i = 0; i < size; ++i){
                data[0][i] = getFloat("Enter x" + (1 + i));
                data[1][i] = getFloat("Enter y" + (1 + i));
            }
        }else{
            int size = getInt("How many plots would you like to create?");
            data = new float[size][];
            
            for(int i = 0; i < size; ++i){
                int size2 = getInt("How many datapoints would you like to enter for plot " + (1 + i));
                data[i] = new float[size2];
                for(int j = 0; j < size2; ++j){
                    data[i][j] = getFloat("Enter datapoint " + (1 + j) + " for plot " + (1 + i));
                }
            }

        }
    }
    
    public int getInt(String display){
        int input = 0;
        for(int i = 0; i < 1; ++i){
            try{
                input = Integer.parseInt(JOptionPane.showInputDialog(mainFrame, display));
            }catch(Exception e){
                JOptionPane.showMessageDialog(mainFrame, "Enter a decimal number", "Invalid Input", JOptionPane.ERROR_MESSAGE);
                --i;
            }
        }
        return input;
    }
    
    public float getFloat(String display){
        float input = 0;
        for(int i = 0; i < 1; ++i){
            try{
                input = Float.parseFloat(JOptionPane.showInputDialog(mainFrame, display));
            }catch(Exception e){
                JOptionPane.showMessageDialog(mainFrame, "Enter a decimal number", "Invalid Input", JOptionPane.ERROR_MESSAGE);
                --i;
            }
        }
        return input;
    }
}
