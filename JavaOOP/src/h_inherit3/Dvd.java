package h_inherit3;

public class Dvd extends Item{
	private String actor;
	private String director;
	
	public Dvd() {}
	public Dvd(String number, String title, String actor, String director)
	{
		setNumber(number);
		setTitle(title);
		this.actor=actor;
		this.director=director;
	}


	public void output() {
		System.out.println(getNumber());
		System.out.println(getTitle());
		System.out.println(getActor());
		System.out.println(getDirector());
	}

	public String getActor() {
		return actor;
	}

	public void setActor(String actor) {
		this.actor = actor;
	}

	public String getDirector() {
		return director;
	}

	public void setDirector(String director) {
		this.director = director;
	}
	
}
