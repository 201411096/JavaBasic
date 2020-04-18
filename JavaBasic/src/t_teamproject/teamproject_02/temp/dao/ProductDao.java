package t_teamproject.teamproject_02.temp.dao;

import java.util.ArrayList;

import t_teamproject.teamproject_02.temp.vo.Product;

public interface ProductDao {
	public Product selectByID(int id);
	public ArrayList searchProduct(int option, String searchWord);
	public int insertProduct(Product vo);
	public int updateProduct(Product vo);
	public int deleteProduct(int id);
}
