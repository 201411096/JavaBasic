package a_sample;

import java.awt.FlowLayout;

import javax.swing.*;
// is-a 방식 (frame을 제외하고는 대부분 has-a)
public class Test2 extends JFrame{
	//멤버 변수 선언
	JRadioButton rb1;
	JRadioButton rb2;
	JRadioButton rb3;
	JRadioButton rb4;
	ButtonGroup g1;
	ButtonGroup g2;
	public Test2() {
		super("나의 창2");
		rb1 = new JRadioButton("남자", true);
		rb2 = new JRadioButton("여자");
		rb3 = new JRadioButton("성인");
		rb4 = new JRadioButton("미성년", true);
		g1 = new ButtonGroup();
		g2 = new ButtonGroup();
		g1.add(rb1);
		g1.add(rb2);
		g2.add(rb3);
		g2.add(rb4);
		
		
		setLayout(new FlowLayout());
		add(rb1);
		add(rb2);
		add(rb3);
		add(rb4);
		
		setSize(800, 600);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	public static void main(String[] args) {
		Test2 t = new Test2();
	}
}
