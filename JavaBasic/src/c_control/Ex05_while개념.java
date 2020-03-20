package c_control;

import java.util.Scanner;
import java.util.StringTokenizer;

public class Ex05_while개념 {
	public static void main(String[] args) {
		//1~10까지 합을 출력
//		int i=1, total=0;
////		for(; i<=10;)
//		while(i<=10)
//		{
//			total+=i;
//			i++;
//		}
//		System.out.println(total);
		Scanner scanner = new Scanner(System.in);
//		System.out.println("숫자를 입력해주세요.");
//		int n=scanner.nextInt();
//
//		int i=1;
//		while(i<=9) {
//			System.out.printf("%d * %d = %d\n", n, i, i*n);
//			i++;
//		}
		
		
		//문장을 입력받아 단아로 나누어 출력
		String str ="";
		System.out.println("점수을 입력해주세요 (99/80/70/90)");
		str=scanner.nextLine();
		StringTokenizer st = new StringTokenizer(str, "/");
		while(st.hasMoreTokens())
		{
			String temp = st.nextToken();
			int n = Integer.parseInt(temp);
			System.out.println(++n);
		}
		
		
			
//			System.out.println(n + " * " + i + " = " + i*n);
			
	}
}
