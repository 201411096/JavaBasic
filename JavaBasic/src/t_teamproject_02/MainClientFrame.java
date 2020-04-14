package t_teamproject_02;

import java.awt.BorderLayout;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.net.Socket;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class MainClientFrame extends JFrame{
	Socket s;
	BufferedReader in;
	BufferedWriter out;
	ClientChatPanel clientChatPanel;
	
	int port=1234;
	
	public MainClientFrame(){
		Configuration config = Configuration.getInstance();
		
		clientChatPanel = new ClientChatPanel();
		
		add(clientChatPanel, BorderLayout.EAST);
		
		setVisible(true);
		setSize(1920, 1080);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		
	}
}
