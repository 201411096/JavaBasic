package a_algorithm;

import java.util.Scanner;
import java.util.StringTokenizer;

public class Ex1085 {
	static int func(int x, int y, int w, int h)
	{
		int min = x;
		if(min>w-x)
			min = w-x;
		if(min>y)
			min = y;
		if(min>h-y)
			min = h-y;
		return min;		
	}
	
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		StringTokenizer st = new StringTokenizer(scanner.nextLine());
		int x=Integer.parseInt(st.nextToken());
		int y=Integer.parseInt(st.nextToken());
		int w=Integer.parseInt(st.nextToken());
		int h=Integer.parseInt(st.nextToken());
		int answer=-1;
		answer=func(x,y,w,h);
		System.out.println(answer);
	}
}
