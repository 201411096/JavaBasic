package h_inherit5;

/*
 * Item i1 = new Book(); // upcasting 부모 클래스 레퍼런스에 자식 클래스 객체 생성 
 * 
 * Book b1 = new Item(); // downcasting 일반적인 경우에는 런타임 오류 발생 (메모리 할당이 적게 되어 있기 때문에..?)
 * 다운 캐스팅이 성립하는 경우 -------------------------------------
 * Item i2 = new Book();  
 * Book b2 = (Book)i2; // 태생이 자식 클래스인 객체를 다운캐스팅 하는 경우 가능
 * 
 */

public class Test {
	static Item[] method() {
//		Item i = new Item(); // 추상클래스는 객체 생성 불가
		
		Book b = new Book("001", "자바", "홍길동", "코스모"); 
		Cd c = new Cd("002", "아는노래", "지코");
		Dvd d = new Dvd("003", "킹덤", "배두나", "김은희");
		
		Item i [] = new Item[3]; 
		i[0]=b; // 부모 클래스 변수에 자식클래스를 넣으면 부모 범위까지만 참조가 됨 // 하지만 오버라이딩으로 이어진 함수는 오버라이딩을 이용해서 자식 클래스의 함수를 실행할 수 있음
		i[1]=c;
		i[2]=d;
		return i;
	}
	
	public static void main(String[] args) {
		Item [] item = method();
		//출력
		for(int i=0; i<item.length; i++)
			item[i].output(); 
	}
}
