package a_basic;

public class Main {
	public static void main(String[] args) {
		//변수 선언
//		Student s;
		//객체 생성
//		s = new Student();
		Student s = new Student();
		s.kor = 70;
		s.eng = 88;
		s.math = 99;
		System.out.println("총점: " + s.calTotal());
		System.out.println("평균: " + s.calAverage());
	}
}
