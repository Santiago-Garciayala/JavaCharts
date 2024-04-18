package javacharts;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.geom.Ellipse2D;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartUtils;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

public class LineChart extends ChartManager{
    
	public LineChart(String labels[], float[][] data) {
            
            super(labels, data);
            
            CategoryDataset dataset = createDataset();
            chart = createChart(dataset);

            setChartPanel(chart);
	}
	
	private CategoryDataset createDataset() {
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		
                for(int i = 0; i < data[0].length; ++i){
                    dataset.addValue((Number)data[1][i], labels[2], data[0][i]);
                }
                
		return dataset;
	}
	
	private JFreeChart createChart(CategoryDataset dataset) {
		
		JFreeChart chart = ChartFactory.createLineChart(
			labels[0],
			labels[1],
			labels[2],
			dataset);
		chart.removeLegend();
		
		CategoryPlot plot = (CategoryPlot) chart.getPlot();
		plot.setRangePannable(true);
		plot.setRangeGridlinesVisible(false);
		
		NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
		rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
		
		ChartUtils.applyCurrentTheme(chart);
		
		LineAndShapeRenderer renderer = (LineAndShapeRenderer) plot.getRenderer();
		renderer.setDefaultShapesVisible(true);
		renderer.setDrawOutlines(true);
		renderer.setUseFillPaint(true);
		renderer.setDefaultFillPaint(Color.WHITE);
		renderer.setSeriesStroke(0, new BasicStroke(3.0f));
		renderer.setSeriesOutlineStroke(0, new BasicStroke(2.0f));
		renderer.setSeriesShape(0, new Ellipse2D.Double(-5.0, -5.0, 10.0, 10.0));
		return chart;
	}
}
