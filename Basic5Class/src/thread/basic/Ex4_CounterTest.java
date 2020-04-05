package thread.basic;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class Ex4_CounterTest extends JFrame{
    private JPanel p1, p2;
    private JButton btn;
    private JTextArea ta;
    private JLabel lb;
    private boolean inputChk; // 기본값 false
    
    public Ex4_CounterTest() {
        setTitle("단일 스레드 테스트!");
        p1 = new JPanel();
        p1.add(btn = new JButton("Click"));
        p1.add(lb = new JLabel("Count!"));//추가 
        add(p1, BorderLayout.NORTH);
        
        p2 = new JPanel();       
        p2.add( ta = new JTextArea(20,50));
        add(p2, BorderLayout.CENTER);
        
        setBounds(200, 200, 600, 400); // location과 size를 같이 지정하는 메소드
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
               
        btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 1- 카운트 다운을 시작하는 스레드 
                new Thread(new Runnable() { // Thread안에 Runnable을 구현한 이름없는 클래스를 넣음
					@Override
					public void run() {
						for(int i=10; i>0; i--) {
							if(inputChk==true) {
								inputChk=false;
								lb.setText("빙고");
//								break;
								return; // 제어권 반납
							}
							lb.setText(Integer.toString(i));
							try {
								Thread.sleep(1000);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
					}
				}).start();
            	
                new Thread(new Runnable() {
					
					@Override
					public void run() {
						String str = JOptionPane.showInputDialog("암호?");
						ta.append(str + "입니다\n");
						if(str.equals("1234"))
							inputChk=true;
					}
				}).start();;
                // 2- 입력값을 받아서 JTextArea에 붙이는 작업 
                
   
            }
        });
    }
    public static void main(String[] args) {
        new Ex4_CounterTest();
    }
}
