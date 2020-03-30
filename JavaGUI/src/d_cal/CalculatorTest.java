package d_cal;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
	public static void main(String[] args) {
		CalculatorTest cal = new CalculatorTest();
		cal.display();
	}
}
