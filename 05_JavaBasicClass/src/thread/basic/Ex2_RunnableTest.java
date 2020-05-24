package thread.basic;

public class Ex2_RunnableTest {
	public static void main(String[] args) {
		MakeCar2 mc1 = new MakeCar2("차틀만들기");
		Thread t1 = new Thread(mc1);			// start() 함수를 사용하기 위해 Thread 함수를 선언  +++ 기존에 만들어뒀던 클래스를 매개변수로 넣어둠
		t1.start();								// start() 함수가 Runnable 인터페이스에는 들어가있지 않음
		
		
		Thread t2 = new Thread(new MakeCar2("엔진 부착"));
		t2.start();
		
		new Thread(new MakeCar2("엔진 부착착착착")).start();
	}
}

class MakeCar2 implements Runnable{
	String work;
	MakeCar2(String work){
		this.work=work;
	}
	public void run() {
		for(int i=0; i<5; i++) {
			System.out.println(work + "작업중");
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}