package c_control;

import java.util.Scanner;

public class Ex00 {
	public static void main(String args[])
	{
		int a,b,c;
		Scanner scanner = new Scanner(System.in);
		a=scanner.nextInt();
		b=scanner.nextInt();
		c=scanner.nextInt();
		
		if( (a>=b && c>=a) || (a>=c && b>=a) )
			System.out.println(a);
		else if( (b>=a && c>=b) || (b>=c && a>=b) )
			System.out.println(b);
		else
			System.out.println(c);

	}
}




























//
//if( (a>=b && c>=a) || (a>=c && b>=a) )
//	System.out.println(a);
//else if( (b>=c && a>=b) || (b>=a && c>=b) )
//	System.out.println(b);
//else
//	System.out.println(c);		