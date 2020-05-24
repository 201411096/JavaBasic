package c_control;

public class Ex04_for복습 {
	public static void main(String[] args) {
		//1부터 3까지 출력
//		for(int i=1; i<=3; i++)
//			System.out.println(i);
		//3행 2열 행렬에 번호 붙이기
		int num=1;
		for(int i=0; i<3; i++)
		{
			for(int j=0; j<2; j++)
			{
				System.out.printf("<%d, %d> ", i, j);
			}
			System.out.println();
		}
	}
}
