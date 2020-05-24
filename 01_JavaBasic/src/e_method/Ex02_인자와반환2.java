package e_method;

public class Ex02_인자와반환2 {
	
	public static void main(String[] args) {
		int sum = add();
		System.out.println("출력: " + sum);
	}
	static int add()
	{
		int a = 10, b=20;
		int sum=a+b;
		return sum;
	}
}
/*
 * return : 프로그램 흐름 반환 
 * 			하나의 변수를 반환할 수 있음
 */
