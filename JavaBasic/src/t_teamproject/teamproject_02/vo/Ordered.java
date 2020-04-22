package t_teamproject.teamproject_02.vo;

public class Ordered {	//판매시 제품 하나와 관련된 정보를 담는 객체
	private int oid;
	private int pid;
	private int count;
	private String date;
	private int cid;
	
	public Ordered() {
		
	}	
	public Ordered(int oid, int pid, int count, String date, int cid) {
		super();
		this.oid = oid;
		this.pid = pid;
		this.count = count;
		this.date = date;
		this.cid = cid;
	}
	public int getOid() {
		return oid;
	}

	public void setOid(int oid) {
		this.oid = oid;
	}

	public int getPid() {
		return pid;
	}

	public void setPid(int pid) {
		this.pid = pid;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public int getCid() {
		return cid;
	}

	public void setCid(int cid) {
		this.cid = cid;
	}
}
