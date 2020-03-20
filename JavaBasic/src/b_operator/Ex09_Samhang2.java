package b_operator;

import java.util.Scanner;

public class Ex09_Samhang2 {

	public static void main(String[] args) {
		//학생 점수를 입력받아 80점 이상이면 "합격" 출력하고 그렇지 않으면 "불합격" 출력
		int score=0;
		Scanner scanner = new Scanner(System.in);
		System.out.println("점수 입력->");
		score = scanner.nextInt();
		System.out.println(score>=80?"합격":"불합격");

	}

}
