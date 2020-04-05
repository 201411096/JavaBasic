package b_baekjoon;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Ex11047  {
	
	static int findAnswer(int price, int priceSample[]) {
		int answer=0;
		for(int i=0; i<priceSample.length; i++)
		{
			if(price>=priceSample[priceSample.length-1-i]) // 등호 부분 실수했었음 (price가 1원이 남은 상태에서 1원짜리 동전이 있을경우, 등호가 있어야만 카운트가 됨
			{
				answer+=price/priceSample[priceSample.length-1-i];
				price = price - (price/priceSample[priceSample.length-1-i])*priceSample[priceSample.length-1-i];
			}
		}
		
		return answer;
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int price = Integer.parseInt(st.nextToken());
		int priceSample [] = new int[n];
		for(int i=0; i<n; i++)
		{
			priceSample[i] = Integer.parseInt(br.readLine());
		}

		bw.write(Integer.toString(findAnswer(price, priceSample)));
		bw.flush();
		bw.close();
		br.close();
	}
}
