package t_teamproject.teamproject_02.dao;

import java.util.ArrayList;

import t_teamproject.teamproject_02.vo.Product;

public interface ProductManagementDao {
	public int purchaseProductByName(String name, int cnt);			//	제품 입고(도매점에서 구매)
	public ArrayList<ArrayList> productCount();						//	제품별 제품이름과 재고 개수를 가져옴
	public ArrayList<Product> getAllProduct();						//	모든 제품의 정보를 가져옴
	public ArrayList<ArrayList> getPidCountFromproduct();			//	제품별 제품식별번호와 재고 개수를 가져옴
}
