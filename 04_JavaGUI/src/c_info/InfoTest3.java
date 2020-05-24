package c_info;
// layout때문에 사용
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class InfoTest3 extends JFrame{
	//멤버변수 선언
	JTextArea ta;
	JButton bAdd, bShow, bSearch, bDelete, bCancel, bExit;
	JTextField tfName, tfId, tfTel, tfSex, tfAge, tfHome;
	ImageIcon i1, i2, i3, i4, i5, i6;
	InfoTest3(){
		//객체 생성
		i1 = new ImageIcon("imgs/a1.png");
		i2 = new ImageIcon("imgs/a.png");
		i3 = new ImageIcon("imgs/b.png");
		i4 = new ImageIcon("imgs/c.png");
		i5 = new ImageIcon("imgs/d.png");
		i6 = new ImageIcon("imgs/e.png");
		
		ta = new JTextArea();
		bAdd = new JButton("Add", i1);
		bShow = new JButton("Show", i2);
		bSearch = new JButton("Search", i3);
		bDelete = new JButton("Delete", i4);
		bCancel = new JButton("Cancel", i5);
		bExit = new JButton("Exit", i6);
		tfName = new JTextField();
		tfId = new JTextField();
		tfTel = new JTextField();
		tfSex = new JTextField();
		tfAge = new JTextField();
		tfHome = new JTextField();
		
	}
	void display() {
		//화면 구성 및 출력
		setLayout(new BorderLayout());
		
		JPanel lp = new JPanel();
		lp.setPreferredSize(new Dimension(300, 450));
		lp.setLayout(new GridLayout(6, 2));
		lp.add(new JLabel("Name", i1, JLabel.CENTER));
		lp.add(tfName);
		lp.add(new JLabel("ID", i2, JLabel.CENTER));
		lp.add(tfId);
		lp.add(new JLabel("Tel", i3, JLabel.CENTER));
		lp.add(tfTel);
		lp.add(new JLabel("Sex", i4, JLabel.CENTER));
		lp.add(tfSex);
		lp.add(new JLabel("Age", i5, JLabel.CENTER));
		lp.add(tfAge);
		lp.add(new JLabel("Home", i6, JLabel.CENTER));
		lp.add(tfHome);
		JPanel bp = new JPanel();
		bp.setPreferredSize(new Dimension(800, 50));
		bp.setLayout(new GridLayout(1, 6));
		bp.add(bAdd);
		bp.add(bShow);
		bp.add(bSearch);
		bp.add(bDelete);
		bp.add(bCancel);
		bp.add(bExit);
		
		add(lp, BorderLayout.WEST);
		add(bp, BorderLayout.SOUTH);
		add(ta, BorderLayout.CENTER);
		
		setTitle("DBTest");
		setLocation(650, 300);
		setSize(800, 500);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public static void main(String[] args) {
		InfoTest3 t = new InfoTest3();
		t.display();
	}
}
