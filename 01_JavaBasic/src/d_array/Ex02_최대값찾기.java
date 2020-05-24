package d_array;

public class Ex02_최대값찾기 {
	public static void main(String[] args) {
		int score [] = {90, 44, 55, 22, 20, 14, 80, 40};
		int max=0;
		for(int i=0; i<score.length; i++) //배열의 개수만큼 반복
		{
			if(max<score[i]) // max가 배열의 i번째 요소보다 작다면 max값을 i번째 요소 값으로 갱신
			{
				max=score[i];
			}
		}
		System.out.println(max); //max값 출력
	}
}
