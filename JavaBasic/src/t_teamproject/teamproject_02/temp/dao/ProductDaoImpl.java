package t_teamproject.teamproject_02.temp.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import t_teamproject.teamproject_02.temp.config.Configuration;
import t_teamproject.teamproject_02.temp.vo.Product;

public class ProductDaoImpl implements ProductDao{
	public ProductDaoImpl() throws ClassNotFoundException {
		Class.forName("oracle.jdbc.driver.OracleDriver");
	}

	@Override
	public Product selectByID(int id) {
		Product p = new Product();
		Connection con = null;
		try {
			con = DriverManager.getConnection(Configuration.url, Configuration.user, Configuration.password);
			String sql = "SELECT p.pid AS PID, p.pgroupid AS PGROUPID, p.pname AS PNAME, p.pprice AS PPRICE, p.pdetail AS PDETAIL FROM PRODUCT P WHERE PID = ?";
			
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, id);
			
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				p.setId(rs.getInt("PID"));
				p.setGroupName(p.changePGIDtoGroupName(rs.getInt("PGROUPID")));
				p.setName(rs.getString("PNAME"));
				p.setPrice(rs.getInt("PPRICE"));
				p.setDetail(rs.getString("PDETAIL"));
			}
			rs.close();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				con.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return p;
	}

	@Override
	public ArrayList searchProduct(int option, String searchWord) {
		ArrayList resultList = new ArrayList();
		Connection con = null;
		try {
			con = DriverManager.getConnection(Configuration.url, Configuration.user, Configuration.password);
			String [] columNames = {"P.PNAME", "P.PDETAIL"};
			String sql="SELECT P.PID AS PID, PG.PGROUPNAME AS PGROUPNAME, P.PNAME AS PNAME, P.PPRICE AS PPRICE FROM PRODUCT P INNER JOIN PRODUCTGROUP PG ON P.PGROUPID=PG.PGROUPID  WHERE " + columNames[option] + " LIKE '%' || ? || '%' ";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, searchWord);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				ArrayList temp = new ArrayList();
				temp.add(Integer.toString(rs.getInt("PID")));
				temp.add(rs.getString("PGROUPNAME"));
				temp.add(rs.getString("PNAME"));
				temp.add(Integer.toString(rs.getInt("PPRICE")));
				resultList.add(temp);
			}
			rs.close();
			ps.close();
		}catch(Exception e) {
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

	@Override
	public int insertProduct(Product vo) {
		return 0;
	}

	@Override
	public int updateProduct(Product vo) {
		return 0;
	}

	@Override
	public int deleteProduct(int id) {
		return 0;
	}
	
}
