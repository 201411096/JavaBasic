package b_baekjoon;

import java.util.Scanner;
import java.util.StringTokenizer;

public class Ex2908 {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		String str = scanner.nextLine();
		StringTokenizer st = new StringTokenizer(str);
		String temp;
		int num1, num2;

		temp = st.nextToken();
		num1 = (int)(temp.charAt(0)-'0')+10*((int)(temp.charAt(1)-'0'))+100*((int)(temp.charAt(2)-'0'));
		temp = st.nextToken();
		num2 = (int)(temp.charAt(0)-'0')+10*((int)(temp.charAt(1)-'0'))+100*((int)(temp.charAt(2)-'0'));
		
		if(num1>num2)
			System.out.println(num1);
		else
			System.out.println(num2);
	}
}
