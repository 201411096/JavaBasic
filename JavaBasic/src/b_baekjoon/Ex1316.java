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
			int alphabet_cnt[] = new int[26];
			char pre_alphabet = stringArray[i].charAt(0);
			alphabet_cnt[ (int)(pre_alphabet-'a') ] = 1;
			for(int j=1; j<stringArray[i].length(); j++)
			{
				if(stringArray[i].charAt(j)!=pre_alphabet && alphabet_cnt[(int)(stringArray[i].charAt(j)-'a')]==1)
				{
					answer--;
					break;
				}
				alphabet_cnt[ (int)(stringArray[i].charAt(j)-'a') ] = 1;
				pre_alphabet=stringArray[i].charAt(j);
			}
		}
		
			
		System.out.println(answer);
	}

}

