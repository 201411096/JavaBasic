package f_exception;

public class Ex01_TryCatch {

	public static void main(String[] args) {
		
		String str[] = new String [] {"행복하자", "끝내자", "맛난거먹자"};
		
		try {
			for(int i=0; i<str.length; i++) //예외 발생 가능 부분
			{
				System.out.println(str[i]);
			}
			return;
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			System.out.println("무조건 실행 구문");
		}
		
		
		
		System.out.println("프로그램 정상 종료");
	}
}
/* 오류
 * 	- 에러 : 심각한 오류(컴ㅍ파일 오류)
 *  - 예외 : 심각하지 않은 오류
 *  		-> 예외처리 : 프로그램을 정상 종료 시키기 위함
 *  		(1) try~catch
 *  			try{
 *  				예외가 발생할 구문
 *  			}catch(Exception ex){
 *  				예외가 발생한 후의 구문
 *  			}finally{
 *  				예외 관계 없이 무조건 실행 구문
 *  			}
 *  		(2) 메소드 뒤에 throws
 */
