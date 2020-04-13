package basic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class TestSelect2 {
	public static void main(String[] args) {
		try {
			//1. 드라이버를 메모리에 로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("성공");
			//2. connection 얻어오기
			Connection con = DriverManager.getConnection("jdbc:oracle:thin:@192.168.0.17:1521:orcl", "scott", "scottscott");//url(ip, port, sid), user password
			//3. sql문장
			String sql = "SELECT count(*) AS cnt FROM EMP"; // 함수나 연산이 있다면 별칭을 주기
			//4. 전송객체 얻어오기
			Statement st = con.createStatement();
			//5. 결과를 받아옴 
			ResultSet rs = st.executeQuery(sql);
			//6. 결과물 확인
			rs.next();
			System.out.println("총 "+ rs.getInt("CNT") + "명입니다."); //sql 대소문자를 안 가림
			//7.닫기
			rs.close();
			st.close();
			con.close();
		}catch (Exception e) {
			System.out.println("실패:" + e.getMessage());
		}
	}
}
