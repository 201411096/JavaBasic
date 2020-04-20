package model;

import java.util.ArrayList;

public interface RentDao {
	public String selectByTel(String tel) throws Exception;
	public void rentVideo(String tel, int vnum) throws Exception;
	public int returnVideo(int vnum) throws Exception;
	public ArrayList selectList() throws Exception;
}
