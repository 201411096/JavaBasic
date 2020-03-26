package e_static_sample;

public class ViewA {
	DBConnect db; //자동으로 null로 초기화됨 (DBConnect db = null)
	
	public ViewA() {
		db = DBConnect.getInstance();
	}
	public void use() {
		System.out.println("디비 작업중");
	}
}
