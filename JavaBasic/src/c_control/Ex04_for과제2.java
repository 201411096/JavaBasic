package c_control;

import java.util.Scanner;

public class Ex04_for과제2 {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int n=scanner.nextInt();
		int num=1;
		for(int i=0; i<n; i++)
		{
			for(int m=0; m<i; m++)
				System.out.print("\t"); // i번째 줄 시작부분에 i-1개만큼의 공백 출력
			for(int j=0; j<n-i; j++) // i번째 줄에는 출력해야 하는 줄-i개만큼 출력
			{
				System.out.print(num++ + "\t" ); //출력할때마다 숫자 증가
			}
			
			
			System.out.println();
		}
		
	}
}
