package basic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class TestInsert2 {
	public static void main(String[] args) {
		//화면에서 입력값 받아서 저장한 변수 상상하기
		int empno = 9003;
		String ename = "김순이";
		int sal = 7000;
		Connection con=null;
		try {
			// 1. 드라이버를 메모리에 로딩
			//new oracle.jdbc.driver.OracleDriver();
			Class.forName("oracle.jdbc.driver.OracleDriver"); // Class.forName -> String과 같은 이름의 클래스를 생성
			System.out.println("성공");

			// 2. Connection 얻어오기
			con = DriverManager.getConnection("jdbc:oracle:thin:@192.168.0.17:1521:orcl", "hrr", "hrr");//url(ip, port, sid), user password
			//Driver를 한번 생성하면 DriverManager가 관리해줌
			
			//3. SQL 문장
//			String sql = "INSERT	"
//					+ "INTO		"
//					+ "emp(empno,ename,sal)		"
//					+ " VALUES("+ empno + ", '"+ ename +"', "+sal +")"; //자바에서 오라클로 보낼떄는 뒤에 ";"없이 보냄
			String sql = "INSERT	"
					+ "INTO		"
					+ "emp(empno,ename,sal)		"
					+ " VALUES(?, ?, ?)";
			System.out.println(sql);
			//4. 전송객체 얻어오기
//			Statement st = con.createStatement();
			PreparedStatement st = con.prepareStatement(sql); //statement와 다르게 sql을 인자로 받음
			st.setInt(1, empno); // sql은 1번부터..
			st.setString(2, ename);
			st.setInt(3, sal);
//			st.executeUpdate(sql);
			st.executeUpdate(); //preparedstatement는 sql이 먼저 들어가기 때문에 여기서는 들어가지 않음  --> 에러를 찾아내기 힘듬
			st.close();

		} catch (Exception e) {
			System.out.println("실패" + e.getMessage());
//			e.printStackTrace();
		}finally {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	}
}
