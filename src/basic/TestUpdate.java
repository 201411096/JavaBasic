package basic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;


public class TestUpdate {
	public static void main(String[] args) {
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
//			String sql = "UPDATE EMP SET ename='홍길숙', sal=4500 WHERE empno=9001"; //자바에서 오라클로 보낼떄는 뒤에 ";"없이 보냄
//			String sql = "DELETE FROM emp_copy";
			String sql = "UPDATE EMP SET SAL=SAL-500 WHERE DEPTNO=20 ";
/*			************************************************************************************************************************
 * 			************************************************************************************************************************
			오라클 내부에서 commit이 안된 상태에서 실행을 하면 무한루프를 돌면서 결과가 안 나올 수 있음
			************************************************************************************************************************
			************************************************************************************************************************/			
//			String sql = "UPDATE EMP SET ENAME ='홍길동동' WHERE EMPNO=8000";
			
			// 4. 전송객체 얻어오기
			Statement st = con.createStatement();
			
			// 5. 전송
			// -executeUpdate() : INSERT/UPDATE/DELETE -->> return int			(몇개가 변경됬는지)
			// -executeQuery() : SELECT				   -->> return ResultSet	(결과값의 집합)
			int result = st.executeUpdate(sql);
			System.out.println(result + "행을 실행합니다.");
			st.close();
			con.close();
		} catch (Exception e) {
			System.out.println("실패" + e.getMessage());
//			e.printStackTrace();
		}
		
	}
}
