package c_control;

public class Ex04_for중첩4 {
	public static void main(String[] args) {
		for(int i=0; i<26; i++)
		{
			for(char ch='Z'; ch>='Z'-i; ch--)
			{
				System.out.print(ch);
			}
			System.out.println();
		}
	}
}
