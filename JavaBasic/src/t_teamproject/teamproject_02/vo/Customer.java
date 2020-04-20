package t_teamproject.teamproject_02.vo;

public class Customer {
	private int id;
	private String name;
	private String tel;
	private int isCustomer;
	public Customer() {
		
	}	
	public Customer(int id, String name, String tel, int isCustomer) {
		super();
		this.id = id;
		this.name = name;
		this.tel = tel;
		this.isCustomer = isCustomer;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public int getIsCustomer() {
		return isCustomer;
	}
	public void setIsCustomer(int isCustomer) {
		this.isCustomer = isCustomer;
	}
	
}
