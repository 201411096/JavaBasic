package b_baekjoon;

import java.util.Scanner;

public class Ex11068 {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int testCase = scanner.nextInt();
		int testNum [] = new int[testCase];
		int answer [] = new int[testCase];
		for(int i=0; i<testCase; i++) // 테스트케이스 수만큼 입력 받음
		{
			testNum[i]=scanner.nextInt();
			answer[i]=0;
		}
		for(int i=0; i<testCase; i++)
		{
			for(int b=2; b<=64; b++) //b 진법의 범위만큼 회문 여부 확인
			{
				int testNum_digit = 0;
				int temp=testNum[i]; // 자릿수를 확인하기 위해서 저장
				while(temp!=0)
				{
					temp/=b;
					testNum_digit++; // i번째 숫자의 b진법에서의 자릿수를 구함
				}
				int testNum_b[] = new int[testNum_digit]; //b진법에서의 각 자릿수만큼 할당
				int temp2=testNum[i]; //각 자리수 저장하는데 필요한 계산에 사용
				for(int j=0; j<testNum_digit; j++)
				{
					testNum_b[j]= temp2%b;
					temp2/=b;
				}
				
				boolean is_palindrome=true;
				for(int j=0; j<testNum_digit/2; j++) // i번째 숫자의 b진법에서의 자릿수만큼 반복
				{
					if(testNum_b[j]!=testNum_b[testNum_digit-j-1]) //j 번쨰와 자릿수-j 번째가 다를 경우 false 로 변경
						is_palindrome=false;
				}
				if(is_palindrome == true)
				{
					answer[i]=1;
					break;
				}
			}
		}
		for(int i=0; i<answer.length; i++)
		{
			System.out.printf("%d\n",answer[i]);
		}
	}
}
