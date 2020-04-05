package thread.basic;

/*
 * wait : thread 를 쉬는 상태로
 * notify
 * notifyAll : 쉬고 있는 thread를 모두 깨움
 * 
 * start : 대기 상태로 들어옴
 * run : 실행
 * sleep : 쉬고 있는 상태로 변했다가 시간이 지나면 다시 실행함
 * 
 */

class Bread 
{
	String bread;
	//##  	
	boolean isCheck = false;
	public void setBread( String bread )
	{
		this.bread = bread;
		//## 		
		isCheck = true;
		synchronized (bread) {
			notifyAll(); // block되있는 애들을 데려옴
		}
		
	}	

	public String getBread()
	{
		//##
		if(isCheck == false) {
			try {
				synchronized (bread) {
					wait(); // block상태로 변환 //이 순간에도 문제 발생 가능
				}
				
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return bread;
	}
}

class Baker extends Thread
{
	Bread bbang;

	Baker( Bread bbang )
	{
		this.bbang = bbang;
	}
	public void run()
	{
		bbang.setBread("진열된 완성된 맛있는 빵");
	}
}

class Customer extends Thread
{
	Bread bbang;

	Customer( Bread bbang )
	{
		this.bbang = bbang;
	}
	public void run()
	{
		System.out.println("빵을 사감 : " + bbang.getBread());
	}
}

class Ex8_BreadTest
{
	public static void main(String[] args) 
	{
		Bread  bread = new Bread();

		Baker  baker = new Baker( bread );
		Customer customer = new Customer( bread );
		customer.start();
		baker.start();

		try{
			customer.join();
			baker.join();			
		}catch( Exception ex ){}

	}
}
