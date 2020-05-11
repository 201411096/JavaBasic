package a_algorithm;

import java.util.Scanner;
import java.util.StringTokenizer;


public class Ex2941 {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		StringBuffer str = new StringBuffer(scanner.nextLine());
		String croatia [] = {"c=", "c-", "dz=", "d-", "lj", "nj", "s=", "z="};
		int croatia_cnt [] = {0, 0, 0, 0, 0, 0, 0, 0}; // 크로아티아 문자 개수를 저장하는 배열
		int str_length = str.length();
		
		int answer=0;
		
		for(int i=0; i<croatia.length; i++) //크로아티아 문자를 찾을때마다 배열값 증가
		{
			for(int j=0; j<str.length()-croatia[i].length()+1; j++)
			{
				if(str.substring(j, j+croatia[i].length()).equals(croatia[i]) )
				{
					croatia_cnt[i]++;
				}
			}
		}
		croatia_cnt[7]-=croatia_cnt[2]; //"dz="가 나올때마다 "z="가 중복된것을 제거 //대다수 코드들은 문자를 찾았을경우 *, 공백등으로 치환하는식으로 해결
		answer=str_length;
		for(int i=0; i<croatia_cnt.length; i++)
		{
			answer-=croatia_cnt[i]*croatia[i].length();
			answer+=croatia_cnt[i];
		}
		
		System.out.println(answer);
		
	}
}
