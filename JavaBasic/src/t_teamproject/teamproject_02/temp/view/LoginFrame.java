package t_teamproject.teamproject_02.temp.view;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class LoginFrame extends JFrame{
	JTextField tfId;
	JPasswordField tfPassword;
	JLabel labelId, labelPassword;
	JButton signUp, signIn;
	JPanel panelList[] = new JPanel[3];
	JPanel totalPanel;
	public LoginFrame() {
		display();
		eventProc();
		connectDB();
		
	}
	public void display() {
		totalPanel = new JPanel();
		for(int i=0; i<panelList.length; i++)
			panelList[i] = new JPanel();
		
		labelId = new JLabel("ID");
		tfId = new JTextField();
		panelList[0].setLayout(new GridLayout(1,2));
		panelList[0].add(labelId);
		panelList[0].add(tfId);
		panelList[0].setBackground(Color.white);
		
		labelPassword = new JLabel("Password");
		tfPassword =  new JPasswordField();
		panelList[1].setLayout(new GridLayout(1,2));
		panelList[1].add(labelPassword);
		panelList[1].add(tfPassword);
		panelList[1].setBackground(Color.white);
		
		signUp = new JButton("직원등록");
		signIn = new JButton("로그인");
		panelList[2].setLayout(new GridLayout(1,2));
		panelList[2].add(signUp);
		panelList[2].add(signIn);
		
		totalPanel.setLayout(new GridLayout(3, 1));
		for(int i=0; i<panelList.length; i++)
			totalPanel.add(panelList[i]);
		totalPanel.setBackground(Color.white);
		totalPanel.setBorder(BorderFactory.createEmptyBorder(70, 70, 70, 70));
		setLayout(new FlowLayout());
		add(totalPanel);
		
		this.getContentPane().setBackground(Color.white);
		this.setLocationRelativeTo(null);
		setSize(480, 300);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
	}
	public void eventProc() {
		
	}
	public void connectDB() {
		
	}
}
