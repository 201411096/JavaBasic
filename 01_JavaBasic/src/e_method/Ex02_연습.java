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
	static char checkUpper(char c) 
	{
		if(c>='A' && c<='Z')
			return c;
		else if(c>='a' && c<='z') // 소문자일 경우 대문자로 변환
			return (char)(c+('A'-'a'));
		else
			return c;
	}
	
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		char ch = scanner.nextLine().charAt(0);
		boolean isLower =checkLower(ch);
		
		
		if(isLower==true)
			System.out.println("소문자");
		ch =checkUpper(ch);
		System.out.println(ch);
		
	}
}
