package t_teamproject_02;

import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JFrame;

public class MainServerFrame extends JFrame{
	SeatViewPanel seatViewPanel;
	public MainServerFrame() {
		Configuration config = Configuration.getInstance();
		seatViewPanel = new SeatViewPanel(10);
		
		setLayout(new FlowLayout());
		
		add(seatViewPanel);
		setVisible(true);
		setSize(1920, 1080);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		new Thread(seatViewPanel).start();
	}
}
