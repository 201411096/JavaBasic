package t_teamproject_02;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class ServerChatPanel extends JPanel{
	JTextField sendTF;
	JButton sendB;
	JTextArea ta;
	public ServerChatPanel() {
		sendTF = new JTextField(16);
		sendB = new JButton("확 인");
		ta = new JTextArea(15,40);
		
		JPanel p = new JPanel();
		p.setLayout( new BorderLayout() );
		p.add( new JLabel("메시지 :"),"West" );
		p.add(sendTF,"Center");
		p.add(sendB, "East");
		
		setLayout(new BorderLayout());
		
		add(new JScrollPane(ta), BorderLayout.CENTER);
		add(p, BorderLayout.SOUTH);
		
		setBackground(Color.white);
		setPreferredSize(new Dimension(300, 1080));
	}
}
