package a_sample;

import java.awt.*;

//-------------- has-a 방식

public class Test {
	
	//멤버변수 선언
	Frame f;
	Button b;
	Label l;
	TextField t;
	TextArea ta;
	Checkbox ch1;
	Checkbox ch2;
	CheckboxGroup chg1;
	List list;
	public Test() {
		// 객체 생성
		f = new Frame("나의 창");
		b = new Button("ok");
		l = new Label("name");
		t = new TextField("이름은 5자 미만입니다.", 40);
		chg1 = new CheckboxGroup();
		ch1 = new Checkbox("man", chg1, true);
		ch2 = new Checkbox("woman", chg1, false);
		ta = new TextArea();
		list = new List();
		list.add("aaa");
		list.add("bbb");
		list.add("ccc");
		list.add("ddd");
		list.add("ddd");
		list.add("ddd");
		
		b.setSize(40, 20);
		l.setSize(60, 20);
		t.setSize(200, 100);
		// 붙이기
//		f.setLayout(new FlowLayout());
//		f.setLayout(new GridLayout(4, 2));
		f.setLayout(new BorderLayout());
		f.add(t, BorderLayout.NORTH);
		f.add(b, BorderLayout.WEST);
		f.add(l, BorderLayout.EAST);
		f.add(ta, BorderLayout.CENTER);
			Panel p = new Panel();
//			p.setLayout(new GridLayout(2,1));
			p.add(ch1);
			p.add(ch2); // 한 영역에 하나만 가능 (이전 컴포넌트를 덮어씌움)
		f.add(p, BorderLayout.SOUTH);
//		f.add(list);
		//화면 출력
		f.setSize(800, 600);
		f.setVisible(true);
	}
	
	public static void main(String[] args) {
		Test t = new Test();
	}
}
