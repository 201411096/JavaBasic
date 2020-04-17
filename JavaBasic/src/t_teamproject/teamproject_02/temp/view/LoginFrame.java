package t_teamproject.teamproject_02.temp.view;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import t_teamproject.teamproject_02.temp.dao.EmployeeDao;
import t_teamproject.teamproject_02.temp.dao.EmployeeDaoImpl;
import t_teamproject.teamproject_02.temp.vo.Employee;

public class LoginFrame extends JFrame{
	EmployeeDao employeedao;
	
	JTextField tfId;
	JPasswordField tfPassword;
	JLabel labelId, labelPassword;
	JButton signUp, signIn;
	JPanel panelList[] = new JPanel[3];
	JPanel totalPanel;
	public LoginFrame() {
		display();
		connectDB();
		eventProc();
	}
	class LoginFrameButtonEvent implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource()==signUp)
				moveToRegisterFrame();
			else if(e.getSource()==signIn)
				loginProcess();
		}		
	}
	
	public void display() {
		totalPanel = new JPanel();
		for(int i=0; i<panelList.length; i++)
			panelList[i] = new JPanel();
		
		labelId = new JLabel("ID");
		tfId = new JTextField();
		panelList[0].setLayout(new GridLayout(1,2));
		panelList[0].add(labelId);
		panelList[0].add(tfId);
		panelList[0].setBackground(Color.white);
		
		labelPassword = new JLabel("Password");
		tfPassword =  new JPasswordField();
		panelList[1].setLayout(new GridLayout(1,2));
		panelList[1].add(labelPassword);
		panelList[1].add(tfPassword);
		panelList[1].setBackground(Color.white);
		
		signUp = new JButton("직원등록");
		signIn = new JButton("로그인");
		panelList[2].setLayout(new GridLayout(1,2));
		panelList[2].add(signUp);
		panelList[2].add(signIn);
		
		totalPanel.setLayout(new GridLayout(3, 1));
		for(int i=0; i<panelList.length; i++)
			totalPanel.add(panelList[i]);
		totalPanel.setBackground(Color.white);
		totalPanel.setBorder(BorderFactory.createEmptyBorder(70, 70, 70, 70));
		setLayout(new FlowLayout());
		add(totalPanel);
		
		this.getContentPane().setBackground(Color.white);
		this.setLocationRelativeTo(null);
		setSize(480, 300);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	public void eventProc() {
		LoginFrameButtonEvent l = new LoginFrameButtonEvent();
		signUp.addActionListener(l);
		signIn.addActionListener(l);
	}
	public void connectDB() {
		try {
			employeedao= new EmployeeDaoImpl();
		} catch (ClassNotFoundException e) {
			System.out.println("db 연결 오류");
			e.printStackTrace();
		}
	}
	public void loginProcess() {
		Employee e = null;
		String id = tfId.getText();
		String pw = String.valueOf(tfPassword.getPassword());
		e = employeedao.selectByID(id);
		if(!e.getId().equals(id)) {
			JOptionPane.showMessageDialog(null, "존재하지 않는 아이디입니다.");
		}else if(!e.getPassword().equals(pw)) {
			JOptionPane.showMessageDialog(null, "비밀번호가 다릅니다.");
		}else if(e.getId().equals(id) && e.getPassword().equals(pw)) {
			JOptionPane.showMessageDialog(null, "로그인 성공");
		}
			
	}
	public void moveToRegisterFrame() {
		
	}
}
