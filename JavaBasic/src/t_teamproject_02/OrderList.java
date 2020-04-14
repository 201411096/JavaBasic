package t_teamproject_02;

import java.util.Date;

public class OrderList {
	private int orderlist_id;
	private int order_id;
	private String customer_tel;
	private Date order_date;
	private int total_price;
	public int getOrderlist_id() {
		return orderlist_id;
	}
	public void setOrderlist_id(int orderlist_id) {
		this.orderlist_id = orderlist_id;
	}
	public int getOrder_id() {
		return order_id;
	}
	public void setOrder_id(int order_id) {
		this.order_id = order_id;
	}
	public String getCustomer_tel() {
		return customer_tel;
	}
	public void setCustomer_tel(String customer_tel) {
		this.customer_tel = customer_tel;
	}
	public Date getOrder_date() {
		return order_date;
	}
	public void setOrder_date(Date order_date) {
		this.order_date = order_date;
	}
	public int getTotal_price() {
		return total_price;
	}
	public void setTotal_price(int total_price) {
		this.total_price = total_price;
	}
}
