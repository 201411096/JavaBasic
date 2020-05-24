package a_algorithm;

import java.util.Scanner;

public class Ex2775 {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int testCase = scanner.nextInt();
		scanner.nextLine();
		int answer [] = new int[testCase];
		for(int m=0; m<testCase; m++)
		{
			int k = scanner.nextInt(); // 층 (0층부터 시작)
			int n = scanner.nextInt(); // 호 (1호부터 시작)
			int array[][]=new int[k+1][n+1]; // 0호까지 있다고 가정하고 생각하고 할당 // 14층 14호일 경우 호뿐만 아니라 층도 15까지 할당해줘야 되는걸 한참동안 인지하지 못했었음
			for(int j=0; j<n+1; j++) // 0층 채우기
			{
				array[0][j]=j;
			}
			for(int i=1; i<array.length; i++)
			{
				for(int j=0; j<array[i].length; j++)
				{
					int sum=0;
					for(int a=0; a<=j; a++) //(a-1)층의 1호부터 b호까지 사람들의 수의 합만큼 사람 계산
					{
						sum+=array[i-1][a];
					}
					array[i][j]=sum;
					
				}
			}
			answer[m]=array[k][n];
		}
		for(int i=0; i<answer.length; i++)
			System.out.println(answer[i] + " ");
	}
}
/*
 *
 * 0 1 2 3 4 5 
 * 0 1 3 6 10 15 
 * 0 1 4 10 20 35 
 * 0 1 5 15 35 70 
 * 0 1 6 21 56 126 
 * 
 * 
 * 
 * 
 * 
 * 1	4	10	20	35
 * 1	3	6	10	15	21	28	36	45
 * 1	2	3	4	5	6	7	8	9
 */