package a_algorithm;

import java.util.Scanner;
import java.util.StringTokenizer;

public class Ex2869 {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		StringTokenizer st = new StringTokenizer(scanner.nextLine());
		int a = Integer.parseInt(st.nextToken());
		int b = Integer.parseInt(st.nextToken());
		int v = Integer.parseInt(st.nextToken());
		int answer=0;
		if((v-a)%(a-b)==0) // 딱 떨어질 경우
			answer = (v-a)/(a-b)+1; // 기본식
		else
			answer = (v-a)/(a-b)+2; // 기본식+1
		System.out.println(answer);
		
	}
}
