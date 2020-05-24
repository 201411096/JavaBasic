package jfreechart;

import java.sql.*;
import java.util.*;

public class Database {

//	String URL = "jdbc:oracle:thin:@192.168.0.17:1521:orcl";
//	String USER ="scott";
//	String PASS = "scottscott";
	public static final String URL = "jdbc:oracle:thin:@192.168.56.1:1521:xe";
	public static final  String USER = "scott";
	public static final  String PASS = "tiger";

	public ArrayList<ArrayList> getData() {

		ArrayList<ArrayList> data = new ArrayList<ArrayList>();
		try{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection(URL, USER , PASS);	
			
			//***************************************************************
//			String sql = "SELECT sal, ename FROM emp";
//			String sql = "SELECT NVL(TO_CHAR(hiredate, 'MM'), 00) AS hire_month,  count(*) AS cnt FROM emp group by TO_CHAR(hiredate, 'MM') ORDER BY TO_CHAR(hiredate, 'MM')"; // 입사월별 인원수
//			String sql = "SELECT JOB, NVL(AVG(SAL), 0) AS avgsal FROM EMP GROUP BY JOB ORDER BY JOB"; // 업무별 평균 월급
			String sql = "SELECT ext.sal AS SAL, ext.ename AS NAME FROM (SELECT * FROM emp ORDER BY nvl(sal, 0) desc) ext WHERE rownum<=5"; // 상위 월급 5명
			//***************************************************************
			
			PreparedStatement stmt = con.prepareStatement( sql );	

			ResultSet rset = stmt.executeQuery();

			
			while( rset.next() ){
				ArrayList temp = new ArrayList();	
//				temp.add( rset.getInt("SAL"));				//****************
//				temp.add( rset.getString("ENAME"));		//****************
//				temp.add( rset.getInt("cnt"));				//**************** 입사월별 인원수
//				temp.add( rset.getString("hire_month"));		//**************** 입사월별..
//				temp.add(rset.getInt("avgsal"));		//**************** 업무별 평균 월급
//				temp.add(rset.getString("JOB"));		//**************** 업무별 평균 월급
				temp.add(rset.getInt("sal"));
				temp.add(rset.getString("name"));
				data.add(temp);
			}
			rset.close();
			stmt.close();
			con.close();
		} catch(Exception ex){
			System.out.println("에러 : " + ex.getMessage() );
			ex.printStackTrace();
		}
		return data;
	}
}
