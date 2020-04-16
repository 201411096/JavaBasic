package t_teamproject.teamproject_02.temp.dao;

import t_teamproject.teamproject_02.temp.vo.Employee;

public interface EmployeeDao {
	public Employee selectByID(String id);
	public int insertEmployee(Employee vo);
	public int updateEmployee(Employee vo);
	public int deleteEmployee(String id);
}
