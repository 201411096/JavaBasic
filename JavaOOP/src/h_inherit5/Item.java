package h_inherit5;

public abstract class Item {
	protected String number; // protected로 사용하면 자식 클래스에서 this.number=number 이런식으로 사용 가능
	protected String title;
	
	public Item () {
		System.out.println("Item 기본 생성자");
	}
	public Item (String number, String name)
	{
		this.number=number;
		this.title=name;
		System.out.println("Item 인자 생성자");
	}
	public abstract void output();
	
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String name) {
		this.title = name;
	}
	
}
