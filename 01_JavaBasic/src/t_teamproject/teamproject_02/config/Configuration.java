package t_teamproject.teamproject_02.config;

public class Configuration {
	// 학원 <-> 집에서 번갈아 작업시 각각의 daoimpl을 찾아가서 하나씩 수정하는 것을 막기위해서 만들었습니다.
	public static final String url = "jdbc:oracle:thin:@192.168.0.17:1521:orcl"; // 학원
//	public static final String url = "jdbc:oracle:thin:@192.168.56.1:1521:xe";
	public static final  String user = "tp2";
	public static final  String password = "tp2";
	
}
