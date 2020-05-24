package c_control;

public class Ex04_for중첩3 {

		public static void main(String [] args) {
			for(int i=0; i<26; i++) {
				for(char ch=(char)('A'+i); ch<='Z'; ch++)
				{
					System.out.print(ch);
				}
				System.out.println();
			}
		}
}