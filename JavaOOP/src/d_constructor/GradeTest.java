package d_constructor;

import java.util.Scanner;

public class GradeTest {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int data_cnt = scanner.nextInt();
		int data [] = new int [data_cnt];
		for(int i=0; i<data.length; i++)
			data[i]=scanner.nextInt();
		System.out.print("점수들 : ");
		for(int i=0; i<data.length; i++)
		{
			if(i==data.length-1)
			{
				System.out.println(data[i]);
			}else
			{
				System.out.print(data[i] + ", ");
			}
		}
		GradeExpr g = new GradeExpr(data);
		System.out.println("총점 : " + g.getTotal());
		System.out.println("평균 : " + g.getAverage());
		System.out.println("최고 점수 : " + g.getGoodScore());
		System.out.println("최저 점수 : " + g.getBadScore());
			
	}
}
