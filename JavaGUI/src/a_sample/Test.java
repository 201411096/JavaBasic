package a_sample;

import java.awt.Button;
import java.awt.Frame;

//-------------- has-a 방식

public class Test {
	
	//멤버변수 선언
	Frame f;
	Button b;
	public Test() {
		// 객체 생성
		f = new Frame("나의 창");
		b = new Button("ok");
		
		// 붙이기
		f.add(b);
		
		//화면 출력
		f.setSize(800, 600);
		f.setVisible(true);
	}
	
	public static void main(String[] args) {
		Test t = new Test();
	}
}
