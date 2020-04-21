package t_teamproject.teamproject_02.dao;

import java.util.ArrayList;

import t_teamproject.teamproject_02.vo.Product;

public interface OrderDao {
	public int insertOrder(String productStringList [], ArrayList<Product> calProductList);
	public ArrayList<ArrayList> getSalesPerformanceGroupByDay();
	public ArrayList<ArrayList> getSalesPerformanceGroupByMonth();
	public ArrayList<ArrayList> getSalesPerformanceGroupByYear();
}
