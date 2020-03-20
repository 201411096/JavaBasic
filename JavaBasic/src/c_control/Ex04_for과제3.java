package c_control;

import java.util.Scanner;

public class Ex04_for과제3 {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int n, m, num=1;
		n=scanner.nextInt(); //높이 입력
		m=scanner.nextInt(); //너비 입력
		
		for(int i=0; i<n; i++) // 높이 개수만큼 개행
		{
			for(int j=0; j<m; j++) // 너비 개수만큼 숫자 출력
			{
				System.out.print(num++ + " "); //출력할떄마다 숫자 증가
			}
			System.out.println(); //개행
		}
	}
}
