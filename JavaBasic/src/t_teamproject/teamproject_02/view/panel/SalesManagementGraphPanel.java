package t_teamproject.teamproject_02.view.panel;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;

import t_teamproject.teamproject_02.jfreechart.SalesManagementChart;

public class SalesManagementGraphPanel extends JPanel{
	SalesManagementChart demo; //이거도 만들어야됨
	JFreeChart chart;
	ChartPanel chartPanel;

	JComboBox comboboxOption;
	String optionList [] = {"일별", "월별", "연도별"};
	JPanel upPanel;
	JPanel wrapperPanel;
	
	int option; //일별 월별 년도별 제품별
	
	public SalesManagementGraphPanel() {
		display();
		eventProc();
	}
	public void display() {
//		setLayout(new GridLayout(1, 1));
		setLayout(new BorderLayout());
		
		upPanel = new JPanel();
		upPanel.add(new JLabel("매출 옵션"));
		comboboxOption = new JComboBox(optionList);
		upPanel.add(comboboxOption);
		wrapperPanel = new JPanel();
		wrapperPanel.setLayout(new GridLayout(1, 1));
		
		demo = new SalesManagementChart();
		chart = demo.getChart(0);
		chartPanel=new ChartPanel(chart);
		wrapperPanel.add(chartPanel);
//		add(wrapperPanel);
		add(wrapperPanel, BorderLayout.CENTER);
		add(upPanel, BorderLayout.NORTH);
	}
	public void eventProc() {
		EventHandler eventHandler = new EventHandler();
//		comboboxOption.addActionListener(eventHandler);
		comboboxOption.addItemListener(eventHandler);
	}
	
//	class EventHandler implements ActionListener{
//		@Override
//		public void actionPerformed(ActionEvent e) {
//			remove(wrapperPanel);
//			wrapperPanel = new JPanel();
//			wrapperPanel.setLayout(new GridLayout(1, 1));
//			
//			demo = new SalesManagementChart();
//			chart = demo.getChart(comboboxOption.getSelectedIndex());
//			chartPanel=new ChartPanel(chart);
//			wrapperPanel.add(chartPanel);
//			add(wrapperPanel);
//		}
//	}
	class EventHandler implements ItemListener{
		@Override
		public void itemStateChanged(ItemEvent e) {
			remove(wrapperPanel);
			wrapperPanel = new JPanel();
			wrapperPanel.setLayout(new GridLayout(1, 1));
			
			demo = new SalesManagementChart();
			chart = demo.getChart(comboboxOption.getSelectedIndex());
			chart.fireChartChanged();
			chartPanel=new ChartPanel(chart);
			wrapperPanel.add(chartPanel);
			add(wrapperPanel);
			
			repaint(); 		//화면 갱신 awt
			revalidate();	//화면 갱신 swing
		}
	}
	
	public void renewal() {
		comboboxOption.setSelectedIndex(0);
		remove(wrapperPanel); // 기존 wrapper 패널을 제거하고 다시 화면을 구성함 (탭 선택시 새로고침 효과를 주는 데 사용함)
		wrapperPanel = new JPanel();
		wrapperPanel.setLayout(new GridLayout(1, 1));
		
		demo = new SalesManagementChart();
		chart = demo.getChart(0);
		chartPanel=new ChartPanel(chart);
		wrapperPanel.add(chartPanel);
		add(wrapperPanel);
	}
	public void renewalInPage() {
		
	}

}
