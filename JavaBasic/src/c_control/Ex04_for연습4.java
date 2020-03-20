package c_control;

import java.util.Scanner;

public class Ex04_for연습4 {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int n= scanner.nextInt();
		int num=1;
		for(int i=0; i<n; i++)
		{
			for(int j=0; j<n; j++)
			{
				System.out.print(num + " ");
				num+=2;
				if(num>10) num=1;
			}
			System.out.println();
		}
	}
}
