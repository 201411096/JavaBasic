package h_inherit2;
import java.awt.*;
/* has-a : MyFrame1 has a Frame */
class MyFrame1{
	Frame f;
	MyFrame1(){
		f=new Frame("나의 첫 화면");
		f.setSize(800, 600);
		f.setVisible(true);
	}
}

public class Test {
	public static void main(String[] args) {
		new MyFrame1();
	}
}
