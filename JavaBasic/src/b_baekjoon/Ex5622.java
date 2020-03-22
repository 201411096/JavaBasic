package b_baekjoon;

import java.util.Scanner;

public class Ex5622 {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		String str = scanner.nextLine();
		int answer=0; // 걸리는 시간
		int alphabetToNumber[] = {2, 2, 2, 3, 3, 3, 4, 4, 4, 5, 5, 5, 6, 6, 6, 7, 7, 7, 7, 8, 8, 8, 9, 9, 9, 9};
		String stringArray [] = str.split("");
		int num[] = new int[str.length()]; //받은 문자열을 숫자 전화번호로 변환한 값을 담는 배열
		for(int i=0; i<num.length; i++) //받은 문자열을 숫자 전화번호로 변환하는 과정
		{
			num[i]=alphabetToNumber[ ((int)(stringArray[i].charAt(0)-'A')) ];
		}
		for(int i=0; i<num.length; i++)
		{
			answer+=(num[i]+1);
		}
		System.out.println(answer);
	}
}
