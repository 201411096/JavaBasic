package a_algorithm;

import java.util.Scanner;
import java.util.StringTokenizer;

public class Ex7568  {
	
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int num = scanner.nextInt();
		scanner.nextLine();
		int kg [] = new int[num];
		int height [] = new int [num];
		int rank [] = new int [num];
		for(int i=0; i<num; i++)  // 입력
		{
			StringTokenizer st = new StringTokenizer(scanner.nextLine());
			kg[i]= Integer.parseInt(st.nextToken());
			height[i]= Integer.parseInt(st.nextToken());
			rank[i]=1;
		}
		for(int i=0; i<num; i++)
		{
			for(int j=0; j<num; j++)
			{
				if(kg[i]<kg[j] &&height[i]<height[j] && i!=j) // 등호 실수해서 계속 막혔었음
					rank[i]++;
			}
		}
		for(int i=0; i<num; i++)
			System.out.print(rank[i] + " ");
	}
}
