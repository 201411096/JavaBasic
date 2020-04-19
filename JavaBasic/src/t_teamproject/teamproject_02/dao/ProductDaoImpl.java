package t_teamproject.teamproject_02.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import t_teamproject.teamproject_02.config.Configuration;
import t_teamproject.teamproject_02.vo.Product;

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
		int result=0; 
		Connection con = null;
		try {
			con = DriverManager.getConnection(Configuration.url, Configuration.user, Configuration.password);
			String sql = "INSERT INTO product(pid, pgroupid, pname, pdetail, pprice) VALUES(PRODUCT_PID_SEQ.NEXTVAL, ?, ?, ?, ?)";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, vo.changeGroupNametoPGID(vo.getGroupName())); // 그룹이름을 그룹번호로 변경
			ps.setString(2, vo.getName());
			ps.setString(3, vo.getDetail());
			ps.setInt(4, vo.getPrice());
			ps.executeUpdate();
			
			ps.close();
		} catch (Exception e) {
			e.printStackTrace();
			return -1; // 관리 패널에 오류임을 알림
		}finally {
			try {
				con.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return result;
	}

	@Override
	public int updateProduct(Product vo) {
		int result=0;
		Connection con = null;
		try {
			con = DriverManager.getConnection(Configuration.url, Configuration.user, Configuration.password);
			String sql = "UPDATE product SET pgroupid=?, pdetail=?, pname=?, pprice=? WHERE PID=?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, vo.changeGroupNametoPGID(vo.getGroupName()));
			ps.setString(2, vo.getDetail());
			ps.setString(3, vo.getName());
			ps.setInt(4, vo.getPrice());
			ps.setInt(5, vo.getId());
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

	@Override
	public int deleteProduct(int id) {
		int result=0;
		Connection con = null;
		try {
			con = DriverManager.getConnection(Configuration.url, Configuration.user, Configuration.password);
			String sql = "DELETE FROM PRODUCT WHERE pid=?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, id);
			result = ps.executeUpdate();
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
	public ArrayList<String> selectProductNameOrderByName() {
		ArrayList<String>  resultArrayList = new ArrayList<String>();
		Connection con = null;
		try {
			con = DriverManager.getConnection(Configuration.url, Configuration.user, Configuration.password);
			String sql = "SELECT PNAME FROM PRODUCT ORDER BY PNAME";
			PreparedStatement ps = con.prepareStatement(sql);
			
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				String temp = rs.getString("PNAME");
				resultArrayList.add(temp);
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
		return resultArrayList;
	}
	@Override
	public ArrayList<Product> searchProductByGroupNameAsc(String searchWord) { //그룹이름으로 찾아서 PID순으로 정렬 후 가져옴
		ArrayList<Product> resultList = new ArrayList();
		Connection con = null;
		try {
			con = DriverManager.getConnection(Configuration.url, Configuration.user, Configuration.password);
			String sql = "SELECT P.PID AS PID, P.PGROUPID AS PGID, P.PNAME AS PNAME, P.PDETAIL AS PDETAIL, P.PPRICE AS PPRICE FROM PRODUCT P INNER JOIN PRODUCTGROUP PG ON P.PGROUPID=PG.PGROUPID WHERE PG.PGROUPNAME=? ORDER BY P.PID";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, searchWord);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				Product temp = new Product();
				temp.setId(rs.getInt("PID"));
				temp.setGroupName(temp.changePGIDtoGroupName(rs.getInt("PGID")));
				temp.setName(rs.getString("PNAME"));
				temp.setDetail(rs.getString("PDETAIL"));
				temp.setPrice(rs.getInt("PPRICE"));
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
}
