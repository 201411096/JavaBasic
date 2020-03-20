package c_control;

import java.util.Scanner;

public class Ex03_switch_성적 {
	public static void main(String [] args)
	{
		//국, 영, 수 점수를 입력 받아서 총점, 평균 출력
		//평균에 따라서 학점 A~F까지 출력
		//switch문을 이용
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
		
		switch((int)avg/10) {
		case 10:
		case 9: score='A';
		case 8: score='B';
		case 7: score='C';
		case 6: score='D';
		default: score='F';
		}
		
		System.out.println("학점 :" + score);
	}
}









//
//switch((int)avg/10) {
//case 10:
//case 9:score='A';break;
//case 8:score='B';break;
//case 7:score='C';break;
//case 6:score='D';break;
//default: score='F';break;
//}