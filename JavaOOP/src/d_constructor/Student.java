package d_constructor;

public class Student {

	private String name;
	private int kor, eng, math;
	private int total;
	private double avg;
		
	/**
		[ 참고 ] 초기화
				int a = 2;	
				int []a = { 9, 99, 999 };

	 	***** 		생성자 함수	*****
			클래스를 초기화 하고자 할 때

		-	클래스가 인스턴스화 될 때 (객체로 될 때) 실행되는 함수
		-	생성자이름과 동일해야만 함
		-	리턴 형이 없음 ( void 도 있음 안됨 )
		-	오버로딩 가능 (overloading) 
				:	 매개변수의 타입과 개수가 다르게 여러 개 생성자 함수 가능

*/
	public Student() {
		name = "홍길동";
		kor = 50;
		eng = 50;
		math = 50;
	}
	
	public Student(String name, int kor, int eng, int math) {
		this.name = name;
		this.kor = kor;
		this.eng = eng;
		this.math = math;
	}	
	
	/*
	 * this 와 this()
	 * 		this : 자기 객체를 지칭하는 예약어로 멤버변수나 멤버메소드를 명확하게 지칭할 때 사용
	 * 		this() : 다른 생성자 함수 호출시
	 * 				[ 주의 ] 제일 첫 라인에 기술되어야 한다
	 */
	public int calTotal()		{  
		total = kor + eng + math;  
		return total;
	}
	public double calAverage() 	{  
		avg = (double)total / 3;   
		return avg;
	}

	public void setName(String name) {
		this.name = name;
	}
	public void setKor(int kor) {
		this.kor = kor;
	}
	public void setEng(int eng) {
		this.eng = eng;
	}
	public void setMath(int math) {
		this.math = math;
	}
	public String getName() {
		return name;
	}
	
	
}
