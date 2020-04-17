package model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import model.CustomerDao;
import model.vo.Customer;

public class CustomerModel implements CustomerDao{
	String url = "jdbc:oracle:thin:@192.168.0.17:1521:orcl";
	String user = "tp2";
	String pass = "tp2";
	
	
	public CustomerModel() throws Exception{
	 	// 1. 드라이버로딩
		Class.forName("oracle.jdbc.driver.OracleDriver");
		
	}
	
	public void insertCustomer(Customer vo) throws Exception{
		// 2. Connection 연결객체 얻어오기
		Connection con = DriverManager.getConnection(url, user, pass);
		// 3. sql 문장 만들기
		String sql = "INSERT INTO video_shop_customer(id, name, tel_1, tel_2, address, email) VALUES(video_shop_customer_id_seq.nextval, ?, ?, ?, ?, ?) ";
		// 4. sql 전송객체 (PreparedStatement)
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, vo.getCustName());
		ps.setString(2, vo.getCustTel1());
		ps.setString(3, vo.getCustTel2());
		ps.setString(4, vo.getCustAddr());
		ps.setString(5, vo.getCustEmail());
		// 5. sql 전송
		int result = ps.executeUpdate();
			
		// 6. 닫기 
		ps.close();
		con.close();

	}
	
	public Customer selectByTel(String tel) throws Exception{
		Customer dao = new Customer();
		// 2. Connection 연결객체 얻어오기
		Connection con = DriverManager.getConnection(url, user, pass);
		// 3. sql 문장 만들기
		String sql = "SELECT name, tel_1, tel_2, address, email FROM video_shop_customer WHERE tel_1 = ?";
		// 4. sql 전송객체 (PreparedStatement)
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, tel);
		// 5. sql 전송
		ResultSet rs = ps.executeQuery();
		
		while(rs.next()) {
			dao.setCustName(rs.getString("name"));
			dao.setCustTel1(rs.getString("tel_1"));
			dao.setCustTel2(rs.getString("tel_2"));
			dao.setCustAddr(rs.getString("address"));
			dao.setCustEmail(rs.getString("email"));
		}
		rs.close();
		ps.close();
		con.close();
		return dao;
	}
	
	public int updateCustomer(Customer vo) throws Exception{
		int result = 0;
		// 2. Connection 연결객체 얻어오기
		Connection con = DriverManager.getConnection(url, user, pass);
		// 3. sql 문장 만들기
		String sql = "UPDATE video_shop_customer SET NAME = ?, TEL_2 = ?, ADDRESS = ?, EMAIL = ?";
		// 4. sql 전송객체 (PreparedStatement)
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, vo.getCustName());
		ps.setString(2, vo.getCustTel2());
		ps.setString(3, vo.getCustAddr());
		ps.setString(4, vo.getCustEmail());
		
		result = ps.executeUpdate();
		
		// 6. 닫기 
		ps.close();
		con.close();

		return result;
	}
}
