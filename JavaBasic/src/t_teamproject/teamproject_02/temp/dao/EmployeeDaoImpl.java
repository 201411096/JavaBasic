package t_teamproject.teamproject_02.temp.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import t_teamproject.teamproject_02.temp.vo.Employee;

public class EmployeeDaoImpl implements EmployeeDao{
	private String url = "jdbc:oracle:thin:@192.168.0.17:1521:orcl";
//	private String url = "jdbc:oracle:thin:@192.168.56.1:1521:xe";
	private String user = "tp2";
	private String password = "tp2";
	public EmployeeDaoImpl() throws ClassNotFoundException {
		Class.forName("oracle.jdbc.driver.OracleDriver");
	}
	public Employee selectByID(String id) {
		Employee e = new Employee();
		Connection con;
		try {
			con = DriverManager.getConnection(url, user, password);
			String sql = "SELECT e.eid AS eid, e.epassword AS epassword, e.ename AS ename, e.tel AS tel, e.sal AS sal, e.hire_date AS hire_date, p.posname AS posname FROM EMPLOYEE e INNER JOIN POS p ON e.POSID=p.POSID WHERE e.eid=?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, id);
			
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				e.setId(rs.getString("eid"));
				e.setPassword(rs.getString("epassword"));
				e.setTel(rs.getString("tel"));
				e.setName(rs.getString("ename"));
				e.setSal(rs.getInt("sal"));
				e.setHire_date(rs.getString("hire_date"));
				e.setPosition(rs.getString("posname"));
				System.out.println(e.getId());
				System.out.println(e.getPassword());
			}
			rs.close();
			ps.close();
			con.close();
			return e;
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		
		return e;
	}
	public int insertEmployee(Employee vo) {
		int result=0;
		
		return result;
	}
	public int updateEmployee(Employee vo) {
		int result=0;
		
		return result;
	}
	public int deleteEmployee(String id) {
		int result=0;
		
		return result;
	}
}