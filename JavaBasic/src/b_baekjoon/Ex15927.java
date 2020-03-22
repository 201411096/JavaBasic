package b_baekjoon;

import java.util.Scanner;

public class Ex15927 {
	public static void main(String[] args)
	{
		Scanner scanner = new Scanner(System.in);
		String str = scanner.nextLine();
		int strLength = str.length();
		int answer=-1;
		boolean allSame=true;
		for(int i=0; i<str.length(); i++) //완전 대칭인 문자열을 처리하지 않으면 50만 자리의 문자열을 처리할때 시간 초과 발생
		{
			if(str.charAt(0)!=str.charAt(i))
				allSame=false;
		}																						
		if(allSame==false)
		{
			HERE:
				for(int i=strLength; i>1; i--) //문자열의 길이-1 만큼 반복 //ex) 문자열이 ABCDE면 부분문자열의 크기가 5,4,3,2식으로 4번  //부분문자열의 길이로도 사용 가능
				{
					for(int j=0; j<strLength-i+1; j++) // 문자열 길이에서 - 현재 확인하고 있는 문자열의 크기를 뺀만큼 반복// 부분문자열의 시작 인덱스로 사용 가능  //ex) 문자열의 크기가 5이고 부분문자열의 크기가 4이면 2번 반복, 부분문자열의 크기가 3이면 3번 반복 
					{
						String subStr = str.substring(j, i+j);  // 회문인지 판별할 부분문자열 // 끝 인덱스는 첫 인덱스+부분문자열의 크기
						for(int n=0; n<i/2; n++)
						{
							if(subStr.charAt(n)!=(subStr.charAt(subStr.length()-1-n)))
							{
								answer=subStr.length();
								break HERE;
							}				
						}
					}
				}
				System.out.println(answer);	
		}else {
			System.out.println(answer);
		}
	}
}
