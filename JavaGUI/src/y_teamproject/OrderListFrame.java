package y_teamproject;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

public class OrderListFrame extends JFrame{
	JLabel labelList [] = new JLabel[4];
	String labelListName [] = {"메뉴", "수량", "가격", "합계: "};
	JPanel panel = new JPanel();
	public OrderListFrame(int total, String temp) {
		for(int i=0; i<labelList.length-1; i++)
			labelList[i] = new JLabel(labelListName[i], SwingConstants.CENTER);
		labelList[3] = new JLabel(labelListName[3] + total, SwingConstants.CENTER);

		panel.setLayout(new GridLayout(1, 3));
		for(int i=0; i<2; i++)
			panel.add(labelList[i]);
		add(panel, BorderLayout.NORTH);
		add(labelList[3], BorderLayout.SOUTH);
		add(new JTextArea(temp));
		setVisible(true);
		setSize(400, 400);
		setLocation(800, 400);
		setTitle("주문내역");
		
	}
	
}
