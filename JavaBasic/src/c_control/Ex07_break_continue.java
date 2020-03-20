package c_control;

public class Ex07_break_continue {
	public static void main(String[] args) {
		
		HERE:
		for(int i=0; i<2; i++)
		{
			for(int j=0; j<3; j++)
			{
//				if(j==1)continue;
				
				System.out.printf("< %d, %d > \n", i, j);
				if(j==1)continue HERE;
			}
			System.out.println("데이터");
		}
	
	}
}
// (1) 2,3,6
// (2) 1
// (3) 2,3
// (4) 4
// (5) 4
// (6) 2
// (7) 4 \n 2 \n 0