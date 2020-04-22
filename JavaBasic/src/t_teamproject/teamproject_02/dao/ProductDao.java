package t_teamproject.teamproject_02.dao;

import java.util.ArrayList;

import t_teamproject.teamproject_02.vo.Product;

public interface ProductDao {
	public Product selectByID(int id);												//	제품의 id로 제품검색
	public ArrayList searchProduct(int option, String searchWord);					//	검색옵션과 검색할 단어에 해당되는 제품정보들을 검색
	public int insertProduct(Product vo);											//	제품 정보 삽입
	public int updateProduct(Product vo);											//	제품 정보 수정
	public int deleteProduct(int id);												//  제품 정보 삭제
	
	public ArrayList<String> selectProductNameOrderByName();						//  제품의 이름을 오름차순 순으로 배열로 가져옴
	
	public ArrayList<Product> searchProductByGroupNameAsc(String searchWord);		//  제품을 그룹별로 묶어서 검색(메인메뉴, 사이드메뉴, 음료수)
}
