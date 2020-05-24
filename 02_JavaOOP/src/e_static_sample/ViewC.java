package e_static_sample;

public class ViewC {
	DBConnect db; //자동으로 null로 초기화됨 (DBConnect db = null)
	
	public ViewC() {
		db = DBConnect.getInstance();
	}
	public void use() {
		System.out.println("디비 작업중");
	}
}
