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
	JPanel panel2 = new JPanel();


	public OrderPanel(JTextArea jt, JButton b1) {
		setLayout(new BorderLayout());
		panel.setPreferredSize(new Dimension(420, 40));
		b1.setPreferredSize(new Dimension(420, 60));
		panel.setBackground(Color.white);
		panel.setLayout(new GridLayout(1, 3));
		panel.add(label1);
		panel.add(label2);
		panel.add(label3);

		add(panel, BorderLayout.NORTH);
		
		setPreferredSize(new Dimension(420, 1080));

		add(jt, BorderLayout.CENTER);
		add(b1, BorderLayout.SOUTH);
		
	}
	public OrderPanel(JTextArea jt, JButton b1, JButton b2) {
		setLayout(new BorderLayout());
		panel.setPreferredSize(new Dimension(420, 40));
		b1.setPreferredSize(new Dimension(210, 60));
		b2.setPreferredSize(new Dimension(210, 60));
		panel.setBackground(Color.white);
		panel.setLayout(new GridLayout(1, 3));
		panel.add(label1);
		panel.add(label2);
		panel.add(label3);
		panel2.setLayout(new GridLayout(1, 2));
		panel2.add(b1);
		panel2.add(b2);

		add(panel, BorderLayout.NORTH);
		
		setPreferredSize(new Dimension(420, 1080));

		add(jt, BorderLayout.CENTER);
		add(panel2, BorderLayout.SOUTH);
		
	}
}
