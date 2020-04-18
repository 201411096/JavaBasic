package t_teamproject.teamproject_02.temp.dao;

import java.util.ArrayList;

import t_teamproject.teamproject_02.temp.vo.Employee;

public interface EmployeeDao {
	public Employee selectByID(String id);
	public ArrayList searchEmployee(int option, String searchWord);
	public int insertEmployee(Employee vo);
	public int updateEmployee(Employee vo);
	public int deleteEmployee(String id);
}
