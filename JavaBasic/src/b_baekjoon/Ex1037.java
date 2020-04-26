package b_baekjoon;

import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class Ex1037{
	public static void main(String[] args) throws IOException{	
		Scanner scanner = new Scanner(System.in);
		
		int numCnt = scanner.nextInt();
		int numArray [] = new int[numCnt];
		long answer=0;
		scanner.nextLine();
		
		for(int i=0; i<numCnt; i++)
		{
			numArray[i] = scanner.nextInt();
		}
		Arrays.sort(numArray); // 문제에서 약수들을 랜덤으로 주는 걸 몰라서 여러번 틀렸었음
		if(numCnt%2==0)
		{
			answer=numArray[0]*numArray[numArray.length-1];
		}else {
			answer=numArray[numArray.length/2]*numArray[numArray.length/2];
		}
		System.out.println(answer);
	}
}


