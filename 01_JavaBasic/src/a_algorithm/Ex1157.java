package a_algorithm;

import java.util.Scanner;

public class Ex1157 {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		String str = scanner.nextLine();
		int alphabet_cnt [] = new int [26];
		int max_idx=0;
		boolean duplicant=false;
		for(int i=0; i<alphabet_cnt.length; i++) //알파벳 당 개수를 채우는 배열을 전부 0으로 초기화
		{
			alphabet_cnt[i]=0;
		}
		
		for(int i=0; i<str.length(); i++) //알파벳을 받으면 그 순서에 맞춰서 배열 숫자 증가 
		{
			int sequence =0; 
			if(str.charAt(i)>='A' && str.charAt(i)<='Z')
				sequence=str.charAt(i)-'A';
			else if(str.charAt(i)>='a' && str.charAt(i)<='z')
				sequence=str.charAt(i)-'a';
			alphabet_cnt[sequence]++;
		}
		
		for(int i=1; i<alphabet_cnt.length; i++) //i=0 부터 시작하면 A가 가장 많을 경우 ?로 나오는 오류 발생
		{
			if(alphabet_cnt[i]>alphabet_cnt[max_idx])
			{
				duplicant=false;
				max_idx=i;
			}else if(alphabet_cnt[i]==alphabet_cnt[max_idx])
			{
				duplicant=true;
			}
		}
		if(duplicant==true)
			System.out.println("?");
		else
			System.out.println((char)('A'+max_idx));
	}
}

