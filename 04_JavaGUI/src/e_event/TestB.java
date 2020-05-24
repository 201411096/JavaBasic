package e_event;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class TestB extends JFrame implements ActionListener{
	JButton b1;

	JTextField tf;
	public TestB(){
		setLayout(new FlowLayout());
		b1=new JButton("button");
		tf=new JTextField(10);
		add(tf);
		add(b1);
	}
	void display() {
		setVisible(true);
		setSize(600, 200);
		setLocation(200, 200);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	public void eventConn() {
		b1.addActionListener(this);
		tf.addActionListener(this);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Object evt = e.getSource();
		if(evt==b1)
		{
			String msg = b1.getText();
			JOptionPane.showMessageDialog(null, msg + "버튼 이벤트발생");
		}
			
		if(evt==tf)
		{
			String msg = tf.getText();
			JOptionPane.showMessageDialog(null, msg + "를 입력하셨습니다.");
		}
	}
	
	public static void main(String[] args) {
		TestB b = new TestB();
		b.display();
		b.eventConn();
	}
}
