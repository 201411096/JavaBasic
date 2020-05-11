package a_algorithm;

import java.util.Scanner;
import java.util.StringTokenizer;

public class Ex2798  {
	static void bubbleSort(int [] array) //버블 정렬
	{
		for(int i=0; i<array.length-1; i++)
		{
			for(int j=0; j<array.length-i-1; j++)
			{
				if(array[j]>array[j+1])
				{
					int temp = array[j];
					array[j] = array[j+1];
					array[j+1] = temp;
				}
			}
		}
	}
	static int findAnswer(int [] array, int maxNum) { // 배열과 합의 최댓값을 인자로
		int answer= array[0] + array[1] + array[2];
		for(int idx1=0; idx1<array.length-2; idx1++) //idx들은 카드 개수의 -2만큼의 범위를 가짐
		{
			for(int idx2=idx1+1; idx2<array.length-1; idx2++) //idx2는 idx1+1부터 시작
			{
				for(int idx3=idx2+1; idx3<array.length; idx3++) //idx3는 idx2+1부터 시작
				{
					if(answer<array[idx1]+array[idx2]+array[idx3] && array[idx1]+array[idx2]+array[idx3]<=maxNum)
						answer = array[idx1]+array[idx2]+array[idx3];
				}
			}
		}
		return answer;
	}
	
	
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		StringTokenizer st = new StringTokenizer(scanner.nextLine());
		int cardNum = Integer.parseInt(st.nextToken()); // 배열 크기 할당에 사용
		int maxNum = Integer.parseInt(st.nextToken()); 
		st = new StringTokenizer(scanner.nextLine());
		int nums [] = new int [st.countTokens()];
		for(int i=0; st.hasMoreTokens(); i++)
			nums[i]=Integer.parseInt(st.nextToken());
		bubbleSort(nums);
		System.out.println(findAnswer(nums, maxNum));
	}
}
