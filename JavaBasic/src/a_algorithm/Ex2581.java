package a_algorithm;

import java.util.Scanner;

public class Ex2581 {
	static boolean isSosu(int n) {
		boolean isSosu = true;
		int div = n-1;
		if(n<=1)
			return false;
		else
		{
			while(div>1)
			{
				if(n%div==0)
				{
					isSosu=false;
					break;
				}
				div--;
			}
		}
		

		return isSosu;
	}
	
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int m = scanner.nextInt();
		int n = scanner.nextInt();
		int min = 0;
		int sum=0;
		
		for(int i=m; i<=n; i++) // m부터 n까지 확인
		{
			boolean b =isSosu(i);
			if(b==true)
			{
				sum+=i;
				if(min==0)
					min=i;
			}
		}
		if(min==0) //최솟값이 0이면 -> 소수가 없으면
		{
			System.out.println(-1); // -1 출력
		}else { //아니면 합, 최소 출력
			System.out.println(sum); 
			System.out.println(min);
		}
		
	}
}
