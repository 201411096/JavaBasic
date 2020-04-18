package t_teamproject.teamproject_02.temp.vo;

public class Product {
	private int id;
	private String groupName;
	private String name;
	private int price;
	private String detail;
	

	public Product() {}	
	
	public Product(int id, String groupName, String name, int price, String detail) {
		super();
		this.id = id;
		this.groupName = groupName;
		this.name = name;
		this.price = price;
		this.detail = detail;
	}
	public String changePGIDtoGroupName(int pgid) {
		switch(pgid) {
		case 0 : return "메인메뉴";
		case 1 : return "사이드메뉴";
		case 2 : return "음료수";
		case 3 : return "세트메뉴";
		}
		return "-1";
	}
	public int changeGroupNametoPGID(String groupName) {
		switch(groupName) {
		case "메인메뉴" : return 0;
		case "사이드메뉴" : return 1;
		case "음료수" : return 2;
		case "세트메뉴" : return 3;
		}
		return -1;
	}
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
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
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}
}
