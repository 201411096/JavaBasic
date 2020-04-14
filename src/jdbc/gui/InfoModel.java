package jdbc.gui;

import java.sql.SQLException;

public interface InfoModel {
	void insert(InfoVO vo) throws SQLException; // interface의 메소드들에는 자동으로 public abstract가 붙음
	void selectAll() throws SQLException;
	void delete() throws SQLException;
}
