package b_baekjoon;

import java.util.Scanner;
import java.util.StringTokenizer;


public class Ex4153 {
	static int findMaxIdx(int array []) { // 배열의 요소 중에 가장 큰 값의 인덱스를 반환
		int max_idx =0;
		for(int i=0; i<array.length; i++)
		{
			if(array[i]>array[max_idx])
				max_idx=i;
		}
		return max_idx;
	}
	
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		String answer [] = new String[30000]; //정답을 담는 배열
		int answer_num=0; //답을 저장하는데 사용하는 인덱스 && 답을 어디까지 저장했는지
		while(true)
		{
			String str = scanner.nextLine();
			if(str.equals("0 0 0")) break;
			StringTokenizer st = new StringTokenizer(str);
			int num[] = new int[3];
			for(int i=0; i<num.length; i++)
				num[i] = Integer.parseInt(st.nextToken());
			int max_idx = findMaxIdx(num);
			int sum =0;
			for(int i=0; i<num.length; i++) //max_idx를 제외한 값들의 제곱을 더함
			{
				if(i!=max_idx)
					sum+=num[i]*num[i];
			}
			
			if(sum==num[max_idx]*num[max_idx])
				answer[answer_num++]="right";
			else
				answer[answer_num++]="wrong";
		}
		for(int i=0; i<answer_num; i++)
			System.out.println(answer[i]);
	}
}