package d_array;

import java.util.Scanner;
import java.util.StringTokenizer;

public class Ex08_성적2 {
	public static void main(String[] args) {
		//학생 수를 입력받기
		int stdNum;
		Scanner scanner = new Scanner(System.in);
		System.out.println("학생수->");
		stdNum=scanner.nextInt();
		int score[][] = new int[stdNum][3];
		int sum[] = new int[stdNum]; //학생별 총점
		int sum2[] = new int[score[0].length]; //과목별 총점
		String [] subject = {"국어", "영어", "수학"}; //출력에 사용
		
		scanner.nextLine();

		for(int i=0; i<score.length; i++)
		{
			System.out.println( i +"번째 학생의 성적을 입력 (ex 88/77/66)");
			String str=scanner.nextLine();
			StringTokenizer st = new StringTokenizer(str, "/");
			for(int j=0; j<score[i].length; j++)
			{
				score[i][j] = Integer.parseInt(st.nextToken());
			}

		}

		for(int i=0; i<score.length; i++)
		{
			System.out.print( i+"번째 학생의 성적은 ");
			for(int j=0; j<score[i].length; j++)
				System.out.print(score[i][j] + " ");
			System.out.println();
		}
		System.out.println("---------------------------------------------------");
		for(int i=0; i<score.length; i++) //학생별 총점 평균 계산
		{
			sum[i]=0;
			for(int j=0; j<score[i].length; j++)
			{
				sum[i]+=score[i][j];
			}
		}
		for(int i=0; i<sum.length; i++) // 학생별 총점 평균 출력
		{
			System.out.println(i + "번째 학생의 총점은 " + sum[i] + " 평균은 " + sum[i]/(double)score[i].length);
		}
		System.out.println("---------------------------------------------------");
		for(int i=0; i<sum2.length; i++) //과목별 총점 평균 계산
		{
			sum2[i]=0;
			for(int j=0; j<score.length; j++) //학생 수만큼
			{
				sum2[i]+=score[j][i];
			}
		}
		for(int i=0; i<sum2.length; i++) //과목별 총점 평균 출력
		{
			System.out.println(subject[i] + "과목 총점은 " + sum2[i] + "이고 평균은 " + sum2[i]/(double)sum2.length + "입니다.");
		}
		
		
	}
}
