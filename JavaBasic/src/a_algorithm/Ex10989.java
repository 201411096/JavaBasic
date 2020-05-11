package a_algorithm;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Ex10989{ // 카운팅정렬 이용
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuffer answer = new StringBuffer();
		
		int inputCase = Integer.parseInt(br.readLine());
		int countArray [] = new int[10001]; // 1~10000까지의 숫자의 입력된 갯수를 담을 수 있도록..
		for(int i=0; i<inputCase; i++)
		{
			countArray[Integer.parseInt(br.readLine())]++;
		}
		for(int i=0; i<10001; i++)
		{
			for(int j=0; j<countArray[i]; j++)
			{
				answer.append(Integer.toString(i)+"\n");
			}
		}
		bw.write(answer.toString());
		bw.flush();
		br.close();
		bw.close();
	}
}
