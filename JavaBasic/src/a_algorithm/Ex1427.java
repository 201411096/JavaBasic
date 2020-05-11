package a_algorithm;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;


public class Ex1427{
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		String inputNum = br.readLine();
		int inputNumArray [] = new int [inputNum.length()];
		for(int i=0; i<inputNumArray.length; i++)
		{
			inputNumArray[i]=(int)inputNum.charAt(i);
		}
		Arrays.sort(inputNumArray);
		for(int i=inputNumArray.length-1; i>=0; i--)
		{
			bw.write(inputNumArray[i]);
			bw.flush();
		}
		
		
		br.close();
		bw.close();
	}
}

