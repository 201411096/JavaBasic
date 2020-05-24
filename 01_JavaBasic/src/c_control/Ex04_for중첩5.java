package c_control;

public class Ex04_for중첩5 {
	public static void main(String[] args) {
		for(int i=0;i<26;i++){
			for(int n=0; n<i; n++)
			{
				System.out.print(" ");
			}
			for(char ch=(char)('A'+i); ch<='Z'; ch++)
			{
				System.out.print(ch);
			}
			System.out.println();
		}
	}
}
