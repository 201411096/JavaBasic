package t_teamproject.teamproject_02.vo;

public class OrderList {
	private int id;
	private int totalPrice;
	
	public OrderList() {
		
	}
	
	public OrderList(int id, int totalPrice) {
		super();
		this.id = id;
		this.totalPrice = totalPrice;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(int totalPrice) {
		this.totalPrice = totalPrice;
	}
	
}
