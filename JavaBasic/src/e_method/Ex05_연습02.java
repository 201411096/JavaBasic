package e_method;

import java.util.Scanner;
import java.util.StringTokenizer;

public class Ex05_연습02 {
	static int [] func(int n, int [] votes)
	{
		int cnt[] = new int[n]; // 후보별 득표수를 저장하는 배열
		for(int i=0; i<votes.length; i++) // 후보마다 득표수 count
			cnt[votes[i]-1]++;
		int answer_length=0;
		int answer_idx=0;
		int max_idx=0;
		
		for(int i=1; i<cnt.length; i++)
		{
			if(cnt[max_idx]<cnt[i])
				max_idx=i;
		}
		for(int i=0; i<cnt.length; i++)
		{
			if(cnt[max_idx]==cnt[i])
			{
				answer_length++;
			}
		}
		int answer [] = new int[answer_length];
		for(int i=0; i<cnt.length; i++)
		{
			if(cnt[max_idx]==cnt[i])
			{
				answer[answer_idx++]=i+1;
			}
		}
		return answer;
	}
	
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int n = scanner.nextInt();
		scanner.nextLine();
		StringTokenizer st = new StringTokenizer(scanner.nextLine());
		int votes_num =0;
		int votes[] = new int [st.countTokens()]; 
		while(st.hasMoreTokens())
		{
			votes[votes_num++]=Integer.parseInt(st.nextToken());
		}
// 1번 투표 결과		
//		int votes [] = {1, 5, 4, 3, 2, 5, 2, 5, 5, 4};
//		int n =5; //후보의 수
// 2번 투표 결과
//		int votes [] = {1,3,2,3,2};
//		int n =3; //후보의 수
		int answer[] ;
		answer = func(n, votes);
		
		for(int i=0; i<answer.length; i++)
		{
			System.out.print(answer[i] + " ");
		}
	}
}