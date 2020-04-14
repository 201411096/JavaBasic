package jdbc.gui;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class InfoModelImpl implements InfoModel{
	String url = "jdbc:oracle:thin:@192.168.0.17:1521:orcl";
	String user = "scott";
	String password = "scottscott";
	public InfoModelImpl() throws Exception {
		//1. 드라이버 로딩
		Class.forName("oracle.jdbc.driver.OracleDriver"); // Class.forName -> String과 같은 이름의 클래스를 생성
	}
	
	@Override
	public void insert(InfoVO vo) throws SQLException {
		Connection con = null;
		try {
			//2. 연결 객체 얻어오기
			 con = DriverManager.getConnection(url, user, password);
			//3. sql 문장
			String sql = "INSERT INTO info_tab(name, jumin, tel, gender, age, home)		"
					+ " VALUES(?, ?, ?, ?, ?, ?)";
			//4. 전송객체 얻어오기
			PreparedStatement st = con.prepareStatement(sql);
			
			st.setString(1, vo.getName());
			st.setString(2, vo.getJumin());
			st.setString(3, vo.getTel());
			st.setString(4, vo.getGender());
			st.setInt(5, vo.getAge());
			st.setString(6, vo.getHome());
			//5. 전송하기
			st.executeUpdate();
			st.close();			
		}finally {
			con.close();
		}
		
		
		
	}
	@Override
	public void selectAll() throws SQLException {
	}
	@Override
	public void delete() throws SQLException {	
	}
	
}
