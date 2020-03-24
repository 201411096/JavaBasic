package e_method;
/*
 *  오버로딩(overloading)
 *  	- 인자의 자료형과 갯수가 다른 동일한 함수를 
 *  	- 리턴형만 다른 함수는 오버로딩이 아님
 */

public class Ex07_overloading {
	static void fighting(String str)
	{
		System.out.println(str + " 화이팅");
	}
	static void fighting(StringBuffer str)
	{
		str.append(" 화이팅");
		System.out.println(str);
	}
	
	static void fighting(char [] ch)
	{
		for(int i=0; i<ch.length; i++)
			System.out.print(ch[i]);
		System.out.println(" 화이팅");
	}
	
	public static void main(String[] args) {
		//각각의 자료형 변수의 값에 "화이팅!!" 더하여 출력
		String str = new String("홍길순");
		StringBuffer sb = new StringBuffer("홍길자");
		char [] ch = new char[] {'홍', '길', '이'};
		
		fighting(str);
		fighting(sb);
		fighting(ch);
	}
}
