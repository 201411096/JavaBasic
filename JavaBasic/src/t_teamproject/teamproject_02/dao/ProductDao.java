package t_teamproject.teamproject_02.dao;

import java.util.ArrayList;

import t_teamproject.teamproject_02.vo.Product;

public interface ProductDao {
	public Product selectByID(int id);
	public ArrayList searchProduct(int option, String searchWord);
	public int insertProduct(Product vo);
	public int updateProduct(Product vo);
	public int deleteProduct(int id);
	
	public ArrayList<String> selectProductNameOrderByName();
	
	public ArrayList<Product> searchProductByGroupNameAsc(String searchWord);
}
