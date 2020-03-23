package e_method;

public class Ex03_3CallByString {
	//결과는 call-by-value처럼 나옴
	static void add( String a, String b) {
		a+=b;
//		a.append(b);
		System.out.println("A=" + a + ", B=" + b );
	}
	public static void main(String[] args) {
		String a = new String("안녕");
		String b = new String("하이");
		add(a, b);
		System.out.println("A=" + a +", B=" +b );
	}
}
