package e_static;

public class Main {
		
/*
 * 단 하나로 공유 :static
 * 
 * 		= 객체 명이 아닌 클래스 명으로 접근이 가능
 * 
 * 		Main m = new Main();
 * 		m.main();
 * 		=>
 * 		Main.main();
*/	
	public static void main(String[] args) {
		
		Book b1 = new Book();
		Book b2 = new Book();
		Book b3 = new Book();

		System.out.println("총 갯수 : " + Book.getCount()); // 객체명.함수(x) 클래스명.함수()식으로 사용하기 위해서는 함수가 static method이어야 함 
//		System.out.println("총 갯수 : " + b2.count);
//		System.out.println("총 갯수 : " + b3.count);
	}
}
