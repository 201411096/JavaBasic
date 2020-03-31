package f_numbergame;

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;

public class NumberGameTest {
	public static void main(String[] args) {
		NumberGame ng = new NumberGame();
		ng.showAnswer();
	}
}

class NumberGame extends JFrame{
	// 1. 멤버 변수 선언
	JButton b [][] = new JButton[4][4];
	public NumberGame(){
		//2. 객체 생성
		//3. 화면 구성
		setLayout(new GridLayout(4, 4));
		for(int i=0; i<b.length; i++)
		{
			for(int j=0; j<b[i].length; j++)
			{
				b[i][j]= new JButton(i+":" + j);
				add(b[i][j]);
			}
		}		
		//화면에 출력
		setSize(600, 400);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	void showAnswer() {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		for(int i=0; i<b.length; i++)
		{
			for(int j=0; j<b[i].length; j++)
			{
				b[i][j].setText("");
			}
		}
	}
}
