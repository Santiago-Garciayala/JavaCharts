/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package javacharts;

import java.awt.EventQueue;

/**
 *
 * @author santi
 */
public class JavaCharts {
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) { 
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run(){
                new MainFrame();
            }
        });
        
    }
    
}
