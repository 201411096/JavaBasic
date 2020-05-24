package e_event;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class TestC extends JFrame{
	JButton b1;
	JTextField tf;
	public TestC(){
		setLayout(new FlowLayout());
		b1=new JButton("button");
		tf=new JTextField(10);
		add(tf);
		add(b1);
	}
	void display(){
		setVisible(true);
		setSize(600, 200);
		setLocation(200, 200);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}
	public void eventProc() { // 이벤트 연결
//		MyEvent m = new MyEvent();
//		b1.addActionListener(m);
//		b1.addActionListener(new MyEvent());
		b1.addActionListener(new ActionListener(){ // ActionListener를 구현한 이름없는 클래스를 객체 생성..
			@Override
			public void actionPerformed(ActionEvent e) {
				String msg = b1.getText();
				JOptionPane.showMessageDialog(null, msg + "버튼 이벤트발생");
			}
		});
		tf.addActionListener(new ActionListener(){ // ActionListener를 구현한 이름없는 클래스를 객체 생성..
			@Override
			public void actionPerformed(ActionEvent e) {
				String msg = tf.getText();
				JOptionPane.showMessageDialog(null, msg + "입력하였습니다.");
			}
		});
	}
//	class MyEvent implements ActionListener{
//		@Override
//		public void actionPerformed(ActionEvent e) {
//			String msg = b1.getText();
//			JOptionPane.showMessageDialog(null, msg + "버튼 이벤트발생");
//		}
//	}
	
	public static void main(String[] args) {
		TestC a = new TestC();
		a.display();
		a.eventProc();
	}
}
