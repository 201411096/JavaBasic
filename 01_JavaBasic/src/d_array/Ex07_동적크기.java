package d_array;

public class Ex07_동적크기 { // 행마다 메모리를 다르게 
	public static void main(String[] args) {
		char star[][] = new char[5][];
		
		for(int i=0; i<star.length; i++)
		{
			star[i] = new char[i+1]; // 0번째 행에서는 0+1만큼 할당
			
			for(int j=0; j<i+1; j++)
			{
				star[i][j]='*';
			}
		}
		
		for(int i=0; i<star.length; i++)
		{
			for(int j=0; j<star[i].length; j++)
			{
				System.out.print(star[i][j] + " ");
			}
			System.out.println();
		}
	}
}
