package basic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class TestSelect3 {
	public static void main(String[] args) {
		try {
			//1. 드라이버를 메모리에 로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("성공");
			//2. connection 얻어오기
			Connection con = DriverManager.getConnection("jdbc:oracle:thin:@192.168.0.17:1521:orcl", "hr", "hrhr");//url(ip, port, sid), user password
			//3. sql문장
//이전 방식		String sql = "SELECT e.employee_id id, e.first_name||' '||e.last_name ename, d.department_name dname FROM EMPLOYEES e, departments d WHERE salary>=3000 AND e.department_id=d.department_id"; // 함수나 연산이 있다면 별칭을 주기
/*ANSI 방식*/String sql = "SELECT e.employee_id id, e.first_name||' '||e.last_name ename,d.department_name dname FROM EMPLOYEES e INNER JOIN departments d ON e.department_id=d.department_id WHERE salary>=3000";
			//4. 전송객체 얻어오기
			Statement st = con.createStatement();
			//5. 결과를 받아옴
			ResultSet rs = st.executeQuery(sql);
			//6. 결과를 확인
			while(rs.next()) {
				System.out.print(rs.getInt("id") +"\t");
				System.out.print(rs.getString("ename") +"\t");
				System.out.println(rs.getString("dname") +"\t");
			}
			rs.close();
			st.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
