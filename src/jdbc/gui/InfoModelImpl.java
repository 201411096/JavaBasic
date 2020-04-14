package jdbc.gui;

import java.sql.SQLException;

public class InfoModelImpl implements InfoModel{

	public InfoModelImpl() throws Exception {
		//1. 드라이버 로딩
		Class.forName("oracle.jdbc.driver.OracleDriver"); // Class.forName -> String과 같은 이름의 클래스를 생성
	}
	
	@Override
	public void insert() throws SQLException {	
	}
	@Override
	public void selectAll() throws SQLException {
	}
	@Override
	public void delete() throws SQLException {	
	}
	
}
