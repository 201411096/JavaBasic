package c_control;

import java.util.Scanner;

public class Ex05_while연습2 { //369게임
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int n = scanner.nextInt(); // 출력범위 입력
		int num=1;
		while(n>0) 
		{
			int cnt=0, temp=num;
			while(true)
			{
				if(temp%10==3 || temp%10==6 || temp%10==9)
				{
					cnt++;
				}
				if(temp<10) break; //숫자가 10보다 작아지면 반복문을 벗어남
				temp/=10;		
			}
						
			if(cnt>0) // 369 포함된 수만큼 짝 출력
			{
				while(cnt>0)
				{
					System.out.print("짝");
					cnt--; //출력한 '짝'의 개수만큼 cnt감소
				}
				System.out.println();
			}
			else // 369가 포함되지 않으면 그냥 숫자 출력
			{
				System.out.println(num);
			}
			num++;
			n--;
		}
	}
}
