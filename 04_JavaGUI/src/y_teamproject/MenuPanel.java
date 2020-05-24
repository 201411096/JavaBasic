package y_teamproject;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

public class MenuPanel extends JPanel{

	public MenuPanel(JButton b1, JButton b2, JButton b3, JButton b4) { //tabbedPane에 붙이는 JPanel
		setLayout(new GridLayout(2,2));
		setBackground(Color.white);
		add(b1);
		add(b2);
		add(b3);
		add(b4);
		setPreferredSize(new Dimension(1500, 1080));
	}
}
