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
		-	클래스이름과 동일해야만 함
		-	리턴 형이 없음 ( void 도 있음 안됨 )
		-	오버로딩 가능 (overloading) 
				:	 매개변수의 타입과 개수가 다르게 여러 개 생성자 함수 가능

*/
	public Student() {
		this("홍길동", 50, 50, 50); // this함수는 제일 첫번째 줄에 와야만 함
		System.out.println("인자없는 생성자");
	}
	public Student(String name, int kor, int eng, int math) {
		this.name = name;
		this.kor = kor;
		this.eng = eng;
		this.math = math;
		System.out.println("인자있는 생성자");
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getKor() {
		return kor;
	}

	public void setKor(int kor) {
		this.kor = kor;
	}

	public int getEng() {
		return eng;
	}

	public void setEng(int eng) {
		this.eng = eng;
	}

	public int getMath() {
		return math;
	}

	public void setMath(int math) {
		this.math = math;
	}


	
	
}
