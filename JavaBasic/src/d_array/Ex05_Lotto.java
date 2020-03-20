package d_array;

import java.util.Scanner;

public class Ex05_Lotto {
	public static void main(String[] args) {
		int lotto[] = new int[6];
		Scanner scanner = new Scanner(System.in);
		int duplicateCnt=0;
		int temp=0;
		//랜덤 숫자 생성하는 방법(중복 배제 없음)
		//		for(int j=0; j<lotto.length; j++)
		//		{
		//			lotto[j]= (int)(Math.random()*45+1);
		//		}
		
		//랜덤 숫자 생성시 중복을 배재하는 버전
//		for(int j=0; j<lotto.length; j++)
//		{
//			temp=0;
//			//랜덤 숫자가 중복일 경우 다시 생성
//			while(true){
//				temp=(int)(Math.random()*45)+1;
//				duplicateCnt=0; 
//				for(int n=0; n<j; n++)
//				{
//					if(lotto[n]!=temp) // 이전 요소들이랑 다를 떄마다 cnt 증가
//						duplicateCnt++;
//				}
//				if(duplicateCnt==j)break; //이전 요소들이랑 모두 다를 경우 랜덤 숫자 생성 종료
//			}
//
//			lotto[j]=temp;
//		}
		//랜덤 숫자 생성시 중복을 배재하는 버전2 중복 발견시 다시 그 자리에서 랜덤 숫자 생성
		for(int j=0; j<lotto.length; j++)
		{
			lotto[j]= (int)(Math.random()*45)+1;
			for(int k=0; k<j; k++)
			{
				if(lotto[k]==lotto[j]) {
					j--;
				}
			}
		}
		
		for(int i=0; i<lotto.length-1;i++)
		{
			for(int j=0; j<lotto.length-i-1; j++)
			{
				if(lotto[j]>lotto[j+1])
				{
					temp = lotto[j];
					lotto[j]=lotto[j+1];
					lotto[j+1]=temp;
				}
			}
		}

		for(int j=0; j<lotto.length; j++)
		{
			System.out.print(lotto[j] + "/");
		}
	}
}
