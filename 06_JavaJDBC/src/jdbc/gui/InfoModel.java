package jdbc.gui;

import java.sql.SQLException;
import java.util.ArrayList;

public interface InfoModel {
	void insert(InfoVO vo) throws SQLException; // interface의 메소드들에는 자동으로 public abstract가 붙음
	ArrayList<InfoVO> selectAll() throws SQLException;
	void delete(String tel) throws SQLException;
	InfoVO selectByTel(String tel) throws SQLException;
	void modify(InfoVO vo) throws SQLException;
}
