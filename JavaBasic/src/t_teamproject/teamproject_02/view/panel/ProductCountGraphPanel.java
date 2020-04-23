package t_teamproject.teamproject_02.view.panel;

import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;

import t_teamproject.teamproject_02.jfreechart.BarChartProductCount;
import t_teamproject.teamproject_02.view.frame.ManagementFrame;

public class ProductCountGraphPanel extends JPanel{
	ManagementFrame managementFrame;
	
	JPanel wrapperPanel;
	
	BarChartProductCount demo;
	JFreeChart chart;
	ChartPanel chartPanel;
	public ProductCountGraphPanel(ManagementFrame managementFrame){
		this.managementFrame = managementFrame;
		display();
	}
	public void display() {
		setLayout(new GridLayout(1, 1));
		
		wrapperPanel = new JPanel();
		wrapperPanel.setLayout(new GridLayout(1, 1));
		
		demo = new BarChartProductCount();
		chart = demo.getChart();
		chartPanel=new ChartPanel(chart);
		wrapperPanel.add(chartPanel);
		add(wrapperPanel);
	}
	/* 함수이름 : renewal
	 * 인자값 : 없음
	 * 반환값 : 없음
	 * 함수설명 : 화면을 갱신하는 함수(차트의 내용이 변경될 경우 실행)
	 * 함수 실행시기 : 이 탭이 선택될시에 실행
	 */
	public void renewal() {
		remove(wrapperPanel); // 기존 wrapper 패널을 제거하고 새로운 데이터를 바탕으로 다시 화면을 구성함 (탭 선택시 새로고침 효과를 주는 데 사용함)
		wrapperPanel = new JPanel();
		wrapperPanel.setLayout(new GridLayout(1, 1));
		
		demo = new BarChartProductCount();
		chart = demo.getChart();
		chartPanel=new ChartPanel(chart);
		wrapperPanel.add(chartPanel);
		add(wrapperPanel);
	}

}
