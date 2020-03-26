package b_baekjoon;

import java.util.Scanner;

public class Ex9020 {
	static int [] primeNum(int n) // n보다 작은 소수를 찾아서 배열로 반환 // 에라토스테네스의 체 이요
	{
		boolean isPrime[] = new boolean[n+1];
		int primeNum = 2; // 체로 사용할 시작 소수
		boolean findNextPrimeNum; // 다음 수를 찾았는지
		int output_num =0; // output으로 내보낼 배열의 크기를 할당하는데 사용
		for(int i=2; i<isPrime.length; i++) // 2부터 n까지 true(소수)로 초기화
		{
			isPrime[i]=true;
		}
		while(true)
		{
			findNextPrimeNum = false; 
			for(int i=2; ; i++)
			{
				int multiple =i*primeNum; // 소수 * 배수가 n보다 커지면 탈출
				if(multiple>n)
					break;
				isPrime[multiple]=false; // 소수*배수의 값을 false로 변경
			}
			
			for(int i=primeNum+1; i<n; i++)
			{
				if(isPrime[i]==true)
				{
					findNextPrimeNum=true; 
					primeNum=i;
					break;
				}
			}
			if(findNextPrimeNum==false) //다음 수가 없으면 반복문 탈출
				break;
		}
		
		for(int i=1; i<isPrime.length; i++) // 소수 갯수 구하기
		{
			if(isPrime[i]==true)
			{
				output_num++;
			}
		}
		int output [] = new int[output_num];
		output_num=0; // 갯수 셀 때 사용했던 변수를 재사용
		for(int i=1; i<isPrime.length; i++) // 소수들을 return하는 배열에 순서대로 입력
		{
			if(isPrime[i]==true)
			{
				output[output_num++]=i;
			}
		}
		return output;
		
	}
	static String goldbach(int [] array, int n) { // n보다 작은 소수가 담긴 배열 변수와 입력했던 숫자
		int num_idx1=-1, num_idx2=-1;
		int min_sub = -1;
		for(int i=0; i<array.length; i++)
		{
			for(int j=i; j<array.length; j++)
			{
				if(array[i] + array[j]==n) // 합이 같다면
				{
					if(min_sub==-1)
					{
						num_idx1=i;
						num_idx2=j;
						min_sub = array[j]-array[i];
					}else {
						if(min_sub>array[j]-array[i])
						{
							num_idx1=i;
							num_idx2=j;
							min_sub = array[j]-array[i];
						}
					}
				}
			}
		}
		return Integer.toString(array[num_idx1]) + " " + Integer.toString(array[num_idx2]);
	}
	
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int testCase = scanner.nextInt();
		String answer [] = new String[testCase];
		scanner.nextLine();
		for(int i=0; i<testCase; i++)
		{
			int n = scanner.nextInt();
			scanner.nextLine();
			int output [] = primeNum(n);
			answer[i] = goldbach(output, n);
		}
		for(int i=0; i<testCase; i++)
			System.out.println(answer[i]);
	}
}
