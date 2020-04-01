package y_teamproject;

public class Main {

	public static void main(String[] args) {
		Menu menu = new Menu();
		menu.makeMenuList(); // 메뉴리스트 생성
		
		MainFrame m = new MainFrame();
		m.display();
	}

}
