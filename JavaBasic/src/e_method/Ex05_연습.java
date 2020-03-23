package e_method;

import java.util.Scanner;
import java.util.StringTokenizer;

public class Ex05_연습 {
	/*
	 * 두 정수와 알파벳 문자 하나를 입력 받기 ex) 3 4 F
	 */
	static char [][] input() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("두 정수와 알파벳 문자 하나를 입력->");
		String str = scanner.nextLine();
		StringTokenizer st = new StringTokenizer(str);
		int first = Integer.parseInt(st.nextToken());
		int second = Integer.parseInt(st.nextToken());
		char alpha = st.nextToken().charAt(0);
		char ch[][] = makeSquare(first, second, alpha);
		
		return ch;
	}
	/*
	 * 입력받은 첫 번째 정수만큼의 행과 두번째 정수만큼의 문자 배열을 만드ㅡㄹ어
	 * 입력받은 문자로 시작하는 알파벳을 저장하기
	 */
	static char [][] makeSquare(int n1, int n2, char ch) {
		char chArray[][] = new char[n1][n2];
		for(int i=0; i<chArray.length; i++)
		{
			for(int j=0; j<chArray[i].length; j++)
			{
				chArray[i][j]=(char)(ch++);
			}
		}
		return chArray;
	}
	static void output(char[][] chArray) {
		for(int i=0; i<chArray.length; i++)
		{
			for(int j=0; j<chArray[i].length; j++)
			{
				System.out.print(chArray[i][j] + " ");
			}
			System.out.println();
		}
	}
	
	
	public static void main(String[] args) {
		char ch[][] = input();
		output(ch);
		
	}
}
