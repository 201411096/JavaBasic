package f_exception;

public class MyException extends Exception{
	public String getMessage() {
		return "맨날 실수하는 예외: 배열 범위 확인";
	}

}
