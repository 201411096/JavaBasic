package a_algorithm;

import java.io.IOException;
import java.util.Scanner;

public class Ex15649{
	static int answerList [] = new int [10]; // 출력할 숫자들을 담는 배열
	static boolean checkList [] = new boolean [10]; // backtracking에 사용할 배열
	public static void makeAnswer(int index, int n, int m) {
		if(index==m) {	// index까지 answerList에 숫자들을 다 담았을 경우 출력하는 부분
			for(int i=0; i<m; i++) {
				System.out.print(answerList[i]);
				if(i!=m-1)
					System.out.print(" ");
			}
			System.out.println();
			return;
		}				// end of if
		for(int i=0; i<n; i++) {	// 트리 형식으로 탐색
			if(checkList[i]==true)	// 이미 체크가 되어있다면 넘어감
				continue;
			checkList[i]=true;		// 체크가 되어 있지 않다면 체크
			answerList[index]=i+1;
			makeAnswer(index+1, n, m);
			checkList[i]=false;		// return을 타고 돌아오면서 체크해체
		}
	}
	public static void main(String[] args) throws IOException{
		Scanner scanner = new Scanner(System.in);
		int n = scanner.nextInt(); // 몇까지의 숫자중에서
		int m = scanner.nextInt(); // 몇개를 고를지
		makeAnswer(0, n, m);
	}
}

