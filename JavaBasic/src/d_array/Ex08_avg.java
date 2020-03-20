package d_array;

import java.util.Scanner;

public class Ex08_avg {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int testCase = scanner.nextInt();
		scanner.nextLine();
		double answer[] = new double[testCase];
		for(int t=0; t<testCase; t++)
		{
			int stdNum= scanner.nextInt();
			int score[] = new int [stdNum];
			int sum=0;
			int cnt=0;
			for(int i=0; i<score.length; i++) //학생 수만큼 점수 입력 받기
			{
				score[i]=scanner.nextInt();
				sum+=score[i];
			}
			for(int i=0; i<score.length; i++)
			{
				if(score[i]>(sum/stdNum))
					cnt++;
			}
			answer[t]= (double)cnt/stdNum*100;
			
		}
		
		for(int i=0; i<answer.length; i++)
		{
//			System.out.println(Math.round(answer[i]*1000)/1000.0 + "%"); //소수점 3자리까지 표현 1
			System.out.printf("%.3f", answer[i]);
			System.out.print("%");
			System.out.println();
		}
	}
}

