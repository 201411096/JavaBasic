package a_algorithm;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Ex1541{ // '-'연산자를 만난 이후로는 전부 다 빼주면 되는 문제
	static int findAnswer(String str) {
		int answer=0;
		ArrayList<Character> op = new ArrayList<Character>(); //연산자
		ArrayList<Integer> operand = new ArrayList<Integer>(); //피연산자
		
		for(int i=0; i<str.length();i++) // 연산자 저장
		{
			if(str.charAt(i)=='+')
				op.add('+');
			else if(str.charAt(i)=='-')
				op.add('-');
		}
		StringTokenizer st = new StringTokenizer(str, "+||-"); //피연산자 구분
		while(st.hasMoreTokens())
			operand.add(Integer.parseInt(st.nextToken())); // 피연산자 저장
		answer=operand.get(0); // 첫번쨰 숫자 입력
		boolean minusFlag=false; // -를 만났는지 안 만났는지 확인
		for(int i=0; i<op.size(); i++) //-를 만났는지 확인하고 -를 만났다면 그 뒤로 나오는 숫자들을 전부 뺴기
		{
			if(op.get(i)=='-')
				minusFlag=true;
			if(minusFlag==true)
				answer-=operand.get(i+1);
			else
				answer+=operand.get(i+1);
		}
		return answer;
	}
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		String str = br.readLine();
		
		bw.write(Integer.toString(findAnswer(str)));
		bw.flush();
		
		br.close();
		bw.close();
	}
}
