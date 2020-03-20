package c_control;

import java.util.Scanner;

public class Ex04_for연습3 {
	public static void main(String [] args) {
		Scanner scanner = new Scanner(System.in);
		String str="";
		System.out.println("문자열 입력");
		str=scanner.nextLine();
		int pos=str.length();
		for(int i=0; i<str.length(); i++) {
			char a = str.charAt(pos-1);
			pos--;
			System.out.print(a);
		}
	}
}
