package d_array;

import java.util.Scanner;
import java.util.StringTokenizer;

public class Ex05_baseball {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		
		int baseball [] = new int[3];
		int answer [] = new int[3];
		int s=0, b=0; //strike ball 갯수
		for(int i=0; i<baseball.length; i++) // 랜덤 숫자 3개 생성
		{
			baseball[i]=(int)(Math.random()*10);
		}
		
		System.out.print("컴퓨터가 생성한 랜덤값 ");
		for(int i=0; i<baseball.length; i++) //컴퓨터가 생성한 랜덤 숫자 3개 확인
		{
			System.out.print(baseball[i]+ " ");
		}
		
		System.out.println();
		
//		while(s!=3) //3strike나올때까지 반복
//		{
//			String str;
//			System.out.println("숫자 3개 입력 (공백으로 구분)");
//			str = scanner.nextLine();
//			StringTokenizer st = new StringTokenizer(str);
//			int cnt=0;
//			while(st.hasMoreTokens())
//			{
//				
//				answer[cnt++]=Integer.parseInt(st.nextToken());
//			}
//			
//			b=0; s=0;
//			for(int i=0; i<baseball.length; i++)
//			{
//				for(int j=0; j<answer.length; j++)
//				{
//					if(baseball[i] == answer[j] && i==j)
//						s++;
//					else if(baseball[i] == answer[j]) {
//						b++;
//					}
//				}
//			}
//			System.out.println(b+ " ball ");
//			System.out.println( s + " strike");
//		}
		HERE:
		for(int num=0; num<5; num++) //기회 5번
		{
			String str;
			System.out.println("숫자 3개 입력 (공백으로 구분)");
			str = scanner.nextLine();
			StringTokenizer st = new StringTokenizer(str);
			int cnt=0;
			while(st.hasMoreTokens())
			{
				
				answer[cnt++]=Integer.parseInt(st.nextToken());
			}
			
			b=0; s=0;
			for(int i=0; i<baseball.length; i++)
			{
				for(int j=0; j<answer.length; j++)
				{
					if(baseball[i] == answer[j] && i==j)
						s++;
					else if(baseball[i] == answer[j]) {
						b++;
					}
				}
			}
			System.out.println(b+ " ball ");
			System.out.println( s + " strike");
			if(s==3)
				break HERE;
		}
		System.out.println("프로그램 종료");
		
		
	}
}
