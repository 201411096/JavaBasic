package b_baekjoon;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;


public class Ex2750{
	static void bubbleSort(int array[]) { // 뒤에서부터 정렬이 되기 시작
		for(int i=0; i<array.length-1; i++)
		{
			for(int j=0; j<array.length-1-i; j++)
			{
				if(array[j]>array[j+1])
				{
					int temp=array[j+1];
					array[j+1]=array[j];
					array[j]=temp;
				}
			}
		}
	}
	static void selectionSort(int array[]) { // 앞에서부터 정렬이 되기 시작
		for(int i=0; i<array.length-1; i++)
		{
			int min_idx=i;
			for(int j=i+1; j<array.length; j++)
			{
				if(array[min_idx]>array[j])
				{
					min_idx = j;
				}
			}
			int temp = array[min_idx];
			array[min_idx]=array[i];
			array[i]=temp;
		}
	}
	static void insertionSort(int array[]) {
		for(int i=1; i<array.length; i++) {
			int temp = array[i];
			int idx=i-1;
			while(idx>=0 && temp<array[idx])
			{
				array[idx+1]=array[idx];
				idx--;
			}
			array[idx+1]=temp;
		}
	}

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuffer answer= new StringBuffer();
		int inputCase = Integer.parseInt(br.readLine());
		int inputNum [] = new int[inputCase];
		for(int i=0; i<inputCase; i++)
		{
			inputNum[i] = Integer.parseInt(br.readLine());
		}
//		bubbleSort(inputNum);
//		selectionSort(inputNum);
		insertionSort(inputNum);
		
		for(int i=0; i<inputNum.length; i++)
			answer.append(Integer.toString(inputNum[i])+"\n");
		
		bw.write(answer.toString());
		bw.flush();
		
		br.close();
		bw.close();
	}
}