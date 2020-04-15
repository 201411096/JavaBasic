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

public class MainClientFrame extends JFrame implements ActionListener{	
	ClientChatPanel clientChatPanel;
	CliSocket clisocket;
	
	public MainClientFrame(){
		Configuration config = Configuration.getInstance();
		clisocket = new CliSocket(this);
		new Thread(clisocket).start();
		
		clientChatPanel = new ClientChatPanel();
		
		add(clientChatPanel, BorderLayout.EAST);
		
		setVisible(true);
		setSize(1920, 1080);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
	}
	@Override
	//말치면 보내는 부분
	public void actionPerformed(ActionEvent e) {
		String msg = clisocket.name+ ":" + clientChatPanel.sendTF.getText()+"\n";
		clisocket.sendMessage(msg);
		clientChatPanel.sendTF.setText("");
	}

	public void appendMsg(String msg) {
		clientChatPanel.ta.append(msg);
	}
	
}
