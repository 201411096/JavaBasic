package a_algorithm;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class Ex1181{
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int inputCase = Integer.parseInt(br.readLine());
		Ex1181SortClass stringArray[] = new Ex1181SortClass[inputCase];
		for(int i=0; i<inputCase; i++) //입력
		{
			stringArray[i] = new Ex1181SortClass(br.readLine());
		}
		Arrays.sort(stringArray); //정렬
		for(int i=0; i<inputCase; i++)
		{
			if(i>=1 && !stringArray[i-1].s.equals(stringArray[i].s)) //i>=1이면서 이전 문자열과 다르다면 출력
			{
				bw.write(stringArray[i].s+"\n");		
				bw.flush();
			}else if(i==0) {							//i=0이면 그냥 출력
				bw.write(stringArray[i].s+"\n");
				bw.flush();
			}
		}
		br.close();
		bw.close();
	}
}
class Ex1181SortClass implements Comparable<Ex1181SortClass>{
	String s;
	int length;
	public Ex1181SortClass(String s) {
		this.s=s;
		this.length=s.length();
	}
	@Override
	public int compareTo(Ex1181SortClass o) {
		if(this.length==o.length)
			return this.s.compareTo(o.s); // 문자열 내림차순
		else
			return this.length-o.length;
	}
	
}
