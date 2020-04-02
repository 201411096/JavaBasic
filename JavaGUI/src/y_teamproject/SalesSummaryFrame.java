package y_teamproject;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class SalesSummaryFrame extends JFrame{
	int size = 16;
	JLabel labelList[] = new JLabel[size];
	JPanel panelLineList[] = new JPanel[size]; // 5줄로 표시
	JPanel panelList[] = new JPanel[size]; //라벨과 같이 panelLineList 에 같이 들어가는 패널
	Menu menu = new Menu();
	public SalesSummaryFrame(int totalmenuCount[]) {
		Menu sortedMenuList [] = menu.getRankArray(totalmenuCount); // 매출 숫자가 담긴 배열을 받아서 높은 순서대로 배치
		int countSoldProduct = menu.countSoldProduct(sortedMenuList); // 매출 순위에 표시할 갯수를 받음
		menu.getPercentage(sortedMenuList); // 메뉴 배열의 각 요소마다의 %를 구해줌
		System.out.println(countSoldProduct);
		setLayout(new GridLayout(size,1));
		
		for(int i=0; i<panelLineList.length; i++)
		{
			FlowLayout flowLayout = new FlowLayout(FlowLayout.LEFT); 
			flowLayout.setHgap(0); // flowlayout의 component간 horizantal(수평)간격을 0으로 설정
			flowLayout.setVgap(0); // flowlayout의 component간 vertical(수직)간격을 0으로 설정
			panelLineList[i] = new JPanel(); // 줄마다
			panelLineList[i].setLayout(flowLayout); // 각 줄마다 라벨 하나와 패널 하나가 들어감
			if(sortedMenuList[i].count!=0) // 매출이 0이 아닐경우에만 라벨 이름 표시
				labelList[i] = new JLabel(sortedMenuList[i].name);
			else
				labelList[i] = new JLabel();
			panelList[i] = new JPanel(); // 각 줄에 있는 큰 패널에 들어가는 작은 패널들
			labelList[i].setPreferredSize(new Dimension(100, 40));
			panelList[i].setLayout(flowLayout); // 작은 패널 하나에 패널 100개가 들어감
			panelList[i].add(new JPanel());
			JPanel panelPercent[] = new JPanel[100];
			for(int j=0; j<sortedMenuList[i].percent; j++)
			{
				panelPercent[j] = new JPanel();
				panelPercent[j].setPreferredSize(new Dimension(18, 40));
				panelPercent[j].setBackground(new Color(j*2, 0, 0));
				panelList[i].add(panelPercent[j]);
			}
			panelLineList[i].add(labelList[i]); // 각 줄에 라벨 부착
			panelLineList[i].add(panelList[i]); // 각 줄에 그래프? 부착
			add(panelLineList[i]); // 프레임에 줄마다 부착
		}
		setVisible(true);
		setSize(1920, 1080);
		setExtendedState(JFrame.MAXIMIZED_BOTH); // 프로그램 시작시 최대화
		setTitle("매출 순위");
	}
}
