package f_numbergame;

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;

public class NumberGameTest2 {
	public static void main(String[] args) {
		NumberGame2 ng = new NumberGame2();
		ng.showAnswer();
	}
}

class NumberGame2 extends JFrame{
	int number =4;
	// 1. 멤버 변수 선언
	JButton b [][] = new JButton[number][number];
	
	//2. 버튼위에 지정할 문자 변수
	char answers [][] = new char[number][number]; // char default 값 '\u0000'
	
	public NumberGame2(){
		//2. 객체 생성
		//3. 화면 구성
		setLayout(new GridLayout(4, 4));
		for(int i=0; i<b.length; i++)
		{
			for(int j=0; j<b[i].length; j++)
			{
				b[i][j]= new JButton(i+":" + j);
				add(b[i][j]);
				
				//문자배열도 0으로 초기화
				answers[i][j]='0';
			}
		}		
		//화면에 출력
		setSize(600, 400);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	void showAnswer() {
		//답을 보여주기
		for(int i=0; i<answers.length; i++)
		{
			for(int j=0; j<answers[i].length; j++)
				b[i][j].setText(Character.toString(answers[i][j]));
		}
		//1분 후에 답 가리기
//		try {
//			Thread.sleep(1000);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//		for(int i=0; i<b.length; i++)
//		{
//			for(int j=0; j<b[i].length; j++)
//			{
//				b[i][j].setText(null);
//			}
//		}
	}
}
