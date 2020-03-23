package e_method;

import java.util.Scanner;

public class Ex00_복습2 {
	static int jumsu;
	public static void main(String[] args) {
		String s = func();
		System.out.println("결과: " + s);
		// 여기서 합격 여부를 출력하려면?		
		method();
	}
	/** 
	 * method1
	 *  	- 점수를 입력받아 합격여부 확인하는 메소드
	 */
	static  String func() {
		Scanner in = new Scanner(System.in);		
		jumsu = in.nextInt();
		if( jumsu >= 80) return "합격";
		else return "불합격";
	}	
	/**
	 * 점수를 넘겨받아 성적을 구하는 메소드
	 */
	static void method() {
		if(jumsu>=90)
			System.out.println("A");
		else if(jumsu>=80)
			System.out.println("B");
		else if(jumsu>=70)
			System.out.println("C");
		else if(jumsu>=60)
			System.out.println("D");
		else
			System.out.println("F");
	}

}
