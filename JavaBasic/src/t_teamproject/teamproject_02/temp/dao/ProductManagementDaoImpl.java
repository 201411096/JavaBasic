package t_teamproject.teamproject_02.temp.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import t_teamproject.teamproject_02.temp.config.Configuration;

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
			System.out.println("다오의 name확인" + name);
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
}
