package c_info;
// layout때문에 사용
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Calendar;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class InfoTest2 extends JFrame{
	//멤버변수 선언
	JTextArea ta;
	JButton bAdd, bShow, bSearch, bDelete, bCancel, bExit;
	JTextField tfName, tfId, tfTel, tfSex, tfAge, tfHome;
	ImageIcon i1, i2, i3, i4, i5, i6;
	InfoTest2(){
		//객체 생성
		i1 = new ImageIcon("imgs/a1.png");
		i2 = new ImageIcon("imgs/a.png");
		i3 = new ImageIcon("imgs/b.png");
		i4 = new ImageIcon("imgs/c.png");
		i5 = new ImageIcon("imgs/d.png");
		i6 = new ImageIcon("imgs/e.png");
		
		ta = new JTextArea();
		bAdd = new JButton("Add", i1);
		bAdd.setMnemonic('i'); // alt+@;
		bAdd.setToolTipText("aaaaaaaaaaaaa");
		bAdd.setVerticalTextPosition(JButton.BOTTOM);
		bAdd.setHorizontalTextPosition(JButton.CENTER);
		bAdd.setRolloverIcon(i5);
		bAdd.setPressedIcon(i3);
		bShow = new JButton("Show", i2);
		bSearch = new JButton("Search", i3);
		bDelete = new JButton("Delete", i4);
		bCancel = new JButton("Cancel", i5);
		bExit = new JButton("Exit", i6);
		tfName = new JTextField(10);
		tfId = new JTextField(10);
		tfTel = new JTextField(10);
		tfSex = new JTextField(10);
		tfAge = new JTextField(10);
		tfHome = new JTextField(10);
		
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
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
	}
	public void eventConn() {
		MyEvent mv = new MyEvent();
		bAdd.addActionListener(mv);
		bShow.addActionListener(mv);
		bSearch.addActionListener(mv);
		bDelete.addActionListener(mv);
		bCancel.addActionListener(mv);
		bExit.addActionListener(mv);
		
		//id textfield에 엔터쳤을 때 이벤트처리
		tfId.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JTextField jt = (JTextField)e.getSource();
				String msg = jt.getText();
//				JOptionPane.showMessageDialog(null, msg + "를 입력하셨습니다.");
				String s = msg.substring(7,8);
				if(s.equals("1") || s.equals("3") || s.equals("9"))
					tfSex.setText("남");
				else if(s.equals("2") || s.equals("4") || s.equals("0"))
					tfSex.setText("여");
				switch(msg.charAt(8)) {
				case '0' : tfHome.setText("서울"); break;
				case '1' : tfHome.setText("인천/부산"); break;
				case '2' : tfHome.setText("경기도"); break;
				default : tfHome.setText("한국인"); break;
				}
				int temp = Integer.parseInt(msg.substring(0, 2));
				//올해 연도 구하기
				Calendar calendar = Calendar.getInstance();
				int year = calendar.get(Calendar.YEAR);
				int age=0;
				if(s.equals("1") || s.equals("2"))
				{
					age= year- (1900+temp)+1;
				} else if(s.equals("3") || s.equals("3"))
				{
					age= year- (2000+temp)+1;
				} else if(s.equals("9") || s.equals("0"))
				{
					age= year- (1800+temp)+1;
				}
				tfAge.setText(Integer.toString(age));
			}
		});
		//x버튼 눌렀을 경우 이벤트처리
		addWindowListener(new WindowAdapter() { // adapter를 사용하면 비어있는 여러가지 함수들을 다 사용할 필요가 없어짐
			@Override
			public void windowClosing(WindowEvent e) {
				int result = JOptionPane.showConfirmDialog(null, "진짜 나가시겠습니까?");
				if(result == JOptionPane.OK_OPTION)
					System.exit(0);
			}
		});
		
	}
	
	class MyEvent implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			JButton evt = (JButton)e.getSource();
			
			if(evt == bAdd) {
				String msg = bAdd.getText();
				JOptionPane.showMessageDialog(null, msg + "버튼 이벤트발생");
			}
			if(evt == bShow) {
				String msg = bShow.getText();
				JOptionPane.showMessageDialog(null, msg + "버튼 이벤트발생");
			}
			if(evt == bSearch) {
				String msg = bSearch.getText();
				JOptionPane.showMessageDialog(null, msg + "버튼 이벤트발생");
			}
			if(evt == bDelete) {
				String msg = bDelete.getText();
				JOptionPane.showMessageDialog(null, msg + "버튼 이벤트발생");
			}
			if(evt == bCancel) {
				String msg = bCancel.getText();
				JOptionPane.showMessageDialog(null, msg + "버튼 이벤트발생");
			}
			if(evt == bExit) {
				System.exit(0);
			}	
		}
		
	}
	
	
	public static void main(String[] args) {
		InfoTest2 t = new InfoTest2();
		t.display();
		t.eventConn();
	}
}
