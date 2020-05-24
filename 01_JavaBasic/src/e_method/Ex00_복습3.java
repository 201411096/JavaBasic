package e_method;

import java.util.Scanner;

public class Ex00_복습3 {
	static int sum=0;
	static double avg=0.0;
	public static void main(String[] args) {
		int [] score =input();
		calScore(score);
		output();
	}
	//총점 평균을 출력
	static void output() {
		System.out.println("총점 : " + sum);
		System.out.println("평균 : " + avg);
	}
	
	//입력받은 국영수 점수로 총점, 평균을 구하기 + 출력
	static void calScore(int [] score) {
		for(int i=0; i<score.length; i++)
			sum+=score[i];
		avg = (double)sum/score.length;
	}
	//국 영 수 점수를 입력 받기
	static int [] input() {
		Scanner scanner = new Scanner(System.in);
		int score[] = new int[3];
		for(int i=0; i<score.length; i++)
			score[i]=scanner.nextInt();
		return score;
	}
}