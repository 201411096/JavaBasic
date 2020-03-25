package b_baekjoon;

import java.util.Scanner;

public class Ex1929_re {		// 코드 검색 이후 수정
	static void primeNum(int m,int n) {
		boolean isPrimeNum [] = new boolean[n+1];
		int primeNum = 2; //소수
		boolean findNextPrimeNum; // 다음 소수가 있는지
		for(int i=2; i<=n; i++) //2부터 n까지 true(소수)로 초기화
		{
			isPrimeNum[i]=true;
		}
		while(true)
		{
			findNextPrimeNum=false;
			for(int i=2; ; i++)
			{
				int multiple = i*primeNum;
				if(multiple>n)
					break;
				isPrimeNum[multiple]=false;
			}
			
			for(int i=primeNum+1; i<n; i++)
			{
				if(isPrimeNum[i]==true) //소수라면
				{
					findNextPrimeNum=true; //다음 소수를 찾음
					primeNum=i;			   // 다음 소수 변경
					break;
				}
			}
			if(findNextPrimeNum == false) // 다음 소수가 없다면..
				break;
		}
		
		for(int i=m; i<=n; i++) //i가 소수라면(true) 출력
		{
			if(isPrimeNum[i]==true)
				System.out.println(i);
		}
		
	}
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int m = scanner.nextInt();
		int n = scanner.nextInt();
		primeNum(m, n);
	}
}

//에라토스테네스의 체
//static void primeNum(int n) {
//	boolean isPrimeNum [] = new boolean[n+1];
//	int primeNum = 2; //소수
//	boolean findNextPrimeNum; // 다음 소수가 있는지
//	for(int i=2; i<=n; i++) //2부터 n까지 true(소수)로 초기화
//	{
//		isPrimeNum[i]=true;
//	}
//	while(true)
//	{
//		findNextPrimeNum=false;
//		for(int i=2; ; i++)
//		{
//			int multiple = i*primeNum;
//			if(multiple>n)
//				break;
//			isPrimeNum[multiple]=false;
//		}
//		
//		for(int i=primeNum+1; i<n; i++)
//		{
//			if(isPrimeNum[i]==true) //소수라면
//			{
//				findNextPrimeNum=true; //다음 소수를 찾음
//				primeNum=i;			   // 다음 소수 변경
//				break;
//			}
//		}
//		if(findNextPrimeNum == false) // 다음 소수가 없다면..
//			break;
//	}
//	
//	for(int i=1; i<=n; i++) //i가 소수라면(true) 출력
//	{
//		if(isPrimeNum[i]==true)
//			System.out.println(i);
//	}
//	
//}
