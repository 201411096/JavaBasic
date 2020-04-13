package t_teamproject_02;
import javax.swing.JFrame;
import javax.swing.JLabel;

/*thread test
인자 : 남은 요금, 시간
오른쪽 패널에 표시해야되는값 
*/
//public class Main extends JFrame implements Runnable{
//	public Main() {
//		setVisible(true);
//		setSize(1920, 1080);
//		setExtendedState(JFrame.MAXIMIZED_BOTH); 			// 프로그램 시작시 mainFrame 최대화
//		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	
//	}	
//	@Override
//	public void run() {		
//	}
//	public static void main(String[] args){
//	}
//}
/* timerFrame test코드
public class Main extends JFrame implements Runnable{
	JLabel j;
	public Main() {
		j = new JLabel("10");
		add(j);
		setVisible(true);
		setSize(1920, 1080);
		setExtendedState(JFrame.MAXIMIZED_BOTH); 			// 프로그램 시작시 mainFrame 최대화
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	
	}
	
	@Override
	public void run() {
		for(int i=0; i<100; i++)
		{
			j.setText(Integer.toString(i));
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
	}

	public static void main(String[] args){
		Main main = new Main();
		new Thread(main).start();

	}
}
 */

//config test코드
public class Main {
	public static void main(String[] args) {
		Configuration config = Configuration.getInstance();
		config.setCol_seat_num(4);
		config.setRow_seat_num(4);

		System.out.println(config.getCol_seat_num());
		System.out.println(config.getRow_seat_num());
		config.setTotalConfiguration();
		config.getTotalConfiguration();
		config.setCol_seat_num(3);
		System.out.println(config.getCol_seat_num());
		System.out.println(config.getRow_seat_num());
	}
} 
