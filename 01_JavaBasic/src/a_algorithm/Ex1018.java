package a_algorithm;

import java.util.Scanner;
import java.util.StringTokenizer;

public class Ex1018  {  
	static int findMin(int arr[][]) {
		int min=arr[0][0];
		for(int i=0; i<arr.length; i++)
			for(int j=0; j<arr[i].length; j++)
				if(arr[i][j]<min)
					min=arr[i][j];
		return min;
	}
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int n =scanner.nextInt();
		int m = scanner.nextInt();
		scanner.nextLine();
		char rectangle[][] = new char[n][m];
		int cntArr[][] = new int [n-8+1][m-8+1];
		for(int i=0; i<n; i++) // 입력
		{
			String str = scanner.nextLine();
			for(int j=0; j<m; j++)
				rectangle[i][j]=str.charAt(j);
		}
		for(int i=0; i<cntArr.length; i++) // 칠해야 하는 갯수들을 저장하는 배열에 갯수 저장
		{
			for(int j=0; j<cntArr[i].length; j++)
			{
				char criteria='B';
				int cnt_start_b=0, cnt_start_w=0; // 맨 위를 B로 기준으로 했을떄의 최솟값과 W를 기준으로 했을때의 최솟값
				for(int n1=0; n1<8; n1++)
				{
					for(int n2=0; n2<8; n2++)
					{
						if(rectangle[i+n1][j+n2]==criteria) // 맨위를 기준값과 같으면서 arr[i][j] i+j값이 홀수이면 다시 칠해야 되는 타일
						{
							if(((n1+n2)%2)==1) 
								cnt_start_b++;
							else
								cnt_start_w++;
						}else {
							if(((n1+n2)%2)==0)
								cnt_start_b++;
							else
								cnt_start_w++;
						}
					}
				}
				cntArr[i][j]=(cnt_start_b<cnt_start_w?cnt_start_b:cnt_start_w);
			}
		}
		System.out.println(findMin(cntArr));
	}
}
