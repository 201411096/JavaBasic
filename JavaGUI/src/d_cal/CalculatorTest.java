package d_cal;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Panel;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;

public class CalculatorTest extends JFrame{
	// 1. 멤버변수 선언
	JButton b [] = new JButton[15];
	JTextField tf;
	// 2. 객체 생성
	public CalculatorTest() {
		tf = new JTextField();
		b[0] = new JButton("1");
		b[1] = new JButton("2");
		b[2] = new JButton("3");
		b[3] = new JButton("4");
		b[4] = new JButton("5");
		b[5] = new JButton("6");
		b[6] = new JButton("7");
		b[7] = new JButton("8");
		b[8] = new JButton("9");
		b[9] = new JButton("+");
		b[10] = new JButton("0");
		b[11] = new JButton("=");
		b[12] = new JButton("-");
		b[13] = new JButton("*");
		b[14] = new JButton("/");
	}
	
	//3. 화면 구성 및 출력하기
	public void display() {
		setLayout(new BorderLayout());
		Panel p1 = new Panel();
		
		p1.setLayout(new GridLayout(5, 3));
		for(int i=0; i<b.length; i++)
			p1.add(b[i]);
		
		add(p1, BorderLayout.CENTER);
		add(tf, BorderLayout.NORTH);
		setLocation(500, 500);
		setTitle("유치원 계산기");
		setSize(600, 400);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	public void eventConn() { // 이벤트 연결
		for(int i=0; i<15; i++)
		{
			if(i!=11)
				b[i].addMouseListener(new writeEvent());
		}
		b[11].addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				String remain = tf.getText();
				String args1=""; // 피연산자1
				String args2=""; // 피연산자2
				String operator = ""; // 연산자
				int result =0; // 결과값
				for(int i=0; i<remain.length(); i++)
				{
					if(remain.charAt(i) == '+' || remain.charAt(i) == '-' || remain.charAt(i) == '*' || remain.charAt(i) == '/')
					{
						operator= Character.toString(remain.charAt(i));
						args1 = remain.substring(0, i);
						args2 = remain.substring(i+1, remain.length());
					}else if(i==remain.length()-1){
						tf.setText(null);
					}
				}
				switch(operator) {
				case "+": result= Integer.parseInt(args1) + Integer.parseInt(args2);break;
				case "-": result= Integer.parseInt(args1) - Integer.parseInt(args2);break;
				case "*": result= Integer.parseInt(args1) * Integer.parseInt(args2);break;
				case "/": result= Integer.parseInt(args1) / Integer.parseInt(args2);break;
				}
				tf.setText(Integer.toString(result));
			}
		});
		tf.addKeyListener(new keyEvent());
	}
	class writeEvent extends MouseAdapter{
		@Override
		public void mouseClicked(MouseEvent e) {
			JButton b = (JButton)e.getSource();
			String remain = tf.getText();
			String s = b.getText();
			if(!remain.equals("0"))
				tf.setText(remain+s);
			else
				tf.setText(s);
		}
	}
	class keyEvent extends KeyAdapter{

		@Override
		public void keyPressed(KeyEvent e) {
			char c = e.getKeyChar();
			String remain = tf.getText();
			String args1=""; // 피연산자1
			String args2=""; // 피연산자2
			String operator = ""; // 연산자
			int result =0; // 결과값
			if(c=='\n') {
				for(int i=0; i<remain.length(); i++)
				{
					if(remain.charAt(i) == '+' || remain.charAt(i) == '-' || remain.charAt(i) == '*' || remain.charAt(i) == '/')
					{
						operator= Character.toString(remain.charAt(i));
						args1 = remain.substring(0, i);
						args2 = remain.substring(i+1, remain.length());
					}else if(i==remain.length()-1){
						tf.setText(null);
					}
				}
				switch(operator) {
				case "+": result= Integer.parseInt(args1) + Integer.parseInt(args2);break;
				case "-": result= Integer.parseInt(args1) - Integer.parseInt(args2);break;
				case "*": result= Integer.parseInt(args1) * Integer.parseInt(args2);break;
				case "/": result= Integer.parseInt(args1) / Integer.parseInt(args2);break;
				}
				tf.setText(Integer.toString(result));
			}
		}
		
	}
	public static void main(String[] args) {
		CalculatorTest cal = new CalculatorTest();
		cal.display();
		cal.eventConn();
	}
}
