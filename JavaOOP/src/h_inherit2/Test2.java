package h_inherit2;

import java.awt.Frame;
/* is-a : MyFrame2 is a Frame  */
class MyFrame2 extends Frame{
	MyFrame2(){
		super("나의 두번쨰 화면");
		setSize(800, 600);
		setVisible(true);
	}
}

public class Test2 {
	public static void main(String[] args) {
		new MyFrame2();
	}
}
