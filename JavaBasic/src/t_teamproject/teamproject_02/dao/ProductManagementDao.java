package t_teamproject.teamproject_02.dao;

import java.util.ArrayList;

public interface ProductManagementDao {
	public int purchaseProductByName(String name, int cnt);
	public ArrayList<ArrayList> productCount();
}
