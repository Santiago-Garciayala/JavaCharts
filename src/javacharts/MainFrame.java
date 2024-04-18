/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javacharts;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.concurrent.atomic.*;
import java.io.*;
import org.jfree.chart.ChartUtils;
import java.net.URL;

/**
 *
 * @author santi
 */
public class MainFrame extends JFrame{
    GraphicsDevice device;
    JPanel mainPanel;
    JLabel title, choiceLabel;
    JButton[] buttons;
    JLabel[] buttonLabels;
    GridBagConstraints constraints;
    //These values need ot be atomic or []
    //because the compiler complains about it being referenced from a lambda as an int
    AtomicInteger graphType; 
    AtomicInteger inputType;
    
    public MainFrame(){
        initMainFrame();
        buildChartSelectionMenu();
    }
    
    private void initMainFrame(){
        device = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
        graphType = new AtomicInteger();
        inputType = new AtomicInteger();
        setLayout(new BorderLayout());
        mainPanel = new JPanel();
        mainPanel.setLayout(new GridBagLayout());
        constraints = new GridBagConstraints();
        
        //declare big labels
        title = new JLabel("Welcome to Java Charts", SwingConstants.CENTER);
        title.setFont(title.getFont().deriveFont(48.0f));
        
        choiceLabel = new JLabel("", SwingConstants.CENTER);
        choiceLabel.setFont(choiceLabel.getFont().deriveFont(30.0f));
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridwidth = 3;
        mainPanel.add(choiceLabel, constraints);
        
        //declare buttons, used for both graph type and input type
        buttons = new JButton[3];
        buttons[0] = new JButton();
        buttons[1] = new JButton();
        buttons[2] = new JButton();
        
        //declare button labels
        buttonLabels = new JLabel[3];
        buttonLabels[0] = new JLabel("Scatter Plot", SwingConstants.CENTER);
        buttonLabels[0].setFont(buttonLabels[0].getFont().deriveFont(18.0f));
        buttonLabels[1] = new JLabel("Box Plot", SwingConstants.CENTER);
        buttonLabels[1].setFont(buttonLabels[1].getFont().deriveFont(18.0f));
        buttonLabels[2] = new JLabel("Ogive", SwingConstants.CENTER);
        buttonLabels[2].setFont(buttonLabels[2].getFont().deriveFont(18.0f));
        
        
        //add buttons to mainPanel
        constraints.gridy = 1;
        constraints.gridwidth = 1;
        constraints.insets = new Insets(15, 10, 0, 10);
        mainPanel.add(buttons[0], constraints);
        
        constraints.gridx = 1;
        mainPanel.add(buttons[1], constraints);
        
        constraints.gridx = 2;
        mainPanel.add(buttons[2], constraints);
        
        //add button labels to main panel
        constraints.gridy = 2;
        constraints.gridx = 0;
        constraints.insets.top = 0;
        mainPanel.add(buttonLabels[0], constraints);
        
        constraints.gridx = 1;
        mainPanel.add(buttonLabels[1], constraints);
        
        constraints.gridx = 2;
        mainPanel.add(buttonLabels[2], constraints);
        
        add(title, BorderLayout.NORTH);
        add(mainPanel, BorderLayout.CENTER);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        //setUndecorated(true);
        //pack();
        setVisible(true);
        device.setFullScreenWindow(this);
    }
    
    private void buildChartSelectionMenu(){
        choiceLabel.setText("Which type of graph would you like to build?");
        
        setChartButtonImages();
        
        //add action listeners to buttons
        for(AtomicInteger i = new AtomicInteger(0); i.get() < buttons.length; i.incrementAndGet()){
            int chartT = i.get();
            buttons[i.get()].addActionListener((ActionEvent e) -> {
                graphType.set(chartT);
                buildInputSelectionMenu();
            });
        }
        
        //change button labels
        buttonLabels[0].setText("Scatter Plot");
        buttonLabels[1].setText("Box Plot");
        buttonLabels[2].setText("Ogive");
        
        validate();
        repaint();
    }
    
    private void buildInputSelectionMenu(){
        //remove all action listeners from buttons
        removeActionListeners();
        
        setInputButtonImages();
        
        //change labels
        choiceLabel.setText("How would you like to build this graph?");
        buttonLabels[0].setText("Excel file");
        buttonLabels[1].setText("CSV file");
        buttonLabels[2].setText("Input data manually");
        
        //add new action listeners to buttons
        for(AtomicInteger i = new AtomicInteger(0); i.get() < buttons.length; i.incrementAndGet()){
            int inputT = i.get();
            buttons[i.get()].addActionListener((ActionEvent e) -> {
                inputType.set(inputT);
                try{
                    ChartBuilder chartBuilder = new ChartBuilder(graphType.get(), inputType.get(), this);
                    getContentPane().removeAll();
                    
                    JPanel lastPanel = new JPanel();
                    lastPanel.setLayout(new BorderLayout());
                    
                    JPanel lastButtonsPanel = new JPanel();
                    lastButtonsPanel.setOpaque(false);
                    lastButtonsPanel.add(createSaveAsPNGButton(chartBuilder));
                    lastButtonsPanel.add(createMakeAnotherChartButton());
                    
                    lastPanel.add(lastButtonsPanel, BorderLayout.SOUTH);
                    lastPanel.add(chartBuilder.chartManager.panel, BorderLayout.CENTER);
                    
                    add(lastPanel);
                    validate();
                    repaint();
                } catch(Exception ex){
                    removeActionListeners();
                    buildChartSelectionMenu();
                    validate();
                    repaint();
                }
            });
        }
        validate();
        repaint();
    }
    
    public JButton createSaveAsPNGButton(ChartBuilder chartBuilder){
        JButton button = new JButton("Save chart as PNG");
        
        try{
            ImageIcon bigIcon = new ImageIcon(getClass().getResource("images/png.png"));
            Image resizedImage = bigIcon.getImage().getScaledInstance(20, 24, Image.SCALE_SMOOTH);
            ImageIcon resizedIcon = new ImageIcon(resizedImage);

            button.setIcon(resizedIcon);
        }catch(Exception ex){
            button.setFont(button.getFont().deriveFont(20.0f));
        }
        
        button.addActionListener((ActionEvent e) -> {
            try{
                String filename = JOptionPane.showInputDialog(this, "Enter a name for your chart image");
                JFileChooser chooser = new JFileChooser();
                chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                
                int retVal = chooser.showSaveDialog(this);
                if(retVal == JFileChooser.APPROVE_OPTION){
                    filename = chooser.getSelectedFile().getAbsolutePath() + "/" + filename;
                } else{
                    throw new IOException();
                }
                
                OutputStream output = new FileOutputStream(filename + ".png");
                ChartUtils.writeChartAsPNG(output, 
                        chartBuilder.chartManager.chart, 
                        chartBuilder.chartManager.panel.getWidth(),
                        chartBuilder.chartManager.panel.getHeight()
                        );
            }catch(IOException IOex){
                JOptionPane.showMessageDialog(this, "Could not save image",
                    "Error", 
                    JOptionPane.ERROR_MESSAGE);
                IOex.printStackTrace();
            }
        });
        
        return button;
    }
    
    public JButton createMakeAnotherChartButton(){
        JButton button = new JButton("Make another chart");
        
        try{
            ImageIcon chartIcon = new ImageIcon(getClass().getResource("images/chart.png"));
            button.setIcon(chartIcon);
        }catch(Exception ex){
            button.setFont(button.getFont().deriveFont(20.0f));
        }
        
        button.addActionListener((ActionEvent e) -> {
            getContentPane().removeAll();
            initMainFrame();
            buildChartSelectionMenu();
            System.gc();
        });
        
        return button;
    }
    
    private void removeActionListeners(){
        if(buttons[0].getActionListeners() != null){
            for(int i = 0; i < buttons.length; ++i){
                buttons[i].removeActionListener(buttons[i].getActionListeners()[0]);
            }
        }
    }
    
    private void setChartButtonImages(){
        try{
            //change icons of buttons
            //IMPORTANT: change images to the actual outputted graphs at the end
            //URL url = getClass().getClassLoader().getResource("/javacharts/images/scatter_plot.jpeg");
            //JOptionPane.showMessageDialog(this, url.getPath());
            //WHY DO TEH IMAGES NOT GET FOUND WHEN RUNNING FROM THE JAR FILE
            //I WANNA DIE
            //System.getProperty("java.class.path");
            //System.out.println(getClass().getClassLoader().getResource("/MainFrame.class"));
            ImageIcon icon1 = new ImageIcon(getClass().getResource("images/scatter_plot.jpeg"));
            buttons[0].setIcon(icon1);
            ImageIcon icon2 = new ImageIcon(getClass().getResource("images/box_plot.jpeg"));
            buttons[1].setIcon(icon2);
            ImageIcon icon3 = new ImageIcon(getClass().getResource("images/ogive.jpeg"));
            buttons[2].setIcon(icon3);
        } catch(Exception e){
            buttons[0].setText("Scatter Plot");
            buttons[0].setFont(buttons[0].getFont().deriveFont(36.0f));
            buttons[1].setText("Box Plot");
            buttons[1].setFont(buttons[1].getFont().deriveFont(36.0f));
            buttons[2].setText("Ogive");
            buttons[2].setFont(buttons[2].getFont().deriveFont(36.0f));
        }
    }
    
    private void setInputButtonImages(){
        try{
            ImageIcon icon1 = new ImageIcon(getClass().getResource("images/excel.jpeg"));
            buttons[0].setIcon(icon1);
            ImageIcon icon2 = new ImageIcon(getClass().getResource("images/csv.jpeg"));
            buttons[1].setIcon(icon2);
            ImageIcon icon3 = new ImageIcon(getClass().getResource("images/mouse_cursor.jpeg"));
            buttons[2].setIcon(icon3);
        }catch(Exception e){
            buttons[0].setText("Excel");
            buttons[0].setFont(buttons[0].getFont().deriveFont(36.0f));
            buttons[1].setText("CSV");
            buttons[1].setFont(buttons[1].getFont().deriveFont(36.0f));
            buttons[2].setText("Manual Input");
            buttons[2].setFont(buttons[2].getFont().deriveFont(36.0f));
        }
    }
}
