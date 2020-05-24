package f_numbergame;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashSet;

import javax.swing.JButton;
import javax.swing.JFrame;

public class NumberGameTest {
	public static void main(String[] args) {
		NumberGame ng = new NumberGame();
		ng.initChar();
		ng.showAnswer();
	}
}

class NumberGame extends JFrame implements ActionListener{
	int number =4;
	JButton b [][] = new JButton[number][number];
	JButton firstClick;
	int firstRow, firstCol;

	char answers [][] = new char[number][number]; // char default 값 '\u0000'
	
	public NumberGame(){
		setLayout(new GridLayout(4, 4));
		for(int i=0; i<b.length; i++)
		{
			for(int j=0; j<b[i].length; j++)
			{
				b[i][j]= new JButton(i+":" + j);
				add(b[i][j]);
				answers[i][j]='0';
				b[i][j].addActionListener(this);
			}
		}		
		setSize(600, 400);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}

	//임의의 알파벳을 임의의 위치에 지정
	void initChar() {
		char alpha='0';
		HashSet<Character> used_alpha = new HashSet<Character>();
		for(int i=0; i<number*number;)
		{
			//임의의 알파벳 만들기 8번
			if(i%2==0) { // i값이 홀수일때는 기존에 만들어뒀던 char값을 사용
				alpha = (char)('A' + (int)(Math.random()*26));
				if(used_alpha.contains(alpha))
					continue;
				used_alpha.add(alpha);
			}
			boolean ok=true;
			while(ok) {
				int row = (int)(Math.random()*number);
				int col = (int)(Math.random()*number);
				if(answers[row][col]=='0')
				{
					answers[row][col]=alpha;
					i++;
					ok=false;
				}	
			}
		}
	}
	void showAnswer() {
		for(int i=0; i<answers.length; i++)
		{
			for(int j=0; j<answers[i].length; j++)
				b[i][j].setText(Character.toString(answers[i][j]));	
		}
		try {
			Thread.sleep(15000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		for(int i=0; i<answers.length; i++)
		{
			for(int j=0; j<answers[i].length; j++)
				b[i][j].setText(null);	
		}
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		JButton jb = (JButton)e.getSource();
		for(int i=0; i<number; i++){
			for(int j=0; j<number; j++){
				if(b[i][j]== jb) {
					if(firstClick==null) {
						firstClick = jb;
						firstRow = i;
						firstCol = j;
						jb.setBackground(Color.cyan);
					}
					else {
						if(answers[i][j]==answers[firstRow][firstCol] && firstClick!=jb)
						{
							jb.setBackground(Color.red);
							firstClick.setBackground(Color.red);
							jb.removeActionListener(this);
							firstClick.removeActionListener(this);
						}
						else
							firstClick.setBackground(null);
						firstClick=null;
					}	
				}
			}
		}
	}
}
