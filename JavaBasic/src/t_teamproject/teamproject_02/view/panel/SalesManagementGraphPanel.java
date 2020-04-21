package t_teamproject.teamproject_02.view.panel;

import java.awt.GridLayout;

import javax.swing.JPanel;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;

import t_teamproject.teamproject_02.jfreechart.BarChartProductCount;
import t_teamproject.teamproject_02.jfreechart.SalesManagementChart;

public class SalesManagementGraphPanel extends JPanel{
	SalesManagementChart demo; //이거도 만들어야됨
	JFreeChart chart;
	ChartPanel chartPanel;

	JPanel wrapperPanel;
	
	int option; //일별 월별 년도별 제품별
	
	public SalesManagementGraphPanel() {
		display();
	}
	public void display() {
		setLayout(new GridLayout(1, 1));
		
		wrapperPanel = new JPanel();
		wrapperPanel.setLayout(new GridLayout(1, 1));
		
		demo = new SalesManagementChart();
		chart = demo.getChart(0);
		chartPanel=new ChartPanel(chart);
		wrapperPanel.add(chartPanel);
		add(wrapperPanel);
	}
	
	public void renewal() {
		remove(wrapperPanel); // 기존 wrapper 패널을 제거하고 다시 화면을 구성함 (탭 선택시 새로고침 효과를 주는 데 사용함)
		wrapperPanel = new JPanel();
		wrapperPanel.setLayout(new GridLayout(1, 1));
		
		demo = new SalesManagementChart();
		chart = demo.getChart(0);
		chartPanel=new ChartPanel(chart);
		wrapperPanel.add(chartPanel);
		add(wrapperPanel);
	}

}
