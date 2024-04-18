package javacharts;

import org.jfree.chart.JFreeChart;
import org.jfree.chart.ChartFactory;
import org.jfree.data.xy.XYDataset;  
import org.jfree.data.xy.XYSeries;  
import org.jfree.data.xy.XYSeriesCollection;  



public class ScatterPlot extends ChartManager{

    

	public ScatterPlot(String[] labels, float[][] data) {
            
            super(labels, data);
            XYDataset dataset = createDataset();

            chart = ChartFactory.createScatterPlot(
                    labels[0], labels[1], labels[2],
                    dataset
            );

            setChartPanel(chart);
	}
        
        private XYDataset createDataset() {  
            XYSeriesCollection dataset = new XYSeriesCollection();  
            
            XYSeries series = new XYSeries("Series 1");
            
            for(int i = 0; i < data[0].length; ++i){
                series.add(data[0][i], data[1][i]);
            }
            
            dataset.addSeries(series);
            return dataset;
        }
}
