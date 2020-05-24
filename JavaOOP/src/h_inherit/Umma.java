package h_inherit;

public class Umma {
	
	public Umma() {
		System.out.println("부모생성자"); // 자식 클래스가 인스턴스화 될 때 같이 생성됨
	}
	
	public void gene() {
		System.out.println("부모는 부모다");
	}
	
	public void job() {
		System.out.println("엄마는 대장");
	}
}
