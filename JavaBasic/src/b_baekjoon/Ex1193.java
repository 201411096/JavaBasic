package b_baekjoon;

import java.util.Scanner;

public class Ex1193 {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int n = scanner.nextInt();
		int n_range_start=1, n_range_end=1;
		int step = 1; //몇번째 범위에 속해있는지
		int numInStep =0; //범위에서 몇번째인지
		int bunmo, bunja;
		
		while( !(n>=n_range_start && n<=n_range_end) )
		{
			step++;
			n_range_start = n_range_end+1;
			n_range_end = n_range_end+step;
		}
		numInStep = n-n_range_start;
		if(step%2==1)
		{
			bunmo=1;
			bunja=step;
			while(numInStep!=0)
			{
				bunmo++;
				bunja--;
				numInStep--;
			}
		}else {
			bunmo=step;
			bunja=1;
			while(numInStep!=0)
			{
				bunmo--;
				bunja++;
				numInStep--;
			}
		}
		System.out.println(bunja +"/" + bunmo);
		
	}
}

