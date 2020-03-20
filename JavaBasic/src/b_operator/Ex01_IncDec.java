package b_operator;

/**
 *  증가(++)/감소(--) 연산자
 */
public class Ex01_IncDec {

	public static void main(String[] args) {
		
		int a = 5;
		int b = 7;
		
//		// [1]
//		System.out.println("a=" + a + ", b=" + b);
//		System.out.println("a=" + a+1 + ", b=" + b+1);
//		System.out.println("a=" + (a+1) + ", b=" + (b+1) );
//		// [2]
//
//		a++;
//		b--;
//		System.out.println("a=" + a + ", b=" + b);
//		
//		++a;
//		--b;
//		System.out.println("a=" + a + ", b=" + b);
		
//		int result = ++a;
//		System.out.println("결과:" + result);
//		int result2 = b--;
//		System.out.println("결과:" + result2);
//		System.out.println("결과:" + b);
		
		System.out.println("a=" + ++a + ", b=" + --b); // 6, 6
		System.out.println("a=" + a++ + ", b=" + b--); // 6, 6
		System.out.println("a=" + a + ", b=" + b); // 7, 5
	}

}
