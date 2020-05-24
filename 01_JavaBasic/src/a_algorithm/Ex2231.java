package a_algorithm;

import java.util.Scanner;

public class Ex2231  {
	static int findAnswer(int n) {
		int temp_sum;
		int temp_num;
		for(int i=1; i<1000000; i++)
		{
			temp_sum=findNumInSum(i);
			temp_num=i;
			if(temp_sum+temp_num==n)
			{
				return i;
			}
		}
		return 0;
	}
	static int findNumInSum(int n) {
		int temp_num = n;
		int sum = 0;
		int cnt=0;
		
		while(temp_num!=0)
		{
			temp_num/=10;
			cnt++;
		}
		int numArr[] = new int[cnt];
		temp_num=n;
		for(int i=0; i<numArr.length; i++)
		{
			numArr[numArr.length-i-1]=temp_num%10;
			temp_num/=10;
		}
		for(int i=0; i<numArr.length; i++)
		{
			sum+=numArr[i];
		}
		return sum;
	}
	
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int number = scanner.nextInt();
		int numInSum = findNumInSum(number);
		int answer = findAnswer(number);
		System.out.println(answer);

	}
}
