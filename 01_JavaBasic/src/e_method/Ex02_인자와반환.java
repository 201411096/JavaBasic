package e_method;

public class Ex02_인자와반환 {
	static void add(int a, int b)
	{
		int sum =a+b;
		System.out.println("합 : " + sum);
	}
	
	public static void main(String[] args) {
		//데이터 입력
		int a=10, b=20;
		add(a,b);

	}

}
