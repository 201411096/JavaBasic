package b_baekjoon;

import java.util.Scanner;

public class Ex1316 {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int testCase = scanner.nextInt();
		scanner.nextLine();
		String stringArray[] = new String[testCase];
		int answer=testCase;
		for(int i=0; i<testCase; i++) 
		{
			stringArray[i]=scanner.nextLine();
		}
		
		for(int i=0; i<testCase; i++)
		{
			int alphabet_cnt[] = new int[26]; //알파벳 개수 만큼 배열 할당
			char pre_alphabet = stringArray[i].charAt(0); //첫번째 문자를 이전의 문자로 저장
			alphabet_cnt[ (int)(pre_alphabet-'a') ] = 1; // 첫번째 문자를 문자 사용 여부를 저장하는 배열에 저장
			for(int j=1; j<stringArray[i].length(); j++) //두번째 문자부터 비교 시작
			{
				if(stringArray[i].charAt(j)!=pre_alphabet && alphabet_cnt[(int)(stringArray[i].charAt(j)-'a')]==1) //이전 알파벳과 다르면서 이전에 사용한 적도 있으면
				{
					answer--;
					break;
				}
				alphabet_cnt[ (int)(stringArray[i].charAt(j)-'a') ] = 1; //알파벳 사용 여부를 저장하는 배열에 저장
				pre_alphabet=stringArray[i].charAt(j); //이전 알파벳 변경
			}
		}
		
			
		System.out.println(answer);
	}

}

