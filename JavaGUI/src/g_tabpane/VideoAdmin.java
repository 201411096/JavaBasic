package g_tabpane;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JPanel;

public class VideoAdmin extends JPanel{
	JButton b;
	public VideoAdmin(){
		b = new JButton("aaa");
		b.setPreferredSize(new Dimension(100, 50));
		
		setBackground(Color.BLUE);
		add(b);
	}
}
