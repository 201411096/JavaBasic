package t_teamproject.teamproject_02.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import t_teamproject.teamproject_02.config.Configuration;

public class ProductManagementDaoImpl implements ProductManagementDao{
	
	public ProductManagementDaoImpl() throws ClassNotFoundException{
		Class.forName("oracle.jdbc.driver.OracleDriver");
	}
	@Override
	public int purchaseProductByName(String name, int cnt) {
		int result = 0;
		Connection con = null;		
		try {
			con = DriverManager.getConnection(Configuration.url, Configuration.user, Configuration.password);
			String sql = "INSERT INTO PRODUCTMANAGEMENT(PMANAGEMENTID, PID, PDATE) VALUES (PM_PMANAGEMENTID_SEQ.nextval, (SELECT PID FROM PRODUCT WHERE PNAME=?), SYSDATE)";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, name);
			for(int i=0; i<cnt; i++)
			{
				ps.executeUpdate();
			}
			ps.close();
		} catch (Exception e) {
			e.printStackTrace();
			return -1; // 에러 발생시 -1반환
		}finally {
			try {
				con.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return result;
	}
	public ArrayList<ArrayList> productCount(){
		ArrayList resultList = new ArrayList();
		Connection con = null;
		try {
			con = DriverManager.getConnection(Configuration.url, Configuration.user, Configuration.password);
			String sql = "SELECT P.PID AS PID , P.PNAME AS PNAME, COUNT(*) AS COUNT FROM PRODUCTMANAGEMENT PM INNER JOIN PRODUCT P ON p.pid=pm.pid  GROUP BY P.PID, P.PNAME ORDER BY P.PID";
			PreparedStatement ps = con.prepareStatement(sql);
			
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				ArrayList temp = new ArrayList();
				temp.add(rs.getInt("COUNT"));
				temp.add(rs.getString("PNAME"));
				resultList.add(temp);
			}
			
		} catch (Exception e) {
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
}
