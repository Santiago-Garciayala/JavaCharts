/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javacharts;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.plot.FastScatterPlot;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.JFreeChart;


public class ChartManager{
    
    float[][] data;
    String[] labels;
    ChartPanel panel;
    JFreeChart chart;
    
    
    public ChartManager(){
        initChartPanel();
    }
    
    public ChartManager(String[] labels, float[][] data){
        this.labels = labels;
        this.data = data;
        initChartPanel();
    }
    
    private void initChartPanel(){
        float[][] tempData = {{0}, {0}};
        FastScatterPlot plot = new FastScatterPlot(tempData, new NumberAxis(""), new NumberAxis(""));
        chart = new JFreeChart("If you're seeing this, something went wrong.", plot);
        panel = new ChartPanel(chart);
    }
    
    protected void setChartPanel(JFreeChart chart){
        panel = new ChartPanel(chart);
        panel.setPreferredSize(new java.awt.Dimension(1024, 360));
        panel.setMinimumDrawHeight(10);
        panel.setMaximumDrawHeight(2000);
        panel.setMinimumDrawWidth(20);
        panel.setMaximumDrawWidth(2000);
    }
    

    
}
