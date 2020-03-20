package d_array;

public class Ex04_정렬 {
	public static void main(String[] args) {
		int score [] = {90, 44, 55, 22, 20, 14, 80, 40};
		
		//bubble sort
//		for(int i=score.length-1; i>0; i--) // 맨뒤에서부터 정렬이 완벽하게 완료되기 시작함
//		{
//			for(int j=0; j<i; j++) { //처음부터 시작 && 뒤에가 하나씩 줄어듬
//				if(score[j]>score[j+1])
//				{
//					int temp=score[j];
//					score[j]=score[j+1];
//					score[j+1]=temp;
//				}
//			}
//		}
//		bubble sort2
//		for(int i=0; i<score.length-1; i++) // 맨뒤에서부터 정렬이 완벽하게 완료되기 시작함
//		{
//			for(int j=0; j<score.length-i-1; j++) { //처음부터 시작 && 뒤에가 하나씩 줄어듬
//				if(score[j]>score[j+1])
//				{
//					int temp=score[j];
//					score[j]=score[j+1];
//					score[j+1]=temp;
//				}
//			}
//		}
//		selection sort
//		for(int i=0; i<score.length-1; i++) //맨 앞에서부터 정렬이 완벽하게 완료되기 시작함
//		{			
//			int min_idx=i;
//			for(int j=i+1; j<score.length ;j++) // 시작하는 순서가 앞에서부터 하나씩 줄어듬
//			{
//				if(score[j]<score[min_idx])
//				{
//					min_idx=j;
//				}
//			}
//			int temp=score[i];
//			score[i]=score[min_idx];
//			score[min_idx]=temp;
//		}
//		insertion sort
		for(int i=1; i<score.length; i++)
		{
			int temp=score[i];
			int idx = i-1;
			while(idx>=0 && temp<score[idx])
			{
				score[idx+1]=score[idx];
				idx--;
			}
			score[idx+1]=temp;
		}
		for(int i=0; i<score.length; i++)
		{
			System.out.println(score[i]);
		}
	}
}
