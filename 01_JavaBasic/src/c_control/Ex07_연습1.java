package c_control;

import java.util.Scanner;

public class Ex07_연습1 {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int n, m;
		int num=1;
		n=scanner.nextInt();
		m=scanner.nextInt();
		for(int i=1; i<=n; i++)
		{
			for(int j=1; j<=m; j++)
			{
				System.out.print(num++ + " ");
			}
			System.out.println();
		}
		
	}
}
