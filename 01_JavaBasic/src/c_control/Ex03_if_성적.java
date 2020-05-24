package c_control;

import java.util.Scanner;

public class Ex03_if_성적 {
	public static void main(String [] args)
	{
		//국, 영, 수 점수를 입력 받아서 총점, 평균 출력
		//평균에 따라서 학점 A~F까지 출력
		//if문을 이용
		int kor, eng, math;
		char score;
		Scanner scanner = new Scanner(System.in);
		
		System.out.print("국어 점수->");
		kor = scanner.nextInt();
		System.out.print("영어 점수->");
		eng = scanner.nextInt();
		System.out.print("수학 점수->");
		math=scanner.nextInt();
		
		int total = kor+eng+math;
		System.out.println("총점:" + total);
		double avg = (double)total/3;
		System.out.println("평균:" + avg);
		
		if(avg>=90)
			score='A';
		else if(avg>=80)
			score='B';
		else if(avg>=70)
			score='C';
		else if(avg>=60)
			score='D';
		else
			score='F';
		System.out.println("학점: " + score);
		
	}
}
