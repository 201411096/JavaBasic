package a_algorithm;

import java.util.Scanner;
import java.util.StringTokenizer;

public class Ex1712 {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		long a, b, c, answer=0L;
		String str = scanner.nextLine();
		StringTokenizer st = new StringTokenizer(str);
		a = Long.parseLong(st.nextToken()); // 고정 비용
		b = Long.parseLong(st.nextToken()); // 가변 비용
		c = Long.parseLong(st.nextToken()); // 
		
		
		if(c-b<=0)
			answer=-1;
		else
			answer = a/(c-b)+1;
		
		System.out.println(answer);
		scanner.close();
	}

}

