package b_encapsulation;

public class Main {

	public static void main(String[] args) {

		Student s = new Student();
//		s.kor=70;
//		s.eng=70;
//		s.math=70;
		s.setKor(80);
		s.setEng(80);
		s.setMath(80);
		System.out.println(s.getKor() + "/" + s.getEng() + "/" + s.getMath());
		s.setName("홍길동");
		System.out.println("이름: " + s.getName());
		System.out.println("총점 : " + s.calTotal());
		System.out.println("평균 : " + s.calAverage());
		
	}
	/*
	 * [ 결론내기 ] 캡슐화란??? 
	 */
}
