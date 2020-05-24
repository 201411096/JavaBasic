package b_sample;


import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

//-------------- has-a 방식

public class Test {
	
	//멤버변수 선언
	JFrame f;
	JButton b;
	JLabel l;
	JTextField t;
	JTextArea ta;
	JCheckBox ch1;
	JCheckBox ch2;
	JList list;
	public Test() {
		// 객체 생성
		f = new JFrame("나의 창");
		b = new JButton("ok");
		l = new JLabel("name");
		t = new JTextField("이름은 5자 미만입니다.", 40);
		ch1 = new JCheckBox("man",true);
		ch2 = new JCheckBox("woman",false);
		ta = new JTextArea();
		
		String data [] = {"Mercury", "Venus", "Earth", "JavaSoft"};
		list = new JList(data);
		

		// 붙이기
//		f.setLayout(new FlowLayout());
//		f.setLayout(new GridLayout(4, 2));
		f.setLayout(new BorderLayout());
			JPanel np = new JPanel(new GridLayout(1, 2));
			np.add(t);
			np.add(list);
		f.add(np, BorderLayout.NORTH);
		f.add(b, BorderLayout.WEST);
		f.add(l, BorderLayout.EAST);
		f.add(ta, BorderLayout.CENTER);
			JPanel p = new JPanel();
//			p.setLayout(new GridLayout(2,1));
			p.add(ch1);
			p.add(ch2); // 한 영역에 하나만 가능 (이전 컴포넌트를 덮어씌움)
		f.add(p, BorderLayout.SOUTH);
		
		//화면 출력
		f.setSize(800, 600);
		f.setVisible(true);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public static void main(String[] args) {
		Test t = new Test();
	}
}
