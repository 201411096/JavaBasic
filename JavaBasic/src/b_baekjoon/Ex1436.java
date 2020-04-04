package b_baekjoon;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Scanner;

public class Ex1436  {
	static int findAnswer(int n) {
		int number=666;
		while(n!=0)
		{
			for(int i=0; i<countDigit(number)-2; i++) // 숫자 자릿수의 -2만큼 반복
			{
				if(Integer.toString(number).substring(i, i+3).equals("666")) // i번째부터 ㅑ+2번쨰까지 666과 동일하다면
				{
					n--; // 하나를 찾음
					break; //찾고 있는 숫자의 반복문안에서 나오기
				}
			}
			number++;	
		}		
		return number-1; //마지막에 찾았을때도 하나가 추가된 부분 상쇄
	}
	static int countDigit(int n) { // 자릿수 세는 함수
		int result=0;
		while(n!=0)
		{
			n/=10;
			result++;
		}		
		return result;
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));	//메모리 초과나서 바꿈
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out)); //메모리 초과나서 바꿈
		int n = Integer.parseInt(br.readLine());
		bw.write(Integer.toString(findAnswer(n)));
		bw.flush();			// 남아있는 부분 강제로 출력
	}
}
