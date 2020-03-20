package c_control;

public class Ex04_for중첩2 {
	public static void main(String [] args) {
		for(int i=0; i<26; i++) {
			for(char ch='A'; ch<='Z'-i; ch++)
			{
				System.out.print(ch);
			}
			System.out.println();
		}
	}
}
