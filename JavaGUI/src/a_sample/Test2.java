package a_sample;

import java.awt.Button;
import java.awt.Frame;

public class Test2 extends Frame{
	//멤버 변수 선언
	Button b;
	public Test2() {
		super("나의 창2"); // 자동으로 들어오는 부분을 수정(무조건 첫번쨰 라인에 들어와야함)
		// 객체 생성
		b = new Button("ok");
		add(b);
		// 화면 출력
		setSize(800, 600);
		setVisible(true);
	}
	public static void main(String[] args) {
		Test2 t = new Test2();
	}
}
