package a_algorithm;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Ex11399{
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int inputNumber = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		int inputArray [] = new int[inputNumber];
		int waitingTimeArray [] = new int[inputNumber];
		int answer=0;
		for(int i=0; i<inputNumber; i++)
			inputArray[i] = Integer.parseInt(st.nextToken());
		Arrays.sort(inputArray);
		
		for(int i=0; i<inputArray.length; i++)
		{
			answer+=inputArray[i]*(inputArray.length-i);
		}

		bw.write(Integer.toString(answer));
		bw.flush();
		
		br.close();
		bw.close();
	}
}
