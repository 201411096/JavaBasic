package e_method;

public class Ex03_2CallByRef {
	/**
	 *  메소드의 인자가 참조형인 경우
	 *  		-> 주소 복사됨
	 *  		call by reference 
	 */
	static void add( StringBuffer a, StringBuffer b) {
		//a+=b;
		a.append(b);
		System.out.println("A=" + a + ", B=" + b );
	}
	public static void main(String[] args) {
		StringBuffer a = new StringBuffer("안녕");
		StringBuffer b = new StringBuffer("하이");
		add(a, b);
		System.out.println("A=" + a +", B=" +b );
	}
}