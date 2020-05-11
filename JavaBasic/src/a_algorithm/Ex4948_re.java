package a_algorithm;

import java.util.Scanner;

public class Ex4948_re{ //에라토스테네스 체 를 코드 검색 이후 수정
	static int primeNum(int n) {
		boolean isPrimeNum [] = new boolean [2*n+1];
		boolean findNextNum;
		int primeNum = 2;
		int answer=0;
		for(int i=2; i<isPrimeNum.length; i++)
			isPrimeNum[i]=true;
		
		while(true)
		{
			findNextNum = false;
			for(int i=2; ; i++)
			{
				int multiple = i*primeNum;
				if(multiple>2*n)
					break;
				isPrimeNum[multiple]=false;
			}
			for(int i = primeNum+1; i<2*n; i++)
			{
				if(isPrimeNum[i]==true)
				{
					findNextNum=true;
					primeNum=i;
					break;
				}
			}
			if(findNextNum==false)
				break;
		}
		for(int i=n+1; i<isPrimeNum.length; i++)
		{
			if(isPrimeNum[i]==true)
				answer++;
		}
		return answer;
	}
	
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int n = 1;
		int answer [] = new int[1000000];
		int num = 0;
		while(n!=0)
		{
			n=scanner.nextInt();
			answer[num++]=primeNum(n);
		}
		
		for(int i=0; i<num; i++)
		{
			if(answer[i]!=0)
				System.out.println(answer[i]);
		}
			
	}
}
