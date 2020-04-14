package t_teamproject_02;

public class ProjectModelImpl implements ProjectModel{
	String url = "jdbc:oracle:thin:@192.168.0.17:1521:orcl";
	String user = "tp2";
	String password = "tp2";
	
	public ProjectModelImpl() throws Exception {
		//1. 드라이버 로딩
		Class.forName("oracle.jdbc.driver.OracleDriver"); // Class.forName -> String과 같은 이름의 클래스를 생성
	}
}
