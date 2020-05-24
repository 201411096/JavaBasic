package c_control;

import java.util.Scanner;

public class Ex04_for연습2 {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		
		System.out.println("문자 하나 입력");
		char n=scanner.next().charAt(0);
		
		if(n>='A' && n<='Z') {
			for(char i=n; i<='Z' ;i++) {
				System.out.print(i + " ");
			}
			
		}else if(n>= 'a' && n<='z') {
			for(char i='a'; i<=n; i++) {
				System.out.print(i + " ");
			}
			
		}else{
			System.out.println("Error");
		}
		

	}

}
