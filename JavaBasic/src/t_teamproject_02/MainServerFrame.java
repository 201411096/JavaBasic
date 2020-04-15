package t_teamproject_02;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

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
