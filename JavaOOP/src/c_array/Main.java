package c_array;

import java.util.Scanner;

public class Main {

	/**
	 * 			배열을 이용해서 3명의 국영수 점수를 입력받아 총점과 평균을 구한다
	 */
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		
		Student [] s = new Student[3];		// 학생 수 3은 배열의 갯수를 의미한다.
		
		for(int i=0; i<s.length; i++)
		{
			System.out.println((i+1)+ "번쨰 학생 이름 입력");
			s[i] = new Student();
			s[i].setName(scanner.next());
			System.out.println((i+1)+"번쨰 학생 국어 영어 수학 점수 입력");
			s[i].setKor(scanner.nextInt());
			s[i].setEng(scanner.nextInt());
			s[i].setMath(scanner.nextInt());
		}
		for(int i=0; i<s.length; i++)
		{
			System.out.println(s[i].getName());
			System.out.println(s[i].calTotal());
			System.out.println(s[i].calAverage());
		}
		
		
		
	}

}
