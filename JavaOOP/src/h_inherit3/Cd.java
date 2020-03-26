package h_inherit3;

public class Cd extends Item{
	private String singer;
	
	public Cd() {}
	public Cd(String number, String title, String singer)
	{
		setNumber(number);
		setTitle(title);
		this.singer=singer;
	}
	public void output() {
		System.out.println(getNumber());
		System.out.println(getTitle());
		System.out.println(getSinger());
	}

	public String getSinger() {
		return singer;
	}

	public void setSinger(String singer) {
		this.singer = singer;
	}
	
}
