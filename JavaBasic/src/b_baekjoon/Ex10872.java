package b_baekjoon;

import java.util.Scanner;

public class Ex10872 {
	static int factorial(int n) {
		if(n<=1)
			return 1;
		else
			return factorial(n-1)*n;
	}

	public static void main(String[] args) throws ClassNotFoundException {
		Scanner scanner = new Scanner(System.in);
		int n = scanner.nextInt();		
		System.out.println(factorial(n));
	}
}

