package b_operator;

/**
 *  shift : 모든 비트의 값을 이동하는 연산자
 *  
 *  [예] 0000 0010	<<1 		0000 0100
 *  	  0000 0010	>>1		0000 0001
 */
public class Ex04_Shift {

	public static void main(String[] args) {
		int su = -4;
		System.out.println(su);
		System.out.println(su << 1);
		System.out.println(su >> 1);
		
		System.out.println(su >>> 1);
		
		  int i = 4, j= 2;
		  i = i << 2;
		  System.out.println("result = " + i );
		  int a = -5;
		  if (  true && ( ( ++a / 3 ) > 0 ) ) {
		        a++;
		  }
		  System.out.println( a );
		  System.out.println(-4/3);
	}

}
