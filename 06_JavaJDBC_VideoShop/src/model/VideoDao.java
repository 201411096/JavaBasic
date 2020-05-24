package model;

import java.util.ArrayList;

import model.vo.Video;

public interface VideoDao {
	public void insertVideo(Video vo, int count) throws Exception;
	public ArrayList selectVideo(int option, String string)throws Exception;
	public Video selectVideoByPK(int vnum)throws Exception;
}
