package t_teamproject.teamproject_02.dao;

import java.util.ArrayList;

import t_teamproject.teamproject_02.vo.Product;

public interface OrderDao {
	public int insertOrder(String productStringList [], ArrayList<Product> calProductList);		// 제품을 주문하는 함수 
	public ArrayList<ArrayList> getSalesPerformanceGroupByDay();								// 이번달의 일별 매출요약을 가져오는 함수(1월 31일이면 1월1일부터 1월31일까지, 2월2일이면 2월1일부터 2월 2일까지의 매출)
	public ArrayList<ArrayList> getSalesPerformanceGroupByMonth();								// 이번년의 월별 매출요약을 가져오는 함수(2월이면 1월부터 2월까지, 12일이면 1월부터 12월까지의 매출)
	public ArrayList<ArrayList> getSalesPerformanceGroupByYear();								// 연도별 매출요약을 가져오는 함수
	public ArrayList<ArrayList> getSalesPerformanceGroupByName();								// 제품별 매출요약을 가져오는 함수
}
