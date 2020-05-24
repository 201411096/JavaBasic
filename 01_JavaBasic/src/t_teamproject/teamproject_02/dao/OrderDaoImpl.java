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
	/* 함수이름 : insertOrder
	 * 인자값 : productStringList(제품의 이름과, 제품별 구매갯수, 제품별 가격합이 담긴 문자열 배열) , calProductList(db에 존재하는  판매가능한 제품 정보를 담은 배열)
	 * 반환값 : 정상 종료시 0반환, 예외 발생시 -1 반환
	 * 함수설명 : 제품 주문
	 * 		  	ㄴ  판매항목들을 묶어줄수있는 영수증 테이블에  영수증식별번호와 총가격 삽입 (한 주문에 포함된 여러 메뉴들을 묶어줌)
	 * 			ㄴ  주문한 메뉴의 개수만큼  각 판매항목당 판매내역을 삽입(제품 번호, 판매갯수, 판매날짜)
	 * 			ㄴ  식재료 관리 테이블에서 각 판매항목당 판매된 갯수만큼 먼저 입고된 식재료를 삭제(오래된 식재료부터 판매)
	 */
	public int insertOrder(String productStringList [], ArrayList<Product> calProductList) {
		int result=0;
		Connection con = null;
		String productNameList[] = new String[productStringList.length];			//	productStringList에서 제품의 이름들을 받아올 배열(제품의 이름에 공백이 포함될 수 있다는 것을 생각하지 못한채 Stringtokenizer를 이용해서 에러가 발생할 수 있습니다. 
		int productIdList[] = new int[productStringList.length];					//	calProductList에서 제품의 식별번호를 받아올 배열
		int productCountList[] = new int[productStringList.length];					//	productStringList에서 제품의 판매 갯수들을 받아올 배열
		int totalPrice=0;															// 	주문의 총가격을 담을 변수
		
		for(int i=0; i<productStringList.length; i++)								//  productStringList을 파싱하는 과정( 판매 물품과 물품별 개수, 총가격을 받아옴)
		{
			StringTokenizer st = new StringTokenizer(productStringList[i]);
			productNameList[i] = st.nextToken(); 									
			productCountList[i] = Integer.parseInt(st.nextToken());
			totalPrice+=Integer.parseInt(st.nextToken());
		}
		for(int i=0; i<productNameList.length; i++) 								//productNameList에서 productIdList를 뽑아옴
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
				System.out.println(productIdList[i]);
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
			return -1;
		}finally {
			try {
				con.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return result;
	}
	/* 함수이름 : getSalesPerformanceGroupByDay
	 * 인자값 : 없음
	 * 반환값 : 정상 종료시 resultList 반환, 예외 발생시 null 반환
	 * 함수설명 : 이번달의 일별 매출요약을 가져오는 함수(1월 31일이면 1월1일부터 1월31일까지, 2월2일이면 2월1일부터 2월 2일까지의 매출)
	 */
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
	/* 함수이름 : getSalesPerformanceGroupByMonth
	 * 인자값 : 없음
	 * 반환값 : 정상 종료시 resultList 반환, 예외 발생시 null 반환
	 * 함수설명 : 이번년의 월별 매출요약을 가져오는 함수(2월이면 1월부터 2월까지, 12일이면 1월부터 12월까지의 매출)
	 */
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
	/* 함수이름 : getSalesPerformanceGroupByYear
	 * 인자값 : 없음
	 * 반환값 : 정상 종료시 resultList 반환, 예외 발생시 null 반환
	 * 함수설명 : 연도별 매출요약을 가져오는 함수
	 */
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
	/* 함수이름 : getSalesPerformanceGroupByName
	 * 인자값 : 없음
	 * 반환값 : 정상 종료시 resultList 반환, 예외 발생시 null 반환
	 * 함수설명 : 제품별 매출요약을 가져오는 함수
	 */
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
