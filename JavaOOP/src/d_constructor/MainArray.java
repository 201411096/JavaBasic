package d_constructor;

import java.util.Scanner;

public class MainArray {
	/**
	 * 	3명의 학생 정보를 입력받아 각 학생별 총점 평균을 구한다면
	 */
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		Student s [] = new Student[3];
		int sum_score[]= new int[3];
		for(int i=0; i<s.length; i++)
		{
			s[i] = new Student();
			System.out.println((i+1) + "학생의 이름, 국어, 영어, 수학 점수를 입력");
			s[i].setName(scanner.next());
			s[i].setKor(scanner.nextInt());
			s[i].setEng(scanner.nextInt());
			s[i].setMath(scanner.nextInt());
		}
		for(int i=0; i<s.length; i++)
		{
			System.out.println((i+1) + "번쨰 학생의 총점과 평균");
			System.out.println(s[i].calTotal());
			System.out.println(s[i].calAverage());
		}
		for(int i=0; i<s.length; i++)
		{
			sum_score[0]+=s[i].getKor();
			sum_score[1]+=s[i].getEng();
			sum_score[2]+=s[i].getMath();
		}
		System.out.println("국어 과목의 총점은 " + sum_score[0]);
		System.out.println("영어 과목의 총점은 " + sum_score[1]);
		System.out.println("수학 과목의 총점은 " + sum_score[2]);
		// 추가적으로 각 과목별 총점을 구한다면?
	}
}


/*
 * 1. 1
 * 2. 2
 * 3. 4 -> 2,4 // 매개변수의 순서가 달라도 메소드 오버로딩이 가능
 * 4. MyClass my = new MyClass();
 * 5. 변수의 자료형과 개수가 같아서 메소드 오버로딩이 되지 않음
 * 6. this("이름없음")을 첫줄으로
*/
