package y_teamproject;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

public class MainScreenPanel extends JPanel {

	public MainScreenPanel(JButton b, ImageIcon i) { //tabbedPane에 붙이는 JPanel
		FlowLayout f = new FlowLayout();
		f.setHgap(0);
		f.setVgap(0);
		setLayout(new FlowLayout());
		b.setBackground(Color.white);
		add(b);
		setBackground(Color.white);
		setPreferredSize(new Dimension(1500, 1080));
	}
}
