package c_control;

import java.util.Scanner;

public class Ex07_연습2 {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int n;
		n=scanner.nextInt();
		
		for(int i=1; i<=n; i++)
		{
			for(int j=1; j<=n; j++)
			{
				System.out.print(i+n*(j-1) + " ");
			}
			System.out.println();
		}
	}
}
