package d_array;

import java.util.Scanner;

public class Ex08_OX {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int testCase=scanner.nextInt(); //testcase 개수 입력
		scanner.nextLine(); 
		int answer[] = new int[testCase]; //testcase 크기만큼 배열 설정
		int plus =1; //O가 나왔을 경우 추가할 점수
		for(int n=0; n<testCase; n++)
		{
			String str = scanner.nextLine();
			plus=1;
			for(int i=0; i<str.length(); i++) // 문자열에 속한 문자의 개수만큼
			{
				if(str.charAt(i)=='O') //O가 나오면 점수를 올려주고, 증가하는 점수도 올림
				{
					answer[n]+=plus++;
				}else // X가 나오면 증가하는 점수가 1로 초기화
				{
					plus=1;
				}
			}
		}
		
		for(int i=0; i<testCase; i++)
		{
			System.out.println(answer[i]);
		}
	}
}

