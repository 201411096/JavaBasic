package t_teamproject.teamproject_02.dao;

import java.util.ArrayList;

import t_teamproject.teamproject_02.vo.Employee;

public interface EmployeeDao {
	public Employee selectByID(String id);
	public ArrayList searchEmployee(int option, String searchWord);
	public int insertEmployeeWithOutDate(Employee vo);
	public int insertEmployee(Employee vo);
	public int updateEmployee(Employee vo);
	public int deleteEmployee(String id);
}