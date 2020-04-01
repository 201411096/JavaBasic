package y_teamproject;

import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class OrderPanel extends JPanel {
	public OrderPanel(JTextArea jt, JButton b) {
		setLayout(new GridLayout(2, 1));
		setPreferredSize(new Dimension(420, 1080));
		add(jt);
		add(b);
		
	}
}
