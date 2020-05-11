package a_algorithm;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Ex2108{
	static void findAnswer(int inputArray [] , int answer [], int countingArray[])
	{
		int max_cnt=0;
		ArrayList<Integer> arraylist = new ArrayList<Integer>();
		double sum = 0.0;
		for(int i=0; i<inputArray.length; i++)
		{
			sum+=inputArray[i];
		}
		for(int i=0; i<countingArray.length; i++)
		{
			if(countingArray[i]>max_cnt) // 더 클 경우는 arraylist 추가 생성
			{
				max_cnt=countingArray[i];
				arraylist = new ArrayList<Integer>(); //빈도수가 갱신될경우 arraylist를 다시 만듬
				arraylist.add(i);
			}else if(countingArray[i]==max_cnt) // 같을 경우는 arraylist에 추가
			{
				arraylist.add(i);
			}	
		}
		Collections.sort(arraylist); // 카운팅 정렬 오름차순
		answer[0]= (int)Math.round(sum/inputArray.length); // 산술평균
		answer[1]=inputArray[inputArray.length/2]; //중앙값
		if(arraylist.size()>=2) //최빈값이 두개 이상일 경우
			answer[2]=arraylist.get(1)-4000; //4000을 빼야 원래값이 나옴
		else //최빈값이 하나일 경우
			answer[2]=arraylist.get(0)-4000;
		answer[3]= inputArray[inputArray.length-1] - inputArray[0]; // 범위
	}
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int inputCase = Integer.parseInt(br.readLine());
		int inputArray[] = new int[inputCase];
		int countingArray[] = new int[8001];
		int answer [] = new int[4]; //정답이 들어가는 배열 // 0은 산술평균, 1은 중앙값, 2는  최빈값, 3은 범위
		for(int i=0; i<inputArray.length; i++) // 입력
		{
			int n= Integer.parseInt(br.readLine());
			inputArray[i] = n;
			countingArray[n+4000]++; //0번째 요소가 -4000부터 시작
		}
		Arrays.sort(inputArray); //오름차순 정렬
		findAnswer(inputArray, answer, countingArray);
		
		for(int i=0; i<answer.length; i++) //출력
		{
			bw.write(Integer.toString(answer[i]) + "\n");
			bw.flush();
		}
		
		
		br.close();
		bw.close();
	}
}

