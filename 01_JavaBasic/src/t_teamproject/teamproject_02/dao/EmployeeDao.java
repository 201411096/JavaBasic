package t_teamproject.teamproject_02.dao;

import java.util.ArrayList;

import t_teamproject.teamproject_02.vo.Employee;

public interface EmployeeDao {		
	public Employee selectByID(String id);								//	id로 직원 검색
	public ArrayList searchEmployee(int option, String searchWord);		//	옵션을 사용한 직원검색(이름, 아이디)
	public int insertEmployeeWithOutDate(Employee vo);					//	회원가입창에서 직접 회원가입시 사용하는 직원등록(날짜가 현재 시간으로 들어감)
	public int insertEmployee(Employee vo);								//	직원관리 창에서 직원등록 입사일 지정 가능
	public int updateEmployee(Employee vo);								//	직원정보를 업데이트
	public int deleteEmployee(String id);								//	직웝정보 삭제
}
