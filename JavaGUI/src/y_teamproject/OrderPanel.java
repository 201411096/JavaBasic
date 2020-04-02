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

public class OrderPanel extends JPanel {
	JLabel label1 = new JLabel("메뉴",SwingConstants.CENTER);
	JLabel label2 = new JLabel("수량",SwingConstants.CENTER);
	JLabel label3 = new JLabel("가격",SwingConstants.CENTER);
	JPanel panel = new JPanel();
	JPanel panel1 = new JPanel();
	JPanel panel2 = new JPanel();

	public OrderPanel(JTextArea jt, JButton b1, JButton b2) {
		setLayout(new BorderLayout());
		panel1.setPreferredSize(new Dimension(420, 40));
		b1.setPreferredSize(new Dimension(210, 60));
		b2.setPreferredSize(new Dimension(210, 60));
		panel1.setLayout(new GridLayout(1, 3));
		panel1.add(label1);
		panel1.add(label2);
		panel1.add(label3);
		panel2.setLayout(new GridLayout(1, 2));
		panel2.add(b1);
		panel2.add(b2);

		add(panel1, BorderLayout.NORTH);		
		add(panel2, BorderLayout.SOUTH);
		add(jt, BorderLayout.CENTER);
		setPreferredSize(new Dimension(420, 1080));
	}
	public OrderPanel(JTextArea jt, JButton b1, JButton b2, JButton b3) { // textarea // 주문버튼 // 취소버튼 // 매출요약버튼
		setLayout(new BorderLayout());
		panel1.setPreferredSize(new Dimension(420, 40));
		b1.setPreferredSize(new Dimension(210, 60));
		b2.setPreferredSize(new Dimension(210, 60));
		b3.setPreferredSize(new Dimension(420, 40));
		panel1.setLayout(new GridLayout(1, 3));
		panel1.add(label1);
		panel1.add(label2);
		panel1.add(label3);
		panel.setLayout(new GridLayout(2, 1));
		panel.add(b3);
		panel.add(panel1);
		panel2.setLayout(new GridLayout(1, 2));
		panel2.add(b1);
		panel2.add(b2);

		add(panel, BorderLayout.NORTH);		
		add(panel2, BorderLayout.SOUTH);
		add(jt, BorderLayout.CENTER);
		setPreferredSize(new Dimension(420, 1080));
	}
}
