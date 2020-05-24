package a_algorithm;

import java.util.Scanner;
import java.util.StringTokenizer;

public class Ex1002 {
	static double calDistance(int x1, int y1, int x2, int y2)
	{
		return Math.sqrt(Math.pow(Math.abs(x1-x2), 2)+Math.pow(Math.abs(y1-y2), 2));
	}
	static int countPoint(double distance, int r1, int r2)
	{
		if(distance == 0 && r1==r2) // 원이 일치
			return -1;
		else if(distance==r1+r2) // 외접
			return 1;
		else if(distance==Math.abs(r1-r2)) // 내접
			return 1;
		else if(distance>r1+r2) // 멀리 떨어진 경우 (외접보다 거리가 먼 경우)
			return 0;
		else if(distance <Math.abs(r2-r1)) // 내접보다 (거리가 가까운 경우)
			return 0;
		else if(distance <r1+r2 && distance>Math.abs(r1-r2)) // 두 점 (외접과 내접 사이)
			return 2;
		
		return -2; //오류

	}
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int testCase = scanner.nextInt();
		int answer [] = new int[testCase];
		scanner.nextLine();
		for(int i=0; i<testCase; i++)
		{
			StringTokenizer st = new StringTokenizer(scanner.nextLine());
			int x1 = Integer.parseInt(st.nextToken());
			int y1 = Integer.parseInt(st.nextToken());
			int r1 = Integer.parseInt(st.nextToken());
			int x2 = Integer.parseInt(st.nextToken());
			int y2 = Integer.parseInt(st.nextToken());
			int r2 = Integer.parseInt(st.nextToken());
			double distance = calDistance(x1, y1, x2, y2);
			answer[i] = countPoint(distance, r1, r2);
		}
		for(int i=0; i<testCase; i++)
			System.out.println(answer[i]);
	}
}

