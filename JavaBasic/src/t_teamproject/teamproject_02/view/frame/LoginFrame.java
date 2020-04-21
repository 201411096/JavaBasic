package t_teamproject.teamproject_02.view.frame;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import t_teamproject.teamproject_02.dao.EmployeeDao;
import t_teamproject.teamproject_02.dao.EmployeeDaoImpl;
import t_teamproject.teamproject_02.view.panel.BackgroundAnimatedPanel;
import t_teamproject.teamproject_02.vo.Employee;

public class LoginFrame extends JFrame{
	EmployeeDao employeedao;
	JTextField tfId;
	JPasswordField tfPassword;
	JButton signUp, signIn;
//	BackgroundPanel panel;
	BackgroundAnimatedPanel panel;
	ImageIcon imageIcon = new ImageIcon("src\\t_teamproject\\teamproject_02\\imgs\\background\\loginFrameBackground.png");
	public LoginFrame() {
		employeedao=null;
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
//		panel = new BackgroundPanel(imageIcon.getImage());
		try {
			panel = new BackgroundAnimatedPanel();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		JPanel wrapperPanel = new JPanel();
		
		wrapperPanel.setLayout(new GridLayout(3,2));
		JLabel idLabel = new JLabel("    ID");
		idLabel.setOpaque(true);
		idLabel.setBackground(new Color(255, 255, 255));
		wrapperPanel.add(idLabel);
		tfId = new JTextField();
		wrapperPanel.add(tfId);
		JLabel pwLabel = new JLabel("   PASSWORD");
		pwLabel.setOpaque(true);
		pwLabel.setBackground(new Color(255, 255, 255));
		wrapperPanel.add(pwLabel);
		tfPassword = new JPasswordField();
		wrapperPanel.add(tfPassword);
		signUp = new JButton("회원가입");
		signIn = new JButton("로그인");
		wrapperPanel.add(signUp);
		wrapperPanel.add(signIn);
		
		panel.setLayout(null);
		wrapperPanel.setBounds(75, 70, 300, 100);
		panel.add(wrapperPanel);
		
		setLayout(new GridLayout());
		add(panel);
		setTitle("로그인화면");
		setBounds(750, 400, 450, 300);
		setResizable(false);
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
		try {
			e = employeedao.selectByID(id);
		}catch(Exception e1){}	
		
		if(e.getId() == null || e.getPassword() == null) {
			JOptionPane.showMessageDialog(null, "로그인 실패");
		}else if(e.getId().equals(id) && e.getPassword().equals(pw)) {
			dispose();
			new SelectFrame(e);
		}
	}
	public void moveToRegisterFrame() {
		new RegisterFrame();
		dispose();
	}
}
/*
public void display() {
setTitle("로그인화면");
setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
setBounds(750, 400, 450, 300);
contentPane = new JPanel();
contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
setContentPane(contentPane);
contentPane.setLayout(null);
signIn = new JButton("로그인");
signIn.addActionListener(new ActionListener() {
	public void actionPerformed(ActionEvent e) {
	}
});
signIn.setBounds(227, 150, 97, 23);
contentPane.add(signIn);

signUp = new JButton("회원가입");
signUp.addActionListener(new ActionListener() {
	public void actionPerformed(ActionEvent e) {
	}
});
signUp.setBounds(118, 150, 97, 23);
contentPane.add(signUp);

tfId = new JTextField();
tfId.setBounds(208, 70, 116, 21);
contentPane.add(tfId);
tfId.setColumns(10);

JLabel lblNewLabel = new JLabel("ID");
lblNewLabel.setBounds(118, 73, 57, 15);
contentPane.add(lblNewLabel);

JLabel lblNewLabel_1 = new JLabel("PW");
lblNewLabel_1.setBounds(118, 100, 57, 15);
contentPane.add(lblNewLabel_1);

tfPassword = new JPasswordField();
tfPassword.setBounds(208, 101, 116, 21);
contentPane.add(tfPassword);
setResizable(false);
setVisible(true);
setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
}
*/