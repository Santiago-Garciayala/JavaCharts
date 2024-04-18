/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javacharts;

import org.jfree.chart.ChartPanel;
import javax.swing.JPanel;
import org.jfree.chart.plot.FastScatterPlot;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.JFreeChart;


public class Chart {
    
    float[][] data;
    ChartPanel panel;
    
    public Chart(){
        initChartPanel();
    }
    
    private void initChartPanel(){
        float[][] tempData = {{0}, {0}};
        FastScatterPlot plot = new FastScatterPlot(tempData, new NumberAxis(""), new NumberAxis(""));
        JFreeChart chart = new JFreeChart("If you're seeing this, something went wrong.", plot);
        panel = new ChartPanel(chart);
    }
    
}
