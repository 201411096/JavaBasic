package a_algorithm;

import java.util.Scanner;
import java.util.StringTokenizer;

public class Ex10250 {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int testCase = scanner.nextInt();
		scanner.nextLine();
		int answer [] = new int[testCase];
		
		for(int i=0; i<testCase; i++)
		{
			String str = scanner.nextLine();
			StringTokenizer st = new StringTokenizer(str);
			int h = Integer.parseInt(st.nextToken()); // 높이
			int w = Integer.parseInt(st.nextToken()); // 층마다의 객실
			int n = Integer.parseInt(st.nextToken()); // 몇번쨰 손님인지
			int xx, yy; //xx 천의자리, 백의자리,  yy 십의자리 일의자리
			if(n%h==0) // 오류를 못찾아서 외부코드 참고 후 수정 (%는 딱 나누어 떨어지는 순간에 0이되버리면서 문제 발생 /는 딱 떨어지기전까지 하나씩 모자람)
			{
				xx=h;
				yy=n/h;
			}else {
				xx = n%h;
				yy=n/h+1;
			}
			answer[i] = xx*100 + yy;
		}
		for(int i=0; i<testCase; i++)
			System.out.println(answer[i]);
	}
}
