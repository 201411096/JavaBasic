package y_teamproject;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/*
 *  각 줄을 panelLineList[] 로 표현
 *  panelLineList[] 의 각 요소에는 메뉴의 이름 부분을 나타내는 labelList[] 와 그래프를 표기하는 panelList[]로 구성
 *  그래프를 표기하는 부분인 panelList[]에는 메뉴별 퍼센트(인트)값만큼 label을 색칠해서 추가하는 식으로 색이 변하는 그래프 표현 
 */

public class SalesSummaryFrame extends JFrame{
	private int size = 16;
	private JLabel labelList[] = new JLabel[size]; 							// panelList와 같이 panelLineList에 같이 들어가는 라벨
	private JPanel panelLineList[] = new JPanel[size]; 						//하나의 줄을 구성하는 패널 배열
	private JPanel panelList[] = new JPanel[size];							 //labelList과 같이 panelLineList 에 같이 들어가는 패널
	private Menu menu = new Menu();
	public SalesSummaryFrame(int totalmenuCount[]) {
		Menu sortedMenuList [] = menu.getRankArray(totalmenuCount); // 매출 숫자가 담긴 배열을 받아서 높은 순서대로 배치
		menu.getPercentage(sortedMenuList); 						// 메뉴 배열의 각 요소마다의 %를 구해줌
		setLayout(new GridLayout(size,1));
		
		for(int i=0; i<panelLineList.length; i++)
		{
			FlowLayout flowLayout = new FlowLayout(FlowLayout.LEFT); 
			flowLayout.setHgap(0);									 // flowlayout의 component간 horizantal(수평)간격을 0으로 설정
			flowLayout.setVgap(0);									 // flowlayout의 component간 vertical(수직)간격을 0으로 설정
			panelLineList[i] = new JPanel(); 						// 줄마다 들어가는 패널
			panelLineList[i].setLayout(flowLayout); 				// 각 줄마다 라벨 하나와 패널 하나가 들어감
			panelList[i] = new JPanel(); 							// 각 줄에 있는 큰 패널에 들어가는 작은 패널들
			panelList[i].setLayout(flowLayout); 					// 그래프를 표현할 패널
				
			if(sortedMenuList[i].getCount()!=0)						// 매출이 0이 아닐경우에만 라벨 이름 표시
				labelList[i] = new JLabel(sortedMenuList[i].getName());
			else
				labelList[i] = new JLabel();			
			labelList[i].setPreferredSize(new Dimension(100, 40));	// 라벨 크기 고정
			
			JPanel panelPercent[] = new JPanel[100];
			for(int j=0; j<sortedMenuList[i].getPercent(); j++)		// 메뉴의 매출 퍼센트만큼 패널을 만들어서 그래프 패널에 부착
			{
				panelPercent[j] = new JPanel();
				panelPercent[j].setPreferredSize(new Dimension(18, 40));
				panelPercent[j].setBackground(new Color(j*2, 0, 0));		//%가 높을수록 빨간색이 점점 진해짐
				panelList[i].add(panelPercent[j]);							
			}
			panelLineList[i].add(labelList[i]); // 각 줄에 라벨 부착
			panelLineList[i].add(panelList[i]); // 각 줄에 그래프? 부착
			add(panelLineList[i]); 				// 프레임에 줄마다 부착
		}
		setVisible(true);
		setSize(1920, 1080);
		setExtendedState(JFrame.MAXIMIZED_BOTH); // 프로그램 시작시 최대화
		setTitle("판매 순위");
	}	
}
