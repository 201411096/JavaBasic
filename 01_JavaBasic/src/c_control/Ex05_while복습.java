package c_control;

public class Ex05_while복습 {
	public static void main(String[] args) {
		int n=1;
		int sum=0;
		while(true) {
			sum+=n;
			if(n++==10)break;
		}
		System.out.println(sum);
	}
}
