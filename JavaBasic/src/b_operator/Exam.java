package b_operator;

import java.util.Scanner;

public class Exam {

	public static void main(String[] args) {
		int score=0;
		Scanner scanner = new Scanner(System.in);
		
		System.out.println("점수 입력");
		score=scanner.nextInt();

		if(score<90 && score>80) {
			System.out.println("평균");
		}
		

	}

}
