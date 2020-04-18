package t_teamproject.teamproject_02.temp.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import t_teamproject.teamproject_02.temp.config.Configuration;
import t_teamproject.teamproject_02.temp.vo.Employee;

public class EmployeeDaoImpl implements EmployeeDao{
	public EmployeeDaoImpl() throws ClassNotFoundException {
		Class.forName("oracle.jdbc.driver.OracleDriver");
	}
	public Employee selectByID(String id) {
		Employee e = new Employee();
		Connection con = null;
		try {
			con = DriverManager.getConnection(Configuration.url, Configuration.user, Configuration.password);
			String sql = "SELECT e.eid AS eid, e.epassword AS epassword, e.ename AS ename, e.tel AS tel, e.sal AS sal, e.hire_date AS hire_date, p.posname AS posname, e.age AS age FROM EMPLOYEE e INNER JOIN POS p ON e.POSID=p.POSID WHERE e.eid=?";
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
				e.setAge(Integer.parseInt(rs.getString("age")));
			}
			rs.close();
			ps.close();
			
			return e;
		} catch (Exception e1) {
			e1.printStackTrace();
		}finally {
			try {
				con.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		
		return e;
	}
	public ArrayList searchEmployee(int option, String searchWord) {
		ArrayList resultList = new ArrayList();
		Connection con = null;
		try {
			con = DriverManager.getConnection(Configuration.url, Configuration.user, Configuration.password);
			String [] columNames = {"E.ENAME", "E.EID"};
			String sql="SELECT E.ENAME AS ENAME, E.EID AS EID, E.TEL AS ETEL, P.POSNAME AS POSNAME, E.AGE AS EAGE, E.SAL AS SAL, TO_CHAR(E.HIRE_DATE, 'YYYY/MM/DD') AS HIRE_DATE  FROM EMPLOYEE E INNER JOIN POS P ON E.POSID=P.POSID WHERE " + columNames[option] + " LIKE '%' || ? || '%' ";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, searchWord);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				ArrayList temp = new ArrayList();
				temp.add(rs.getString("EID"));
				temp.add(rs.getString("ENAME"));
				temp.add(rs.getString("POSNAME"));
				temp.add(rs.getString("ETEL"));
				temp.add(Integer.toString(rs.getInt("EAGE")));
				temp.add(Integer.toString(rs.getInt("SAL")));
				temp.add(rs.getString("HIRE_DATE"));
				resultList.add(temp);
			}
			rs.close();
			ps.close();
						
		} catch (Exception e) {
			System.out.println("EmployeeDaoImpl_searchEmployee 오류");
			e.printStackTrace();
		}finally {
			try {
				con.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return resultList;		
	}
	public int insertEmployee(Employee vo) {
		int result=0; 
		Connection con = null;
		try {
			con = DriverManager.getConnection(Configuration.url, Configuration.user, Configuration.password);
			String sql = "INSERT INTO EMPLOYEE(EID, EPASSWORD, ENAME, TEL, SAL, HIRE_DATE, AGE, POSID) VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, vo.getId());
			ps.setString(2, vo.getPassword());
			ps.setString(3, vo.getName());
			ps.setString(4, vo.getTel());
			ps.setInt(5, vo.getSal());
			ps.setString(6, vo.getHire_date());
			ps.setInt(7, vo.getAge());
			ps.setInt(8, vo.changePosNameToPosId(vo.getPosition()));
			
			ps.executeUpdate();
			
			ps.close();

		}catch(Exception e) {
			e.printStackTrace();
			return -1;
		}finally {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return 0;
	}
	public int updateEmployee(Employee vo) {
		int result=0;
		Connection con = null;
		try {
			con = DriverManager.getConnection(Configuration.url, Configuration.user, Configuration.password);
			String sql = "UPDATE EMPLOYEE SET ENAME = ?, POSID = ?, TEL = ?, HIRE_DATE = ?, SAL = ?, AGE = ? WHERE EID= ? ";

			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, vo.getName());
			ps.setInt(2, vo.changePosNameToPosId(vo.getPosition())); // 조심
			ps.setString(3, vo.getTel());
			ps.setString(4, vo.getHire_date());
			ps.setInt(5, vo.getSal());
			ps.setInt(6, vo.getAge());
			ps.setString(7, vo.getId());
			result = ps.executeUpdate();
			
			ps.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				con.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return result;
	}
	public int deleteEmployee(String id) {
		int result=0;
		Connection con = null;
		try {
			con = DriverManager.getConnection(Configuration.url, Configuration.user, Configuration.password);
			String sql = "DELETE FROM EMPLOYEE WHERE eid=?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, id);
			result = ps.executeUpdate();
			ps.close();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}
}
