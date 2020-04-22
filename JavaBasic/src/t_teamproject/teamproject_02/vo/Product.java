package t_teamproject.teamproject_02.vo;

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
	@Override
	public String toString() {							//제품 정보를 string으로 담는 함수
		StringBuffer result = new StringBuffer("");
		
		result.append("제품 이름 :" + this.name +"\n");
		result.append("제품 그룹 :" + this.groupName +"\n");
		result.append("제품 가격 :" + this.price +"\n");
		result.append("제품 설명 \n" + this.detail +"\n");
		
		
		return result.toString();
	}
	
	public String toStringForOrderList(int cnt) {		//영수증 출력에 사용할 함수
		StringBuffer result = new StringBuffer("");
		result.append("\t" + this.name +"\t");
		result.append("" + cnt +"\t");
		result.append("" + cnt*this.price +"\t");
		
		return result.toString();
	}
	
	public String changePGIDtoGroupName(int pgid) { //오라클에서 pgid를 받으면 pgroupname으로 변경
		switch(pgid) {
		case 0 : return "메인메뉴";
		case 1 : return "사이드메뉴";
		case 2 : return "음료수";
		case 3 : return "세트메뉴";
		}
		return "-1";
	}
	public int changeGroupNametoPGID(String groupName) { //오라클로 전송시에 pgroupname을 pgid로 변환
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
