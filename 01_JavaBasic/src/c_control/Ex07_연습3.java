package c_control;

import java.util.Scanner;

public class Ex07_연습3 {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int n,m;
		n=scanner.nextInt();
		m=scanner.nextInt();
				
		for(int i=1; i<=n; i++)
		{
			if(i%2==1)
			{
				for(int j=1; j<=m; j++)
				{
					System.out.print(((i-1)*m+j) + " ");
				}
				
			}else {
				for(int j=1; j<=m; j++)
				{
					System.out.print((i*m-j+1) + " ");
				
				}
				
			}
			System.out.println();
		}
		
	}
}
