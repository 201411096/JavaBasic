package b_baekjoon;

import java.util.Scanner;

public class Ex1011 {
	
	static int func(int length) {
		int speed=1; // 현재 속도
		int number=0; // 작동 횟수
		int temp_length=length; // 현재 거리
		number=(int)Math.sqrt(length); // 작동 횟수를 처음 거리의 제곱근 만큼으로 초기화
		speed=number; // 최고 속도를 처음 거리의 제곱근만큼으로 초기화
		int speed_down_criteria=0; // 속도를 줄이는 기준 거리
		for(int i=1; i<=number; i++) // 최고 속도까지 올릴 수 있는 거리의 기준
		{
			speed_down_criteria+=i;
		}
		temp_length-=speed_down_criteria; // 최고 속도를 낼 때까지의 거리를 뺌
		while(temp_length>0) {			
			if(temp_length<speed_down_criteria) // 현재 거리가 최고 속도의 기준 거리보다 작다면 속도 감소
			{
				speed_down_criteria-=speed; // 속도를 감소 시키기 전 기준 거리 감소
				speed--; // 감속
				temp_length-=speed; //감소한 속도만큼 거리 감소
				number++; // 횟수 증가
			}else
			{
				temp_length-=speed; // 속도만큼 거리 감소
				number++; // 횟수 증가
			}
		}
		

		
		return number;
	}
	
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int testCase = scanner.nextInt();
		int answer [] = new int [testCase];
		for(int i=0; i<testCase; i++)
		{
			int x = scanner.nextInt(); // 시작 지점
			int y = scanner.nextInt(); // 끝 지점
			int length = y-x; // 길이
			answer[i] = func(length); 
			
		}
		for(int i=0; i<testCase; i++)
		{
			System.out.println(answer[i]);
		}

	}
}
/* 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16
 * 1 2 3 3 4 4 5 5 5                   7 
 * 1 1 1 2 2 2 2 2 3 3  3  3  3  3  3  4
 */



/*	길이		시동횟수		최고속도
 * 	1		1			1		ex) 1
 * 	2		2			1		ex) 1 1
 * 	3		3			1		ex) 1 1 1
 * 	4		3			2		ex) 1 2 1
 * 	5		4			2		ex) 1 2 1 1
 * 	6		4			2		ex) 1 2 2 1
 * 	7		5			2		ex) 1 2 2 1 1
 * 	8		5			2		ex) 1 2 2 2 1
 *  9		5			3		ex) 1 2 3 2 1
 *  10		6			3		ex) 1 2 3 2 1 1
 *  11		6			3		ex) 1 2 3 2 2 1
 *  12		6			3		ex) 1 2 3 3 2 1
 *  13		7			3		ex) 1 2 3 3 2 1 1
 *  14		7			3		ex) 1 2 3 3 2 2 1
 *  15		7			3		ex) 1 2 3 3 3 2 1
 *  16		7			4		ex) 1 2 3 4 3 2 1
 *  17
 *  18
 */










