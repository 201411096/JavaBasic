package h_inherit2;

import java.awt.Frame;
/* is-a : MyFrame2 is a Frame  */
class MyFrame2 extends Frame{
	MyFrame2(){
		setSize(800, 600);
		setVisible(true);
		setTitle("aaaa");
	}
}

public class Test2 {
	public static void main(String[] args) {
		new MyFrame2();
	}
}
