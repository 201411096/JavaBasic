package h_inherit5;
/*
 * 	this : 자신 객체를 지칭하는 레퍼런스
 * 	super : 부모 객체를 지칭하는 레퍼런스
 * -----------------------------
 * 	this(), super() 메소드 둘 다 첫번째에 사용해야 함
 * 	this() : 자신 객체에 있는 다른 생성자 함수를 호출할 떄
 * 	super() : 부모 생성자 함수를 호출할 떄 사용
 */

public class Book extends Item{
	private String author;
	private String publisher;
	
	public Book() {
		System.out.println("Book 기본 생성자");
	}
	public Book(String number, String title, String author, String publisher)
	{
// 		item 기본 생성자 사용
		super.number=number; // 부모 멤버 변수에는 super 사용
		super.title=title;
//		item 인자 생성자 사용
//		super(number, title); 
		this.author=author;
		this.publisher=publisher;
		System.out.println("Book 인자 생성자");
	}
	
	public void output()
	{
		System.out.println(getNumber());
		System.out.println(getTitle());
		System.out.println(getAuthor());
		System.out.println(getPublisher());
	}
	
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getPublisher() {
		return publisher;
	}
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}
	

}
