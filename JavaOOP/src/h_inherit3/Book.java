package h_inherit3;

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
