package jdbc.transection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import temp.Password;
public class AccLogic 
{
	// 연결 객체 생성시 필요한 변수 선언
	String url;
	String user;
	String pass;

	//===============================================
	// 드라이버를 드라이버매니저에 등록
	public AccLogic() throws Exception{
		/////////////////////////////////////////////////////////
		// 1. 드라이버를 드라이버 매니저에 등록
		Class.forName("oracle.jdbc.driver.OracleDriver");
		url = "jdbc:oracle:thin:@192.168.0.20:1521:orcl";
		user = "hrr";
		pass = Password.password;
	}


	//====================================================
	// 보내는 계좌번호와 받는 계좌번호와 계좌금액을 넘겨받아 
	//	보내는계좌에서 계좌금액을 빼고 받는계좌에서 계좌금액을 더한다
	public int moveAccount(String sendAcc, String recvAcc, int amount)
	{
		Connection con = null;

		///////////////////////////////////////////////////////////
		//	 1. Connection 객체 생성
		//@@ 2. Auto-commit을 해제
		//	 3. 출금계좌에서 이체금액을 뺀다
		//	 4. 입금계좌에 이체금액을 더한다
		//@@ 5. commit을 전송한다
		//	 6. 객체 닫기
		//	 - 만일 정상적인 경우는 0을 리턴하고 도중에 잘못되었으면 트랜잭션을 롤백시키고 -1을 리턴
		try {
			con = DriverManager.getConnection(url, user, pass); // 하나의 문장에 하나의 전송객체를 만드는 것 추천
			con.setAutoCommit(false);
			String sendSql = "UPDATE account SET amount = amount - ? WHERE account_num = ?";
			PreparedStatement sendPs = con.prepareStatement(sendSql);
			//? 지정하기
			sendPs.setInt(1, amount);
			sendPs.setString(2, sendAcc);
			int rs1 = sendPs.executeUpdate(); // executeUpdate() 하는 순간에 바로 커밋이 되버림
			if(rs1 == 0) //update문 사용시 수정된 계좌가 하나도 없을 경우
			{
				con.rollback();
				return -1;
			}
			sendPs.close();
			
//			
			String recvSql = "UPDATE account SET amount = amount + ? WHERE account_num = ?";
			PreparedStatement recvPs = con.prepareStatement(recvSql);
			//? 지정하기
			recvPs.setInt(1, amount);
			recvPs.setString(2, recvAcc);
			int rs2 = recvPs.executeUpdate();
			if(rs2 == 0)
			{
				con.rollback();
				return -1;
			}
			recvPs.close();
			
			con.commit();
		} catch (Exception e) {
			try {
				con.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
			return -1; // 비정상적인 경우 -1 리턴
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		System.out.println("이체 성공");
		return 0;
	}

	//=======================================================
	//	보내는계좌번호와 받는계좌번호를 넘겨받아
	//		보내는계좌고객명과 보내는계좌의남은 금액을 얻어오고
	//		받는계좌고객명을 얻어와서
	//		얻은 정보를 ConfirmData객체에 넣고 리턴
	public ConfirmData confirmAccount(String sendAcc, String recvAcc) 
		throws Exception{

		
		String sendCust="", recvCust="";
		int gainMoney=0;
		ConfirmData  resultData=null;
		
		//	1. Connection 객체 생성
		//	2. 테이블에서, 넘겨받은 sendAcc와 같은 account_num필드에서 customer, amount를 얻어온다
		//	3. 테이블에서, 넘겨받은 recvAcc와 같은 account_num필드에서 customer를 얻어온다
		//  4. 2와 3에서 얻은 값을 ConfirmData 객체에 저장
		//	5. 4번의 객체를 리턴



		return resultData;
	}

}


//#################################################################
//	테이블명 : account
//	account_num		계좌번호		varchar2(20)
//	customer		고객명			varchar2(20)
//	amount			계좌금액		int



























