package z_sample;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.*;

public class Login extends JFrame{
	
	JTextField tf1, tf2;
	JButton b1;
	public Login() {
		tf1 = new JTextField();
		tf2 = new JTextField();
		b1 = new JButton("로그인");
	}
	public void display() {
		setLayout(new BorderLayout());
		JPanel p1 = new JPanel();
		p1.setLayout(new GridLayout(2,2));
		p1.add(new JLabel("아이디"));
		p1.add(tf1);
		p1.add(new JLabel("비밀번호"));
		p1.add(tf2);
		add(p1, BorderLayout.CENTER);
		add(b1, BorderLayout.EAST);

		setTitle("Login");
		setSize(600, 300);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	public static void main(String[] args) {
		Login login = new Login();
		login.display();
	}
}
