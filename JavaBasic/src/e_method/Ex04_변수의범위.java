package e_method;

public class Ex04_변수의범위 {
	//멤버 변수
	static int a,b, sum; //heap 영역에 할당 // 0으로 자동 초기화
	static int c;
	public static void main(String[] args) {
		//데이터 입력
		a=10; //stack에 할당(지역변수)
		b=20;
		add();
		System.out.println("합 : " + sum);
		System.out.println("c : " + c);
	}
	static void add()
	{
		int sum =a+b;
//		System.out.println("합 : " + sum);
	}
}
