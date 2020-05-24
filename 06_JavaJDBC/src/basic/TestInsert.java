package basic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;


public class TestInsert {
	public static void main(String[] args) {
		try {
			// 1. 드라이버를 메모리에 로딩
			//new oracle.jdbc.driver.OracleDriver();
			Class.forName("oracle.jdbc.driver.OracleDriver"); // Class.forName -> String과 같은 이름의 클래스를 생성
			System.out.println("성공");

			// 2. Connection 얻어오기
			Connection con = DriverManager.getConnection("jdbc:oracle:thin:@192.168.0.17:1521:orcl", "hrr", "hrr");//url(ip, port, sid), user password
			//Driver를 한번 생성하면 DriverManager가 관리해줌
			
			//3. SQL 문장
			String sql = "INSERT INTO emp(empno,ename,sal) VALUES(9001, '맹순이', 4000)"; //자바에서 오라클로 보낼떄는 뒤에 ";"없이 보냄
			
			//4. 전송객체 얻어오기
			Statement st = con.createStatement();
			st.executeUpdate(sql);
			st.close();
			con.close();
		} catch (Exception e) {
			System.out.println("실패" + e.getMessage());
//			e.printStackTrace();
		}
		
	}
}
