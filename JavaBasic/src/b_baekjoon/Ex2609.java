package b_baekjoon;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Ex2609{
	static void getArrayListFromNumber(ArrayList<Integer> list, int num1) {
		while(num1>1) {
			int divideNum=2;
			while(true) {
				if(num1%divideNum==0) { // 나뉘어떨어지면 소인수를 모아두는 arraylist에 추가하고 반복문 탈출 / 나뉘어 떨어지지 않으면 나누는수+1
					num1/=divideNum;
					list.add(divideNum);
					break;
				}
				divideNum++;
			}
		}
	}
	static int getAnswer1(ArrayList<Integer> list1, ArrayList<Integer> list2) { // 최대공약수
		int answer=1;
		int list1_idx=0;
		int list2_idx=0;
		
		while(list1_idx<list1.size() && list2_idx<list2.size()) {
			if(list1.get(list1_idx)==list2.get(list2_idx)) { //인수가 같다면
				answer*=list1.get(list1_idx);
				list1_idx++;
				list2_idx++;
			}else if( list1.get(list1_idx)>list2.get(list2_idx) ) {
				list2_idx++;
			}else {
				list1_idx++;
			}
		}
		return answer;
	}
	static int getAnswer2(ArrayList<Integer> list1, ArrayList<Integer> list2, int answer1) { // 최소공배수
		int answer=1;
		int list1_idx=0;
		int list2_idx=0;
		for(int i=0; i<list1.size(); i++) {
			answer*=list1.get(i);
		}
		for(int i=0; i<list2.size(); i++) {
			answer*=list2.get(i);
		}
		answer/=answer1; //최대공약수로 나눠줌
		
		return answer;
	}
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int num1 = Integer.parseInt(st.nextToken());
		int num2 = Integer.parseInt(st.nextToken());
		ArrayList<Integer> list1 = new ArrayList<Integer>();
		ArrayList<Integer> list2 = new ArrayList<Integer>();
		
		
		getArrayListFromNumber(list1, num1); // 각각의 소인수들을 담아옴
		getArrayListFromNumber(list2, num2);
		
		int answer1= getAnswer1(list1, list2);
		int answer2= getAnswer2(list1, list2, answer1); //최대공약수도 넣어서 계산
		
		bw.write(Integer.toString(answer1)+"\n");
		bw.flush();
		bw.write(Integer.toString(answer2));
		bw.flush();
	}
}


