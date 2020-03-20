package d_array;

public class Ex06_복습 {
	public static void main(String[] args) {
		char ch[][] = new char[3][4];
		int num=0;
		char al = 'A';
//		for(int i=0; i<ch.length; i++)
//		{
//			for(int j=0; j<ch[i].length; j++)
//			{
//				ch[i][j]=(char)('A'+ num++);
//				
//			}
//		}
		for(int i=0; i<ch.length; i++)
		{
			for(int j=0; j<ch[i].length; j++)
			{
				ch[i][j]=al++;
				
			}
		}
		
		for(int i=0; i<ch.length; i++)
		{
			for(int j=0; j<ch[i].length; j++)
			{
				System.out.print(ch[i][j] + " ");
			}
			System.out.println();
		}
	}
}
