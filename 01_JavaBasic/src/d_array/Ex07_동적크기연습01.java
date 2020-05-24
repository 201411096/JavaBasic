package d_array;

public class Ex07_동적크기연습01 {
	public static void main(String[] args) {
		int a[][] = new int[][]{{3,-5, 12 }, {-2, 11, 2, -7}, {21, -21, -35, -93, -11}, {9, 14, 39, -98}};
		//동적할당 안한 버전
//		int max=0, max_idx=0;
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
//				max_idx=i;
//			}
//		}
//		System.out.println(max_idx + "행");
		//동적할당 버전
		int sum[] = new int[a.length];
		int max_idx =0;
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
		System.out.println(max_idx +"행");
		
		
	}
}
