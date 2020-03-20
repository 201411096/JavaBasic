package d_array;

import java.util.Scanner;

public class Ex08_avg {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int testCase = scanner.nextInt(); // 테스트케이스 개수 입력
		scanner.nextLine();
		double answer[] = new double[testCase]; // 테스트케이스 개수만큼 답안 배열 설정
		for(int t=0; t<testCase; t++)
		{
			int stdNum= scanner.nextInt(); //테스트케이스 t번째 학생 수 입력
			int score[] = new int [stdNum]; //테스트케이스 t번째 학생 수만큼 t번쨰 점수 배열 설정
			int sum=0;
			int cnt=0; // 평균보다 높은 학생 수를 세는 변수
			for(int i=0; i<score.length; i++) //학생 수만큼 점수 입력 받기
			{
				score[i]=scanner.nextInt();
				sum+=score[i];
			}
			for(int i=0; i<score.length; i++)
			{
				if(score[i]>(sum/stdNum)) //점수가 평균보다 높을 때마다 증가
					cnt++;
			}
			answer[t]= (double)cnt/stdNum*100; //평균보다 높은 학생의 비율을 답안 배열에 저장
			
		}
		
		for(int i=0; i<answer.length; i++)
		{
			System.out.printf("%.3f", answer[i]);
			System.out.print("%");
			System.out.println();
		}
	}
}

