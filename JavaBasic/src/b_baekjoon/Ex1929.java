package b_baekjoon;

import java.util.Scanner;

public class Ex1929 { //시간 초과해서 에라토스테네스의 체 참고
	static boolean isSosu(int n){
		boolean b = true;
		int div=n-1;
		if(n<=1)
			return false;
		else {
			while(div>1)
			{
				if(n%div==0)
				{
					b=false;
					break;
				}
				div--;
			}
		}
		return b;
	}	
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int [] number = new int[1000001]; // 기본적으로 0으로 초기화된 배열 (1이면 소수)
		int num = 2; //시작 소수
		int m = scanner.nextInt();
		int n = scanner.nextInt();
		for(int i=m; i<=n; i++) // m부더 n까지 기본적으로 1로 초기화 하지만 i가 1이하일 경우 0으로 초기화
		{
			if(i<=1)
				number[i+1]=0; // 1의 소수여부는 number[2]의 값으로 판별
			else
				number[i+1]=1;
		}
		while(num <Math.sqrt(n)) //시작 소수(2)부터 입력받은 n의 제곱근까지의 소수를 확인 (소수를 찾으면..)
		{
			int multiple_num=2;
			if(isSosu(num))
			{
				while(num*multiple_num<=n) //소수의 배수들을 0으로 변경
				{
					number[num*multiple_num+1]=0;
					multiple_num++;
				}
			}
			num++;
		}
		for(int i=m; i<=n; i++)
		{
			if(number[i+1]==1)
				System.out.println(i);
		}			
	}
}

