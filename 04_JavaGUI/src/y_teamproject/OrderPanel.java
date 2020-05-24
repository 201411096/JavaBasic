package y_teamproject;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

public class OrderPanel extends JPanel {		// 주문 내역을 보여주는 jtextarea를 포함한 패널
	private String labelName [] = {"메뉴", "수량", "가격"};
	private JLabel labelList [] = new JLabel[3];
	private JPanel panelList [] = new JPanel[3];
	
	//주문 내역 부분을 포함한 패널 화면을 구성
	public OrderPanel(JTextArea jt, JButton b1, JButton b2, JButton b3) { // textarea // 주문버튼 // 취소버튼 // 매출요약버튼
		setLayout(new BorderLayout());		
		b1.setPreferredSize(new Dimension(210, 60));
		b2.setPreferredSize(new Dimension(210, 60));
		b3.setPreferredSize(new Dimension(420, 40));

		for(int i=0; i<panelList.length; i++)
		{
			panelList[i] = new JPanel();
		}
		panelList[1].setPreferredSize(new Dimension(420, 40));
		panelList[1].setLayout(new GridLayout(1, 3));
		for(int i=0; i<labelList.length; i++)
		{
			labelList[i] = new JLabel(labelName[i], SwingConstants.CENTER);
			panelList[1].add(labelList[i]);
		}
		panelList[0].setLayout(new GridLayout(2,1));
		panelList[0].add(b3);
		panelList[0].add(panelList[1]);
		panelList[2].setLayout(new GridLayout(1, 2));
		panelList[2].add(b1);
		panelList[2].add(b2);

		add(panelList[0], BorderLayout.NORTH);
		add(panelList[2], BorderLayout.SOUTH);
		add(jt, BorderLayout.CENTER);
		setPreferredSize(new Dimension(420, 1080));
	}

}
