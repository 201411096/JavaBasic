package y_teamproject;

import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextArea;

public class OrderPanel extends JPanel {
	JLabel label = new JLabel("메뉴                                         수량                                      가격                        ");
	JPanel panel = new JPanel();
//	public OrderPanel(JTextArea jt, JButton b) {
//		setLayout(new GridLayout(2, 1));
//		setPreferredSize(new Dimension(420, 1080));
//		add(jt);
//		add(b);
//		
//	}
	public OrderPanel(JTextArea jt, JButton b) {
		setLayout(new FlowLayout());
		setPreferredSize(new Dimension(420, 1080));
		add(label);
		add(jt);
		add(b);
		
	}
}
