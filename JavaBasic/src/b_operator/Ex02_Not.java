package b_operator;

/**
 * 	not : 결과를 반대로 하는 연산자
 * 			- 일반논리 not :  !
 * 			- 이진논리 not : ~
 */
public class Ex02_Not {

	public static void main(String[] args) {
	
		boolean result = 3>4;
		System.out.println(result); // false
		System.out.println(!result); //true
		
		int a = 15;
		System.out.println(~a);
	}

}
