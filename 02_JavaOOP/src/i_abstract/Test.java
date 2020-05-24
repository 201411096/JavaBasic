package i_abstract;

import java.util.Scanner;

public class Test {
	public static void main(String[] args) {
		// 화면가정 - 여기서 그림을 그린다 가정
		Sharp s = method();
		s.cal();
	}
	static Sharp method(){
		Scanner in = new Scanner(System.in);
		System.out.println("님이 원하는 도형은? 1.사각 2.원 3.삼각");
		int sel = in.nextInt();
		Sharp s = null;
		switch(sel){
		case 1 : s = new Rect(); break;
		case 2 : s = new Circle(); break;
		case 3 : s = new Tri(); break;
		}
		s.draw();
		return s;
	}
	//		[ 추가 ] 밑변이나 높이 등의 값을 각각 입력 받아 넓이 구하기
}
abstract class Sharp{
	public abstract void draw();
	public abstract void cal();
}
class Rect extends Sharp{
	public void draw(){
		System.out.println("사각형을 그림");
	}
	public void cal() {
		Scanner scanner = new Scanner(System.in);
		System.out.print("사각형의 높이와 길이를 입력하세요 ->");
		int n1= scanner.nextInt();
		int n2= scanner.nextInt();
		System.out.println("사각형의 넓이는 " + n1*n2 + " 입니다.");
	}
	public void rectangel(){
		System.out.println("사각형은 점 4개의 도형입니다.");
	}
}
class Circle  extends Sharp{
	public void draw(){
		System.out.println("원을 그림");
	}
	public void cal() {
		Scanner scanner = new Scanner(System.in);
		System.out.print("원의 반지름을 입력하세요 ->");
		int n1= scanner.nextInt();
		System.out.printf("원의 넓이는 %.5f 입니다.", n1*n1*Math.PI);
	}
}
class Tri  extends Sharp{
	public void draw(){
		System.out.println("삼각형을 그림");
	}
	public void cal() {
		Scanner scanner = new Scanner(System.in);
		System.out.print("삼각형의 높이와 밑변을 입력하세요 ->");
		int n1= scanner.nextInt();
		int n2= scanner.nextInt();
		System.out.println("삼각형의 넓이는 " + n1*n2/2.0 + " 입니다.");
	}
} 
