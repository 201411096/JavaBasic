package c_control;

public class Ex04_for중첩 {
	public static void main(String[] args) {
		
//		*****
//		 ****
//		  ***
//		   **
//		    *
		
		for(int i=0;i<5;i++) {
			for(int n=0; n<i; n++)
				System.out.print(" ");
			for(int j=0;j<5-i;j++)
			{
				System.out.print("*");
			}
			System.out.println("");
		}
		
//---------------------------------------------------------------------------------------------------------
//		A~Z 한줄에 출력
		for(int i=0; i<26; i++) {
			for(char ch='A'; ch<='A'+i; ch++)
			{
				System.out.print(ch);
			}
			System.out.println();
		}
		
		
	}
}
