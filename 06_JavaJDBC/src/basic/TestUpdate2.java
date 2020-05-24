package basic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;


public class TestUpdate2 {
	public static void main(String[] args) {
		int deptno=20;
		int salInc =600;
		try {
			// 1. 드라이버를 메모리에 로딩
			// new oracle.jdbc.driver.OracleDriver();
			Class.forName("oracle.jdbc.driver.OracleDriver"); // Class.forName -> String과 같은 이름의 클래스를 생성
			System.out.println("성공");

			// 2. Connection 얻어오기
//			Connection con = DriverManager.getConnection("jdbc:oracle:thin:@192.168.0.17:1521:orcl", "hrr", "hrr");//url(ip, port, sid), user password
			Connection con = DriverManager.getConnection("jdbc:oracle:thin:@192.168.0.17:1521:orcl", "scott", "scottscott");//url(ip, port, sid), user password
			//Driver를 한번 생성하면 DriverManager가 관리해줌
			
			// 3. SQL 문장
//			String sql = "UPDATE EMP SET SAL=SAL-500 WHERE DEPTNO=20 ";
			String sql = "UPDATE EMP SET SAL=SAL+? WHERE DEPTNO=?";

			
			// 4. 전송객체 얻어오기
//			Statement st = con.createStatement();
			PreparedStatement st = con.prepareStatement(sql);
			st.setInt(1, salInc);
			st.setInt(2,  deptno);
			// 5. 전송
			// -executeUpdate() : INSERT/UPDATE/DELETE -->> return int			(몇개가 변경됬는지)
			// -executeQuery() : SELECT				   -->> return ResultSet	(결과값의 집합)
			
			int result = st.executeUpdate();
			System.out.println(result + "행을 실행합니다.");
			st.close();
			con.close();
		} catch (Exception e) {
			System.out.println("실패" + e.getMessage());
//			e.printStackTrace();
		}
		
	}
}
