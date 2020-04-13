package basic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class TestSelect {
	public static void main(String[] args) {
		try {
			//1. 드라이버를 메모리에 로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("성공");
			//2. connection 얻어오기
			Connection con = DriverManager.getConnection("jdbc:oracle:thin:@192.168.0.17:1521:orcl", "scott", "scottscott");//url(ip, port, sid), user password
			//3. sql문장
			String sql = "SELECT empno, ename, sal, job FROM emp";
			//4. 전송객체 얻어오기
			Statement st = con.createStatement();
			//5. 결과를 받아옴 
			ResultSet rs = st.executeQuery(sql);
			//6. 결과물 확인
			while(rs.next()) { // 다음 레코드가 있으면 true 없으면 false
				System.out.print(rs.getInt("EMPNO") + "\t");
				System.out.print(rs.getString("ENAME") + "\t");
				System.out.print(rs.getInt("SAL") + "\t");
				System.out.println(rs.getString("JOB") + "\t");
			}
			//7.닫기
			rs.close();
			st.close();
			con.close();
		}catch (Exception e) {
			System.out.println("실패:" + e.getMessage());
		}
	}
}
