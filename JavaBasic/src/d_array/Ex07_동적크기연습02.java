package d_array;

public class Ex07_동적크기연습02 {
	public static void main(String[] args) {
		int a[][]=new int[][]{{3,-5, 12, 3, -21}, {-2, 11, 2, -7, -11}, {21, -21, -35, -93, -11}, {9, 14, 39, -98, -1}};
		//문제 수정 전(가장 큰 열도 구하는 문제),(동적 할당 안 씀) 
//		int max_idx_row=0, max_idx_col=0;
//		int max=0;
//		for(int i=0; i<a.length; i++)
//		{
//			int temp=0;
//			for(int j=0; j<a[i].length; j++)
//			{
//				temp+=a[i][j];
//			}
//			if(max<temp)
//			{
//				max=temp;
//				max_idx_row=i;
//			}
//		}
//		max=0;
//		for(int i=0; i<a[0].length; i++)
//		{
//			int temp=0;
//			for(int j=0; j<a.length; j++)
//			{
//				temp+=a[j][i];
//			}
//			if(max<temp)
//			{
//				max=temp;
//				max_idx_col=i;
//			}
//		}
//		System.out.println(max_idx_row+1 + "행");
//		System.out.println(max_idx_col+1 + "열");
		int sum[] = new int[a.length];
		int max_idx =0;
		int max_row_idx=0;
		for(int i=0; i<a.length; i++)
		{
			sum[i]=0;
			for(int j=0; j<a[i].length; j++)
			{
				sum[i]+=a[i][j];
			}
		}
		
		for(int i=0; i<sum.length; i++)
		{
			if(sum[i]>=sum[max_idx])
				max_idx=i;
		}
		System.out.println("합이 가장 큰 행 :" + max_idx);
		for(int i=0; i<a[max_idx].length; i++)
		{
			if(a[max_idx][max_row_idx]<=a[max_idx][i])
				max_row_idx=i;
		}
		System.out.println("합이 가장 큰 행에서의 가장 큰 수의 열 번호 : " + max_row_idx);
		
	}
}
