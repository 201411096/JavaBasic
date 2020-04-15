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




public class MainServerFrame extends JFrame implements ActionListener{
	SevSocket sevsocket=null;
	
	SeatViewPanel seatViewPanel;
	ServerChatPanel serverChatPanel;
	JPanel testPanel;
	JTabbedPane jtabbedPane = new JTabbedPane();
	public MainServerFrame() {
		sevsocket = new SevSocket(this);
		new Thread(sevsocket).start();
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
		
		serverChatPanel.sendB.addActionListener(this);
		serverChatPanel.sendTF.addActionListener(this);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		String msg = "서버 : "+ serverChatPanel.sendTF.getText() + "\n";
		System.out.print(msg);
		sevsocket.sendMessage(msg);
		serverChatPanel.sendTF.setText("");
	}

	public void appendMsg(String msg) {
		serverChatPanel.ta.append(msg);
	}

}
