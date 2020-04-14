package jdbc.gui;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

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
	public void modify(InfoVO vo) throws SQLException {
		Connection con = null;
		try {
			//2. 연결 객체 얻어오기
			 con = DriverManager.getConnection(url, user, password);
			//3. sql 문장
			String sql = "UPDATE info_tab SET name=?, jumin=?, gender=?, age=?, home=? WHERE tel=?";
			//4. 전송객체 얻어오기
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, vo.getName());
			st.setString(2, vo.getJumin());
			st.setString(3, vo.getGender());
			st.setInt(4, vo.getAge());
			st.setString(5, vo.getHome());
			st.setString(6, vo.getTel());
			//5. 전송하기
			st.executeUpdate();
			
			st.close();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			con.close();
		}
	}

	@Override
	public ArrayList<InfoVO> selectAll() throws SQLException {
		Connection con = null;
		ArrayList<InfoVO> list= null;
		try {
			//2. connection 만들기
			con=DriverManager.getConnection(url, user, password);
			//3.sql 만들기
			String sql = "SELECT * FROM info_tab";
			//4.전송객체 얻어오기
			PreparedStatement st = con.prepareStatement(sql);
			//5.전송
			//	ㄴ int executeUpdate() : INSERT, DELETE, UPDATE
			//	ㄴ ResultSet executeQuery() : SELECT
			ResultSet rs = st.executeQuery();
			list = new ArrayList<InfoVO>();
			while(rs.next()) {
				InfoVO vo = new InfoVO();
				vo.setName(rs.getString("NAME"));
				vo.setJumin(rs.getString("JUMIN"));
				vo.setTel(rs.getString("TEL"));
				vo.setGender(rs.getString("GENDER"));
				vo.setHome(rs.getString("HOME"));
				vo.setAge(rs.getInt("AGE"));
				list.add(vo);
			}
			//6. 닫기
			rs.close();
			st.close();
		}catch(Exception E) {			
		}finally {
			con.close();
		}
		return list;
	}
	@Override
	public void delete(String tel) throws SQLException {
		Connection con = null;
		try {
			con = DriverManager.getConnection(url, user, password);
			
			String sql = "DELETE FROM info_tab WHERE tel=?";
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, tel);
			
			int result = st.executeUpdate();
			
			System.out.println(result + "행이 변경되었습니다.");
			
			st.close();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			con.close();
		}
	}

	@Override
	public InfoVO selectByTel(String tel) throws SQLException{
		Connection con = null;
		InfoVO vo = null;
		try {
			con=DriverManager.getConnection(url, user, password);
			
			String sql = "SELECT * FROM info_tab WHERE tel=?";
			
			PreparedStatement st = con.prepareStatement(sql);
			
			st.setString(1, tel);
			ResultSet rs = st.executeQuery();
			if(rs.next()) 					//결과값이 하나여도 한번은 실행해야함
			{
				vo = new InfoVO();
				vo.setAge(rs.getInt("AGE"));
				vo.setGender(rs.getString("Gender"));
				vo.setHome(rs.getString("HOME"));
				vo.setJumin(rs.getString("JUMIN"));
				vo.setName(rs.getString("NAME"));
				vo.setTel(rs.getString("TEL"));
			}
			rs.close();
			st.close();
		} catch (Exception e) {
			// TODO: handle exception
		}finally {
			con.close();
		}
		return vo;
	}
}