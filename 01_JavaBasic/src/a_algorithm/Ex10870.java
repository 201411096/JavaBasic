package a_algorithm;

import java.util.Scanner;

public class Ex10870 {
	static int func(int n) {
		if(n==0)
			return 0;
		else if(n<=2)
			return 1;
		else
			return func(n-1)+func(n-2);
			
	}

	public static void main(String[] args) throws ClassNotFoundException {
		Scanner scanner = new Scanner(System.in);
		int n = scanner.nextInt();		
		int answer = func(n);
		System.out.println(answer);
	}
}
