package thread.basic;

class Count{
	int i=0;
	void increment() { // 메소드 앞에 synchrnoized를 붙일 경우 tc1이 실행하는 동안 tc2가 실행하지 못함
		synchronized (this) { // 이 블럭 안에서만 동기화 가능 // 인자로 클래스가 들어가는데, 마땅한 값이 없을 경우 그냥 this사용
			i++;
		}
		
	}
}
class ThreadCount extends Thread
{
	Count cnt;
	ThreadCount(Count cnt){
		this.cnt = cnt;
	}
	public void run() {
		for(int i=0; i<100000000; i++)
		{
			cnt.increment();
		}
	}
}

public class Ex5_SyncTest {
	public static void main(String[] args) {
		Count cnt = new Count();
		ThreadCount tc1 = new ThreadCount(cnt);
		tc1.start();
		ThreadCount tc2 = new ThreadCount(cnt);
		tc2.start();
		
		try {
			tc1.join(); //tc1이 끝날떄까지 대기
			tc2.join(); //tc2가 끝날떄까지 대기
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("i의 결과:" + cnt.i);
	}
}
