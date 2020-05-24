package a_algorithm;

import java.util.Scanner;

public class Ex4948 {
	static int primeNum(int n)
	{
		boolean isPrimeNum [] = new boolean [2*n+1];
		int startNum = n+1;
		int endNum = 2 * n;
		int answer=0;
		if(n<1)
			return 0;
		else if(n==1)
			return 1;
		
		for(int i=1; i<endNum; i++) // 2 부터 endNum까지 true로 초기화 
		{
			isPrimeNum[i+1]= true;
		}
		for(int i=2; i<=Math.sqrt(endNum); i++)
		{
			if(isPrimeNum[i+1]==false)
				continue;
			for(int j=2; j*(i)<endNum; j++)
				isPrimeNum[j*(i)+1]=false;
		}
		for(int i=startNum; i<endNum; i++) // startNum 부터 endNum까지 소수인지 확인 
		{
			if(isPrimeNum[i+1]==true)
				answer++;
		}
		return answer;
	}
	
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int n;
		int answer[] = new int [1000000];
		int num=0;
		while(true)
		{
			n = scanner.nextInt();
			if(n==0) break;
			answer[num++] = primeNum(n);
		}
		for(int i=0; i<num; i++)
		{
			System.out.println(answer[i]);
		}
	}
}
/*
 * 베르트랑 공준은 임의의 자연수 n에 대하여, n보다 크고, 2n보다 작거나 같은 소수는 적어도 하나 존재한다는 내용을 담고 있다.
 * 
 * 이 명제는 조제프 베르트랑이 1845년에 추측했고, 파프누티 체비쇼프가 1850년에 증명했다.
 * 
 * 예를 들어, 10보다 크고, 20보다 작거나 같은 소수는 4개가 있다. (11, 13, 17, 19) 또, 14보다 크고, 28보다 작거나
 * 같은 소수는 3개가 있다. (17,19, 23)
 * 
 * n이 주어졌을 때, n보다 크고, 2n보다 작거나 같은 소수의 개수를 구하는 프로그램을 작성하시오.
 * 
 * 입력 입력은 여러 개의 테스트 케이스로 이루어져 있다. 각 케이스는 n을 포함하며, 한 줄로 이루어져 있다. (n ≤ 123456)
 * 
 * 입력의 마지막에는 0이 주어진다.
 * 
 * 출력 각 테스트 케이스에 대해서, n보다 크고, 2n보다 작거나 같은 소수의 개수를 출력한다.
 * 
 * 예제 입력 1 1 10 13 100 1000 10000 100000 0 예제 출력 1 1 4 3 21 135 1033 8392
 */
