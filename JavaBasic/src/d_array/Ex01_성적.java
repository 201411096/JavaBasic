package d_array;

import java.util.Scanner;

public class Ex01_성적 {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
//		int [] kor;
//		kor = new int[5];
		int [] kor = new int[5];
		int sum=0;
//		kor[0]=90;
//		kor[1]=88;
//		kor[2]=89;
		kor[3]=70;
		kor[4]=77;
		
		//배열 초기화
		int [] kor2 = new int[] {90,88,77,60,50,40}; // 원래 문법
		int [] kor3 = {90,88,77,60,50,40}; // 이런식으로 생략 가능
		for(int i=0; i<kor2.length; i++)
		{
			System.out.println(kor2[i]);
			sum+=kor2[i];
		}
		
		System.out.println("sum: " + sum);
		
	}
}
