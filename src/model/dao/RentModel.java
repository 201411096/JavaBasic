package model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import model.RentDao;


public class RentModel implements RentDao{
	String url = "jdbc:oracle:thin:@192.168.0.17:1521:orcl";
	String user = "tp2";
	String pass = "tp2";

	Connection con;
	
	public RentModel() throws Exception{
		// 1. 드라이버로딩
		Class.forName("oracle.jdbc.driver.OracleDriver");
	}

	@Override
	public String selectByTel(String tel) throws Exception { // throws를 해줘야 view에서 예외확인이 가능
		String name=null;
		// 2. Connection 연결객체 얻어오기	
		Connection con = DriverManager.getConnection(url, user, pass);
		// 3. sql 문장 만들기
		String sql = "SELECT name FROM video_shop_customer WHERE tel_1=?";
		// 4. sql 전송객체 (PreparedStatement)
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, tel);
		ResultSet rs = ps.executeQuery();
		while(rs.next()) {
			name = rs.getString("name");
		}
		rs.close();
		ps.close();
		con.close();
		// 5. sql 전송
		return name;
	}
	/*
	 *  함수명 : rentVideo
	 *  인자 : 전화번호(String), 비디오번호(int)
	 *  리턴값 : 
	 *  역할 : 대여테이블에 정보를 입력
	 */
	@Override
	public void rentVideo(String tel, int vnum) throws Exception { //이미 빌려간 비디오도 계속 빌려갈 수 있는 문제가 생김
		Connection con = DriverManager.getConnection(url, user, pass);
		String presql = "SELECT * FROM video_shop_rental WHERE VMID=? AND returndate IS NULL";
		PreparedStatement preps = con.prepareStatement(presql);
		preps.setInt(1, vnum);
		ResultSet rs = preps.executeQuery();
		if(rs.next())
		{
			return;						// void여도 return이 가능(제어권 반환)
//			throw new Exception();		// return이 일반적 예외던지는것도 가능하긴 함
		}
		
		String sql = "INSERT INTO video_shop_rental(VRID, RENTALDATE, VMID, ID) VALUES(video_shop_vr_vrid_seq.NEXTVAL, SYSDATE, ?, (SELECT ID FROM video_shop_customer WHERE tel_1=?) )";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setInt(1, vnum);
		ps.setString(2, tel);
		
		ps.executeUpdate();
		ps.close();
		con.close();
	}

	@Override
	public int returnVideo(int vnum) throws Exception {
		Connection con = DriverManager.getConnection(url, user, pass);
		String sql = "UPDATE video_shop_rental SET returndate=SYSDATE WHERE VMID=? AND returndate IS NULL"; // RETURNDATE가 NULL이라는 조건을 추가(VIDEONUM이 PRIMARY KEY가 아니기 떄문 // 이전에 같은 비디오가 렌탈 반납된 기록에 덮어씌워지는것을 방지)
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setInt(1, vnum);
		ps.executeUpdate();
		
		ps.close();
		con.close();
		return 0;
	}

	@Override
	public ArrayList selectList() throws Exception {
		ArrayList resultList = new ArrayList();
		Connection con = DriverManager.getConnection(url, user, pass);
		String sql = "SELECT R.VMID VMID, V.VNAME VNAME, VC.NAME AS VCNAME, vc.tel_1 AS VCTEL, r.rentaldate+3 AS EXRENTALDATE, '미납' AS ENDD\r\n" + 
				"FROM VIDEO_SHOP_RENTAL R\r\n" + 
				"INNER JOIN VIDEO_SHOP_VIDEO_MANAGEMENT VM\r\n" + 
				"ON R.vmid=vm.vmid\r\n" + 
				"INNER JOIN VIDEO_SHOP_VIDEO V\r\n" + 
				"ON VM.VID = V.vid\r\n" + 
				"INNER JOIN video_shop_customer VC\r\n" + 
				"ON VC.ID = R.ID\r\n" + 
				"WHERE r.returndate is null";
		PreparedStatement ps = con.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		while(rs.next()) {
			ArrayList temp = new ArrayList();
			temp.add(rs.getString("VMID"));
			temp.add(rs.getString("VNAME"));
			temp.add(rs.getString("VCNAME"));
			temp.add(rs.getString("VCTEL"));
			temp.add(rs.getString("EXRENTALDATE"));
			temp.add(rs.getString("ENDD"));
			resultList.add(temp);
		}
		return resultList;
	}
}
