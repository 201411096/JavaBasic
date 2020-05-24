package c_control;

import java.util.Scanner;

public class Ex04_for연습 {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		System.out.println("정수 하나 입력");
		
		int num=scanner.nextInt();
		for(int i=1; i<=num; i++)
		{
			System.out.print(i + "\t");
			if(i%5==0)
				System.out.println();
		}
		
	}
}
