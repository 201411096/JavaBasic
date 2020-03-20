package b_operator;

import java.util.Scanner;

/**
 *  두 정수를 입력받아 사칙연산과 나머지 연산을 수행한 결과를 출력한다
 */

// 나머지 연산자 활용 - 홀/짝수 구하기

public class Ex03_Arithmetic {

	public static void main(String[] args) { 
//		int num1, num2, sum, sub, mul; // 변수 선언
//		double div;
//		Scanner scanner = new Scanner(System.in); //Scanner 선언
//		System.out.println("첫번쨰 정수 입력");  
//		num1 = scanner.nextInt(); //입력받아 변수에 저장
//		System.out.println("두번쨰 정수 입력");
//		num2 = scanner.nextInt();
//		sum = num1 + num2; //계산하여 결과변수에 저장
//		sub = num1 - num2;
//		mul = num1 * num2;
//		div = (double)num1 / num2;
//		System.out.println("두 정수의 덧셈 결과 :" + sum); // 결과 출력
//		System.out.println("두 정수의 뺄셈 결과 :" + sub);
//		System.out.println("두 정수의 곱셈 결과 :" + mul);
//		System.out.println("두 정수의 나눗셈 결과 :" + div);
		
		
		int su=0; //정수형 변수 su 선언
		Scanner scanner = new Scanner(System.in); // Scanner 선언
		System.out.println("정수를 입력하세요"); // 화면에 출력
		su = scanner.nextInt(); // 입력받은 정수를 su에 저장
		if(su % 2 == 0) {
			System.out.println("짝수입니다.");
		}
		else {
			System.out.println("홀수입니다.");
		}
		
		if(su % 3 == 0) {
			System.out.println("3의 배수입니다.");
		}else {
			System.out.println("3의 배수가 아닙니다.");
		}
	}

}
