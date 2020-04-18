package t_teamproject.teamproject_02.temp.vo;

public class Employee {
	private String id;
	private String password;
	private String name;
	private String tel;
	private int sal;
	private String hire_date;
	private String position;
	private int age;
	
	public Employee() {}
	public Employee(String id, String password, String name, String tel, int sal, String hire_date, String position, int age) {
		super();
		this.id = id;
		this.password = password;
		this.name = name;
		this.tel = tel;
		this.sal = sal;
		this.hire_date = hire_date;
		this.position = position;
		this.age = age;
	}


	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
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
	public int getSal() {
		return sal;
	}
	public void setSal(int sal) {
		this.sal = sal;
	}
	public String getHire_date() {
		return hire_date;
	}
	public void setHire_date(String hire_date) {
		this.hire_date = hire_date;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
}
