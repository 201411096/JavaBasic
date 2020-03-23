package e_method;

import java.util.Scanner;

public class Ex02_연습 {
	static boolean checkLower(char c)
	{
		if(c>='a' && c<='z')
			return true;
		else
			return false;
	}
	static boolean checkUpper(char c)
	{
		if(c>='A' && c<='Z')
			return true;
		else
			return false;
	}
	
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		char ch = scanner.nextLine().charAt(0);
		boolean isLower =checkLower(ch);
		boolean isUpper =checkUpper(ch);
		
		if(isLower==true)
			System.out.println("소문자");
		if(isUpper==true)
			System.out.println("대문자");
	}
}
