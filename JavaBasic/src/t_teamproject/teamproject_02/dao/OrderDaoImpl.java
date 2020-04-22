package t_teamproject.teamproject_02.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.StringTokenizer;

import t_teamproject.teamproject_02.config.Configuration;
import t_teamproject.teamproject_02.vo.Product;

public class OrderDaoImpl implements OrderDao{
	public OrderDaoImpl() throws ClassNotFoundException {
		Class.forName("oracle.jdbc.driver.OracleDriver");
	}
	public int insertOrder(String productStringList [], ArrayList<Product> calProductList) {
		int result=0;
		Connection con = null;
		String productNameList[] = new String[productStringList.length];
		int productIdList[] = new int[productStringList.length];
		int productCountList[] = new int[productStringList.length];
		int totalPrice=0;
		for(int i=0; i<productStringList.length; i++)
		{
			StringTokenizer st = new StringTokenizer(productStringList[i]);
			productNameList[i] = st.nextToken(); //이걸 id로 바꿔야됨
			productCountList[i] = Integer.parseInt(st.nextToken());
			totalPrice+=Integer.parseInt(st.nextToken());
		}
		for(int i=0; i<productNameList.length; i++) //제품이름리스트에서 제품 아이디리스트를 가져옴
		{
			for(int j=0; j<calProductList.size(); j++)
			{
				if(productNameList[i].equals(calProductList.get(j).getName()))
				{
					productIdList[i]=calProductList.get(j).getId();
				}
			}
		}
		try {
			con = DriverManager.getConnection(Configuration.url, Configuration.user, Configuration.password);
			String sqlToOrderList = "INSERT INTO ORDERLIST(OLID, TOTALPRICE) VALUES(ORDERLIST_OLID.NEXTVAL, ?)";
			PreparedStatement ps1 = con.prepareStatement(sqlToOrderList);
			ps1.setInt(1, totalPrice);
			ps1.executeUpdate();
			ps1.close();
			for(int i=0; i<productStringList.length; i++) {
				String sqlToOrdered = "INSERT INTO ORDERED(OID, PID, OCNT, ODATE, OLID) VALUES(ORDERED_OID.NEXTVAL, ?, ?, SYSDATE, ORDERLIST_OLID.CURRVAL)";
				PreparedStatement ps2 = con.prepareStatement(sqlToOrdered);
				ps2.setInt(1, productIdList[i]);
				ps2.setInt(2, productCountList[i]);
				ps2.executeUpdate();
				ps2.close();
				
				String sqlToProductManagement = " DELETE FROM PRODUCTMANAGEMENT WHERE PMANAGEMENTID IN ( SELECT ext.PMANAGEMENTID FROM (SELECT * FROM PRODUCTMANAGEMENT WHERE PID=? ORDER BY pdate ASC, PMANAGEMENTID ASC) ext WHERE ROWNUM <= " + Integer.toString(productCountList[i]) + " ) ";
				PreparedStatement ps3 = con.prepareStatement(sqlToProductManagement);
				ps3.setInt(1, productIdList[i]);
				ps3.executeUpdate();
				ps3.close();
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
		return result;
	}
	@Override
	public ArrayList<ArrayList> getSalesPerformanceGroupByDay() {
		ArrayList resultList = new ArrayList();
		Connection con = null;
		try {
			con = DriverManager.getConnection(Configuration.url, Configuration.user, Configuration.password);
			String sql ="SELECT TO_CHAR(ODATE) AS DAY, SUM(OL.TOTALPRICE) AS PRICE FROM ORDERED O INNER JOIN ORDERLIST OL ON O.OLID=OL.OLID WHERE TO_CHAR(ODATE, 'YYMM')=TO_CHAR(SYSDATE, 'YYMM') GROUP BY TO_CHAR(ODATE) ORDER BY TO_CHAR(ODATE)";
			PreparedStatement ps = con.prepareStatement(sql);
			
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				ArrayList temp = new ArrayList();
				temp.add(rs.getInt("PRICE"));
				temp.add(rs.getString("DAY"));
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
	@Override
	public ArrayList<ArrayList> getSalesPerformanceGroupByMonth() {
		ArrayList resultList = new ArrayList();
		Connection con = null;
		try {
			con = DriverManager.getConnection(Configuration.url, Configuration.user, Configuration.password);
			String sql ="SELECT TO_CHAR(ODATE, 'YY.MM') AS MONTH, SUM(OL.TOTALPRICE) AS PRICE FROM ORDERED O INNER JOIN ORDERLIST OL ON O.OLID=OL.OLID WHERE TO_CHAR(ODATE, 'YY')=TO_CHAR(SYSDATE, 'YY') GROUP BY TO_CHAR(ODATE, 'YY.MM') ORDER BY TO_CHAR(ODATE, 'YY.MM')";
			PreparedStatement ps = con.prepareStatement(sql);
			
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				ArrayList temp = new ArrayList();
				temp.add(rs.getInt("PRICE"));
				temp.add(rs.getString("MONTH"));
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
	@Override
	public ArrayList<ArrayList> getSalesPerformanceGroupByYear() {
		ArrayList resultList = new ArrayList();
		Connection con = null;
		try {
			con = DriverManager.getConnection(Configuration.url, Configuration.user, Configuration.password);
			String sql ="SELECT TO_CHAR(ODATE, 'YYYY') AS YEAR, SUM(OL.TOTALPRICE) AS PRICE FROM ORDERED O INNER JOIN ORDERLIST OL ON O.OLID=OL.OLID GROUP BY TO_CHAR(ODATE, 'YYYY') ORDER BY TO_CHAR(ODATE, 'YYYY')";
			PreparedStatement ps = con.prepareStatement(sql);
			
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				ArrayList temp = new ArrayList();
				temp.add(rs.getInt("PRICE"));
				temp.add(rs.getString("YEAR"));
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
	@Override
	public ArrayList<ArrayList> getSalesPerformanceGroupByName() {
		ArrayList resultList = new ArrayList();
		Connection con = null;
		try {
			con = DriverManager.getConnection(Configuration.url, Configuration.user, Configuration.password);
			String sql ="SELECT P.PNAME AS PNAME, SUM(OL.TOTALPRICE) AS PRICE FROM ORDERED O INNER JOIN ORDERLIST OL ON O.OLID=OL.OLID INNER JOIN PRODUCT P ON O.PID=P.PID GROUP BY O.PID, P.PNAME order by O.PID";
			PreparedStatement ps = con.prepareStatement(sql);
			
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				ArrayList temp = new ArrayList();
				temp.add(rs.getInt("PRICE"));
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
