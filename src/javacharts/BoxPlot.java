package javacharts;

import java.util.ArrayList;
import java.util.List;

import org.jfree.chart.JFreeChart;
import org.jfree.data.statistics.DefaultBoxAndWhiskerCategoryDataset;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.renderer.category.BoxAndWhiskerRenderer;
import org.jfree.chart.plot.CategoryPlot;

public class BoxPlot extends ChartManager{

	public BoxPlot(String[] labels, float[][] data) {
            super(labels, data);
            System.out.println("invoked BoxPLot constructor");
            
            DefaultBoxAndWhiskerCategoryDataset dataset = createDataset();
            CategoryAxis xAxis = new CategoryAxis(labels[1]);
            NumberAxis yAxis = new NumberAxis(labels[2]);
            yAxis.setAutoRangeIncludesZero(false);
            
            BoxAndWhiskerRenderer renderer = new BoxAndWhiskerRenderer();
            renderer.setMaximumBarWidth(0.1);
            renderer.setWhiskerWidth(1);
            
            CategoryPlot plot = new CategoryPlot(dataset, xAxis, yAxis, renderer);
            chart = new JFreeChart(labels[0], plot);
            
            setChartPanel(chart);
	}
        
        private DefaultBoxAndWhiskerCategoryDataset createDataset(){
            DefaultBoxAndWhiskerCategoryDataset dataset = new DefaultBoxAndWhiskerCategoryDataset();
            for(int i = 0; i < data.length; ++i){
                List<Float> list = new ArrayList<>();
                for(int j = 0; j < data[i].length; ++j){
                    list.add(data[i][j]);
                }
                dataset.add(list, "Series", "Type " + i);
            }
            
            return dataset;
        }

}