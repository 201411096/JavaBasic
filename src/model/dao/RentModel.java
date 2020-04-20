package model.dao;

import java.sql.Connection;
import java.util.ArrayList;

import model.RentDao;


public class RentModel implements RentDao{

	Connection con;
	
	public RentModel() throws Exception{
		// 1. 드라이버로딩
	}

	@Override
	public String selectByTel(String tel) throws Exception {
		return null;
	}

	@Override
	public void rentVideo(String tel, int vnum) throws Exception {
		
	}

	@Override
	public int returnVideo(int vnum) throws Exception {
		return 0;
	}

	@Override
	public ArrayList selectList() throws Exception {
		return null;
	}
}
