package temp;

import java.util.Scanner;

public class ex{
	public static void main(String[] args)
	{
		Scanner scanner = new Scanner(System.in);
		int n, m ;
		n=scanner.nextInt();
		char star[][] = new char[n][];
		
		for(int i=0; i<star.length; i++)
		{
			if(i%2==0)
				star[i] = new char[10];
			else
				star[i] = new char[2*i];
			for(int j=0; j<star[i].length; j++)
			{
				star[i][j]='*';
			}
		}
		for(int i=0; i<star.length; i++)
		{
			for(int j=0; j<star[i].length; j++)
			{
				System.out.print(star[i][j]);
			}
			System.out.println();
		}
		
		
		
		
	}
}










