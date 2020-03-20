package c_control;

import java.util.Scanner;

public class Ex04_for과제1 {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int n = scanner.nextInt();
		
		for(int i=0; i<n; i++) 
		{
			for(int m=n-i-1; m>0; m--) // i번째 줄에 n-i-1개 만큼의 공백 출력 
				System.out.print("  "); 
			
			
			for(int m=0; m<i+1; m++) // i번쨰 줄에 i개수 만큼 1부터 숫자 출력
			{
				System.out.print(m+1 + " "); 
			}
			System.out.println(); // 개행
		}
	}
}
