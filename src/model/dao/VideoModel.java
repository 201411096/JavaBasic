package model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.VideoDao;
import model.vo.Video;

public class VideoModel implements VideoDao{
	String url = "jdbc:oracle:thin:@192.168.0.17:1521:orcl";
	String user = "tp2";
	String pass = "tp2";
	
	public VideoModel() throws Exception{
		// 1. 드라이버로딩
		Class.forName("oracle.jdbc.driver.OracleDriver");
	}
	
	
	public void insertVideo(Video vo, int count) throws Exception{
		// 2. Connection 연결객체 얻어오기
		Connection con = DriverManager.getConnection(url, user, pass);
		con.setAutoCommit(false);
		// 3. sql 문장 만들기
		String sql1 = "INSERT INTO VIDEO_SHOP_VIDEO(VID, VGENRE, VNAME, VDIRECTOR, VACTOR, VDETAIL) VALUES(video_shop_video_vid_seq.nextval, ?, ?, ?, ?, ?)";
		// 4. sql 전송객체 (PreparedStatement)
		PreparedStatement ps1 =con.prepareStatement(sql1);
		ps1.setString(1, vo.getGenre());
		ps1.setString(2, vo.getVideoName());
		ps1.setString(3, vo.getDirector());
		ps1.setString(4, vo.getActor());
		ps1.setString(5, vo.getExp());
		// 5. sql 전송
		try {
			ps1.executeUpdate();
		}catch(Exception e) {
			con.rollback();
		}finally {
			ps1.close();
		}
		String sql2 = "INSERT INTO VIDEO_SHOP_VIDEO_MANAGEMENT(VMID, VID) VALUES(video_shop_vm_vmid_seq.nextval, video_shop_video_vid_seq.currval)";
		PreparedStatement ps2 =con.prepareStatement(sql2);
		for(int i=0; i<count; i++)
		{
			 ps2.executeUpdate();
		}
		con.commit();
		// 6. 닫기
		ps2.close();
		con.close();
	}
	public ArrayList selectVideo(int option, String string) {
		Connection con = null;
		ArrayList videoList = new ArrayList();
		try {
			con = DriverManager.getConnection(url, user, pass);
			String [] columnNames = {"VNAME","VACTOR"};
			String sql="";
			
			sql = "SELECT * FROM VIDEO_SHOP_VIDEO WHERE " + columnNames[option] + " LIKE '%' || ? || '%'";
//			sql = "SELECT * FROM VIDEO_SHOP_VIDEO WHERE VNAME LIKE '%' || ? || '%'";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, string);
			ResultSet rs = ps.executeQuery();
			while(rs.next())
			{
				ArrayList temp = new ArrayList();
				temp.add(rs.getInt("VID"));
				temp.add(rs.getString("VNAME"));
				temp.add(rs.getString("VACTOR"));
				videoList.add(temp);
			}
			rs.close();
			ps.close();
			con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		return videoList;
	}
}
