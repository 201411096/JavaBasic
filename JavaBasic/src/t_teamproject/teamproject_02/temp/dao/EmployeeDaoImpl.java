package t_teamproject.teamproject_02.temp.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import t_teamproject.teamproject_02.temp.vo.Employee;

public class EmployeeDaoImpl implements EmployeeDao{
//	private String url = "jdbc:oracle:thin:@192.168.0.17:1521:orcl";
	private String url = "jdbc:oracle:thin:@192.168.56.1:1521:xe";
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
