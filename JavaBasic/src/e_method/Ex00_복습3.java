package e_method;

import java.util.Scanner;

public class Ex00_복습3 {
	public static void main(String[] args) {
		input();
	}
	//국 영 수 점수를 입력 받기
	static void input() {
		Scanner scanner = new Scanner(System.in);
		int kor =0, eng=0, math=0;
		kor=scanner.nextInt();
		eng=scanner.nextInt();
		math=scanner.nextInt();
	}
}
