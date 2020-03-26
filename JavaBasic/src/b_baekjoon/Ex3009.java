package b_baekjoon;

import java.util.Scanner;
import java.util.StringTokenizer;


public class Ex3009 {
	static int findLonelyOne(int array []) { // 3개짜리 배열을 받아서 셋 중에서 하나만 다른 수를 반환
		int answer=-1;
		for(int i=0; i<array.length; i++)
		{	
			answer=array[i];
			for(int j=0; j<array.length; j++)
			{
				if(array[i]==array[j] && i!=j)
					answer=-1;
			}
			if(answer!=-1)
				break;
		}
		return answer;
	}
	
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int x [] = new int [3];
		int y [] = new int [3];
		int answerX =-2, answerY = -2;
		for(int i=0; i<3; i++)
		{
			StringTokenizer st = new StringTokenizer(scanner.nextLine());
			x[i]=Integer.parseInt(st.nextToken());
			y[i]=Integer.parseInt(st.nextToken());
		}
		answerX=findLonelyOne(x);
		answerY=findLonelyOne(y);
		System.out.print(answerX + " ");
		System.out.print(answerY);
	}
}
