package t_teamproject.teamproject_02.dao;

import java.util.ArrayList;

import t_teamproject.teamproject_02.vo.Product;

public interface ProductManagementDao {
	public int purchaseProductByName(String name, int cnt);
	public ArrayList<ArrayList> productCount();
	public ArrayList<Product> getAllProduct();
}
