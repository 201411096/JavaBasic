package d_array;

import java.util.Scanner;

public class Ex08_OX {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int testCase=scanner.nextInt();
		scanner.nextLine();
		int answer[] = new int[testCase];
		int plus =1;
		for(int n=0; n<testCase; n++)
		{
			String str = scanner.nextLine();
			plus=1;
			for(int i=0; i<str.length(); i++) // 문자열에 속한 문자의 개수만큼
			{
				if(str.charAt(i)=='O')
				{
					answer[n]+=plus++;
				}else
				{
					plus=1;
				}
			}
		}
		
		for(int i=0; i<testCase; i++)
		{
			System.out.println(answer[i]);
		}
	}
}

