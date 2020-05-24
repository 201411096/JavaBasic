package c_control;

import java.util.Scanner;

public class Ex06_dowhile개념 {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		String result;
		 do{
			int n;
			System.out.println("숫자 하나를 입력해주세요");
			n=scanner.nextInt();
			for(int i=1; i<=9; i++)
			{
				System.out.printf("%d * %d = %d\n", n, i, n*i);
			}
			System.out.println("구구단 다시 할까요?");
			result = scanner.next();
			
		}while((result.equals("Y") || result.equals("y")));
	}
}
