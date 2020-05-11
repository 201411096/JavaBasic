package a_algorithm;
import java.util.ArrayList;
import java.util.Scanner;

public class Ex11653{
	public static void main(String[] args){
		Scanner scanner = new Scanner(System.in);
		int number = scanner.nextInt();
		ArrayList<Integer> answerList = new ArrayList<Integer>();
		
		while(number>1) { // 입력받았던 값이 1보다 클 경우.. (소인수분해가 가능한 상태)
			int divideNum = 2;	// 나눌 수 있는값은 2부터 시작
			while(true) {
				if(number%divideNum==0) {			 // 나누어떨어질 경우
					number/=divideNum;				 // 값을 소인수로 나누고
					answerList.add(divideNum);		 // 소인수를 정답 배열에 저장
					break;							 // 반복문 탈출(나누는 값이 다시 2로됨)
				}
				divideNum++;						 // 나누어떨어지지 않을경우 나누는 값을 하나씩 증가 시킴			
			}
		}
		for(int i=0; i<answerList.size(); i++) {
			System.out.println(answerList.get(i));
		}
	}
}


