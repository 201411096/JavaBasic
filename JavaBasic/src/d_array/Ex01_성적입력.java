package d_array;

import java.util.Scanner;
import java.util.StringTokenizer;

/*
 * 학생 점수를 입력 받아 총점과 평균 출력
 * 
 * 입력 형식 : 80/88/77
 */

public class Ex01_성적입력 {
	
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		String str = new String();
		int score [] = new int[3];
		int sum=0; 
		double avg;
		
		System.out.println("점수 입력 : ex)80/88/77");
		str=scanner.nextLine();
		StringTokenizer st = new StringTokenizer(str, "/");
		int cnt=0;
		while(st.hasMoreTokens())
		{
			
			score[cnt++]= Integer.parseInt(st.nextToken());
		}
		
		for(int i=0; i<score.length; i++)
		{
			sum+=score[i];
		}
		avg=(double)sum/score.length;
		System.out.println("총점 : " + sum);
		System.out.println("총점 : " + avg);
	}
}
