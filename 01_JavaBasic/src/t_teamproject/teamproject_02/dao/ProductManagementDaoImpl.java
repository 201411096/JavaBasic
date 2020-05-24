package t_teamproject.teamproject_02.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import t_teamproject.teamproject_02.config.Configuration;
import t_teamproject.teamproject_02.vo.Product;

public class ProductManagementDaoImpl implements ProductManagementDao{
	
	public ProductManagementDaoImpl() throws ClassNotFoundException{
		Class.forName("oracle.jdbc.driver.OracleDriver");
	}
	/* 함수이름 : purchaseProductByName
	 * 인자값 : 도매점으로부터 구매할 제품의 이름과 갯수
	 * 반환값 : 정상 종료시 0반환 예외발생시 -1 반환
	 * 함수설명 : 제품 입고(도매점에서 구매)
	 */
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
	/* 함수이름 : productCount
	 * 인자값 : 없음
	 * 반환값 : 정상 종료시 resultList 반환 예외발생시 null 반환
	 * 함수설명 : 제품별 제품이름과 재고 개수를 가져옴
	 */
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
	/* 함수이름 : getPidCountFromproduct
	 * 인자값 : 없음
	 * 반환값 : 정상 종료시 resultList 반환 예외발생시 null 반환
	 * 함수설명 : 제품별 제품아이다와 재고 개수를 가져옴
	 */
	public ArrayList<ArrayList> getPidCountFromproduct(){
		ArrayList resultList = new ArrayList();
		Connection con = null;
		try {
			con = DriverManager.getConnection(Configuration.url, Configuration.user, Configuration.password);
			String sql = "SELECT P.PID AS PID , P.PNAME AS PNAME, COUNT(*) AS COUNT FROM PRODUCTMANAGEMENT PM INNER JOIN PRODUCT P ON p.pid=pm.pid  GROUP BY P.PID, P.PNAME ORDER BY P.PID";
			PreparedStatement ps = con.prepareStatement(sql);
			
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				ArrayList temp = new ArrayList();
				temp.add(rs.getInt("PID"));
				temp.add(rs.getInt("COUNT"));
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
	/* 함수이름 : getAllProduct
	 * 인자값 : 없음
	 * 반환값 : 정상 종료시 resultList 반환 예외발생시 null 반환
	 * 함수설명 : 제품의 정보를 전부 가져옴
	 */
	public ArrayList<Product> getAllProduct(){
		ArrayList resultList = new ArrayList();
		Connection con = null;
		try {
			con = DriverManager.getConnection(Configuration.url, Configuration.user, Configuration.password);
			String sql = "SELECT P.PID AS PID, P.PNAME AS PNAME, P.PGROUPID AS PGROUPID, P.PDETAIL AS PDETAIL, P.PPRICE AS PPRICE FROM PRODUCT P INNER JOIN PRODUCTGROUP PG ON P.PGROUPID=PG.PGROUPID ORDER BY P.PID";
			PreparedStatement ps = con.prepareStatement(sql);
			
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				Product p = new Product();
				p.setId(rs.getInt("PID"));
				p.setName(rs.getString("PNAME"));
				p.setGroupName(p.changePGIDtoGroupName(rs.getInt("PGROUPID")));
				p.setDetail(rs.getString("PDETAIL"));
				p.setPrice(rs.getInt("PPRICE"));
				resultList.add(p);
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
