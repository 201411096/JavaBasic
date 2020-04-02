package y_teamproject;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class SalesSummaryFrame extends JFrame{
	JLabel labelList[] = new JLabel[6];
	JPanel panelLineList[] = new JPanel[6]; // 6줄로 표시
	JPanel panelList[] = new JPanel[6]; //라벨과 같이 panelLineList 에 같이 들어가는 패널
	
	public SalesSummaryFrame() {
		setLayout(new GridLayout(6,1));
		for(int i=0; i<panelLineList.length; i++)
		{
			panelLineList[i] = new JPanel(); // 줄마다
			panelLineList[i].setLayout(new BorderLayout()); // 각 줄마다 라벨 하나와 패널 하나가 들어감
			labelList[i] = new JLabel("가가가가가"); // 라벨 이름
			panelList[i] = new JPanel(); // 각 줄에 있는 큰 패널에 들어가는 작은 패널들
			panelList[i].setLayout(new GridLayout(1, 100)); // 작은 패널 하나에 패널 100개가 들어감
			JPanel panelPercent[] = new JPanel[100];
			for(int j=0; j<panelPercent.length; j++)
			{
				panelPercent[j] = new JPanel();
				panelPercent[j].setBackground(new Color(j*1, j*1, j*1));
				panelList[i].add(panelPercent[j]);
			}
			panelLineList[i].add(labelList[i], BorderLayout.WEST); // 각 줄에 라벨 부착
			panelLineList[i].add(panelList[i], BorderLayout.CENTER); // 각 줄에 그래프? 부착
			add(panelLineList[i]); // 프레임에 줄마다 부착
		}
		setVisible(true);
		setSize(800, 400);
		setLocation(600, 400);
		setTitle("매출 요약");
	}
}
