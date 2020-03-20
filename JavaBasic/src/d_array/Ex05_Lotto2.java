package d_array;

import java.util.Scanner;

public class Ex05_Lotto2 {
	public static void main(String[] args) {
		int lotto[][] = new int[5][6];
		Scanner scanner = new Scanner(System.in);
		int duplicateCnt, temp;
		//중복 배제 안되는 버전
//		for(int i=0; i<lotto.length; i++) // 줄 (행)
//		{
//			for(int j=0; j<lotto[i].length; j++) // 칸 (열)
//			{
//				lotto[i][j]= (int)(Math.random()*45+1);
//			}
//		}
		//중복 배제 되는 버전
		for(int i=0; i<lotto.length; i++)
		{
			for(int j=0; j<lotto[i].length; j++)
			{
				
				temp=0;
				//랜덤 숫자가 중복일 경우 다시 생성
				while(true){
					temp=(int)(Math.random()*45)+1;
					duplicateCnt=0;
					for(int n=0; n<j; n++)
					{
						if(lotto[i][n]!=temp)
							duplicateCnt++;
						
					}
					if(j==duplicateCnt)break;
				}
				lotto[i][j]=temp;
			}
		}
		
		
		
		
		//정렬
		for(int m=0; m<lotto.length; m++)
		{
			for(int i=0; i<lotto[m].length-1;i++)
			{
				for(int j=0; j<lotto[m].length-i-1; j++)
				{
					if(lotto[m][j]>lotto[m][j+1])
					{
						temp = lotto[m][j];
						lotto[m][j]=lotto[m][j+1];
						lotto[m][j+1]=temp;
					}
				}
			}
		}
		
		
		for(int i=0; i<lotto.length; i++)
		{
			for(int j=0; j<lotto[0].length; j++)
			{
				System.out.print(lotto[i][j] + "/");
			}
			System.out.println();
		}
		
	}
}
