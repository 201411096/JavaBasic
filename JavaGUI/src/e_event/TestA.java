package e_event;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class TestA extends JFrame{
	JButton b1;
	JTextField tf;
	public TestA(){
		setLayout(new FlowLayout());
		b1=new JButton("button");
		tf=new JTextField(10);
		add(tf);
		add(b1);
		
		setVisible(true);
		setSize(600, 200);
		setLocation(200, 200);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}
	public void eventProc() { // 이벤트 연결
		MyEvent m = new MyEvent();
		b1.addActionListener(m);
		tf.addActionListener(m);
	}
	class MyEvent implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			Object o = e.getSource();
			if(o==b1)
			{
				String msg = b1.getText();
				JOptionPane.showMessageDialog(null, msg + "버튼 이벤트발생");
			}
				
			if(o==tf)
			{
				String msg = tf.getText();
				JOptionPane.showMessageDialog(null, msg + "를 입력하셨습니다.");
			}
				
		}
	}
	
	public static void main(String[] args) {
		TestA a = new TestA();
		a.eventProc();
	}
}
