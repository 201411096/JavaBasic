package t_teamproject_02;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

public class MainServerFrame extends JFrame{
	SeatViewPanel seatViewPanel;
	ServerChatPanel serverChatPanel;
	JPanel testPanel;
	JTabbedPane jtabbedPane = new JTabbedPane();
	public MainServerFrame() {
		
		Configuration config = Configuration.getInstance();
		seatViewPanel = new SeatViewPanel();
		serverChatPanel = new ServerChatPanel();
		testPanel = new JPanel();

		
		jtabbedPane.add(seatViewPanel);
		jtabbedPane.add(testPanel);
		add(jtabbedPane);
		add(serverChatPanel, BorderLayout.EAST);
		setVisible(true);
		setSize(1920, 1080);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		
	}
}
