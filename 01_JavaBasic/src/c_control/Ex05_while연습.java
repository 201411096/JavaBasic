package c_control;

import java.util.Scanner;
import java.util.StringTokenizer;

public class Ex05_while연습 {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		String str ="";
		int oddNum=0, evenNum=0;
		str = scanner.nextLine();
		StringTokenizer st = new StringTokenizer(str);
		while(st.hasMoreTokens())
		{
			int n= Integer.parseInt(st.nextToken());
			if(n%2==0)
				evenNum++;
			else
				oddNum++;
		}
		System.out.println("짝수 : " + evenNum);
		System.out.println("홀수 : " + oddNum);
	}
}
