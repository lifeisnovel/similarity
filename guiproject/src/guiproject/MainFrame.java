package guiproject;

import java.util.ArrayList;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.SwingConstants;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.QuickChart;
import org.knowm.xchart.XChartPanel;
import org.knowm.xchart.XYChart;
import org.knowm.xchart.XYChartBuilder;
import org.knowm.xchart.style.XYStyler;
import org.knowm.xchart.style.markers.Marker;
import org.knowm.xchart.style.markers.None;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MainFrame extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	/**
	 * @wbp.nonvisual location=101,279
	 */

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame frame = new MainFrame();
					//frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);

		double[] xData = new double[] {0.0, 1.0, 2.0};
		double[] yData = new double[] {2.0, 1.0, 0.0};
		
	    // Customize Chart
	    //chart.getStyler().setLegendPosition(LegendPosition.InsideNE);
	    //chart.getStyler().setDefaultSeriesRenderStyle(XYSeriesRenderStyle.Area);

	    // Schedule a job for the event-dispatching thread:
	    // creating and showing this application's GUI.
	    javax.swing.SwingUtilities.invokeLater(new Runnable() {

	      @Override
	      public void run() {

	        // Create and set up the window.
	        JFrame chartFrame = new JFrame("Smooting Project");
	        chartFrame.setLayout(new BorderLayout());
	        chartFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	        // label
	        //JLabel label = new JLabel("Blah blah blah.", SwingConstants.CENTER);
	        //chartFrame.add(label, BorderLayout.SOUTH);
	        
	        JPanel ctrlPanel = new JPanel();
			//ctrlPanel.setBounds(0, 0, 300, 200);
			//ctrlPanel.setLayout(null);
			
			JLabel lblOrder = new JLabel("order");
			lblOrder.setBounds(34, 51, 57, 15);
			ctrlPanel.add(lblOrder);
			
			textField = new JTextField();
			textField.setBounds(147, 48, 116, 21);
			ctrlPanel.add(textField);
			textField.setColumns(10);
			
			JLabel lblWindowSize = new JLabel("window size");
			lblWindowSize.setBounds(34, 76, 79, 15);
			ctrlPanel.add(lblWindowSize);
			
			textField_1 = new JTextField();
			textField_1.setBounds(147, 73, 116, 21);
			ctrlPanel.add(textField_1);
			textField_1.setColumns(10);
			
		    JButton btnFile = new JButton("File Select");
			btnFile.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					JFileChooser chooser = new JFileChooser();
					FileNameExtensionFilter filter = new FileNameExtensionFilter("txt 파일", "txt");
					chooser.setFileFilter(filter);
					int returnVal = chooser.showOpenDialog(chartFrame);
					if (returnVal == JFileChooser.APPROVE_OPTION) {
						/* input File */
						File inFile = chooser.getSelectedFile();
						
						ArrayList xDataList = new ArrayList<Double>();
						ArrayList yDataList = new ArrayList<Double>();

						XYChart chart = new XYChartBuilder().width(600).height(400).title(chooser.getSelectedFile().getName()).xAxisTitle("Mass/Charge").yAxisTitle("Intensity").build();
						
						int lineNum = 0;
						BufferedReader br = null;
						try {
							br = new BufferedReader(new FileReader(inFile));
							String line;
							while ((line = br.readLine()) != null) {
								if (lineNum > 0) {
									// 둘째 줄부터 데이터 시작이므로 둘째 줄부터 읽어옴
									xDataList.add(Double.valueOf(line.split("\t")[0]).doubleValue());
									yDataList.add(Double.valueOf(line.split("\t")[1]).doubleValue());
								}
								lineNum++;
							}
						} catch (FileNotFoundException e) {
							JOptionPane.showMessageDialog(null, "파일을 열 수 없습니다!");
							e.printStackTrace();
						} catch (IOException e) {
							JOptionPane.showMessageDialog(null, "파일을 여는 중에 에러가 발생하였습니다.");
							e.printStackTrace();
						} finally {
							if (br != null) {
								try {
									//Marker[] chartMarker = new Marker[](None);
									
								    // Series
								    chart.addSeries("before", xDataList, yDataList);
								    //chart.getStyler().setSeriesMarkers(chartMarker);

							        // chart
							        JPanel chartPanel = new XChartPanel<XYChart>(chart);
							        chartFrame.add(chartPanel, BorderLayout.CENTER);
							        
							        // Display the window.
							        chartFrame.pack();
							        chartFrame.setVisible(true);
									br.close();
								} catch (IOException e) {
									JOptionPane.showMessageDialog(null, "파일을 여는 중에 에러가 발생하였습니다.");
									e.printStackTrace();
								}
							}
						}
					}
				}
			});
			btnFile.setBounds(302, 47, 97, 23);
			ctrlPanel.add(btnFile);
			
			JButton btnSubmit = new JButton("submit");
			btnSubmit.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					JOptionPane.showMessageDialog(null, "구현 중입니다.");
				}
			});
			btnSubmit.setBounds(302, 72, 97, 23);
			ctrlPanel.add(btnSubmit);
			
	        //contentPane.add(ctrlPanel);
			chartFrame.add(ctrlPanel, BorderLayout.NORTH);
			
	        // Display the window.
	        chartFrame.pack();
	        chartFrame.setVisible(true);
	      }
	    });
		
		
		//new SwingWrapper(chart).displayChart();
		
	}
}
