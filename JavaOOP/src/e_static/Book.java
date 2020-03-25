package e_static;

public class Book {
//	int count;
	private static int count;

	public Book(){
		count++;
		System.out.println("책 한개 생성");
	}

	public static int getCount() { // 메소드에 static을 붙이는 이유는 클래스명으로 접근하기 위함
		return count;
	}

	public static void setCount(int count) {
		Book.count = count;
	}
	
	
}
