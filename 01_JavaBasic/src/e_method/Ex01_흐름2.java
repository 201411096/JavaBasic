package e_method;

public class Ex01_흐름2 {

	void method(){
		System.out.println("method 실행");
	}	
	public static void main(String[] args) {
		Ex01_흐름2 ex = new Ex01_흐름2();
		System.out.println("메인 함수 시작");
		ex.method();
		System.out.println("메인 함수 끝");
	}
	

}
/*
 * 명명규칙
 * 1. 문자+숫자+_+$ 조합
 * 2. 길이제한 없음
 * 3. 첫글자에 숫자만 아니면 됨
 * 4. 대소문자 구별
 * 5. 예약어는 사용할 수 없음
 * 권장사항
 * 	- 클래스명의 첫글자는 대문자시작
 * 	- 변수와 메소드명의 첫글자는 소문자로 시작
 * 	- 라벨명은 전부 대문자로
 * 	- 패키지명은 전부 소문자로
 */

