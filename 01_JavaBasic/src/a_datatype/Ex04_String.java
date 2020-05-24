package a_datatype;

/* [주의] 클래스명을 절대로 String 이라고 하면 안된다 */
/* 
 * String은 기본 데이타 타입이 아닌 참조형 데이타 타입이다.
 * 참조형은 new를 이용하여 객체를 생성해야 한다.
 */

class Ex04_String {

	public static void main ( String  [] args ) {

			byte a = 64;

			byte b = 64;

			byte result = (byte) (a+b);

			System.out.println("result = " + result );

}

}

