package a_algorithm;

import java.util.Scanner;
import java.util.StringTokenizer;

public class Ex2839 {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int n5, n3=0, sum; //5kg 설탕 개수, 3kg 설탕 개수, 처음에 받는 총 무게
		sum=scanner.nextInt();
		n5=sum/5;
		while(n5>=0)
		{
			int temp = sum- n5*5;
			if(temp%3==0)
			{
				n3=temp/3;
				break;
			}
			else {
				n5--;
			}
		}
		if(n5==-1)
			System.out.println("-1");
		else
			System.out.println(n5+n3);
	}

}

