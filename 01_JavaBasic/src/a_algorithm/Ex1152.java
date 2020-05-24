package a_algorithm;

import java.util.Scanner;
import java.util.StringTokenizer;

public class Ex1152 {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		String str = scanner.nextLine();
		StringTokenizer st = new StringTokenizer(str);
		int answer=0;
		while(st.hasMoreTokens())
		{
			st.nextToken();
			answer++;
		}
		System.out.println(answer);
	}
}
