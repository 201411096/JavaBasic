package b_baekjoon;

import java.util.Scanner;

public class Ex2292 {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int n = scanner.nextInt();
		int startNum=0, endNum=1;
		int answer=1;
		
		while(!(n>=startNum && n<=endNum)) // 범위에 들 때까지..
		{
			startNum=endNum+1;
			endNum=endNum+answer*6;
			answer++;
		}
		System.out.println(answer);
	}
}
//1 7 19 37 61
//0 6 12 18 24
