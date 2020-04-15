package t_teamproject_02;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

import javax.swing.JFrame;

public class MainClientFrame extends JFrame{	
	ClientChatPanel clientChatPanel;

	
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
