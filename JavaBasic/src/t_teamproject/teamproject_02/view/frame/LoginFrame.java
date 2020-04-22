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
	BackgroundAnimatedPanel panel;
	ImageIcon imageIcon = new ImageIcon("src\\t_teamproject\\teamproject_02\\imgs\\background\\loginFrameBackground.png");
	public LoginFrame() {
		employeedao=null;
		display();			//화면 배치
		connectDB();		//db 연결
		eventProc();		//이벤트 리스너 연결
	}
	class LoginFrameButtonEvent implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource()==signUp)		//signup버튼 클릭시 회원가입 창으로 이동
				moveToRegisterFrame();
			else if(e.getSource()==signIn)	//signin버튼 클릭시 로그인 확인 절차 진행
				loginProcess();
		}		
	}
	
	public void display() {
		try {	//움직이는 이미지를 배경으로 사용할 수 있는 패널 생성
			panel = new BackgroundAnimatedPanel("src\\t_teamproject\\teamproject_02\\imgs\\background\\loginFrameBackground.gif");
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
		signUp.addActionListener(l);	//signUp버튼에 이벤트리스너 연결
		signIn.addActionListener(l);	//signUp버튼에 이벤트리스너 연결
	}
	public void connectDB() {			//db연결
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
			e = employeedao.selectByID(id);								//	입력한 id값에 해당하는 직원 정보를 가져옴
		}catch(Exception e1){}	
		
		if(e.getId() == null || e.getPassword() == null) {				//  입력한 id값에 해당하는 직원 정보가 없다면 dialog창 실행
			JOptionPane.showMessageDialog(null, "로그인 실패");
		}else if(e.getId().equals(id) && e.getPassword().equals(pw)) {	//	입력한 id값에 해당하는 직원 정보가 존재하면서 직원정보의 비밀번호와 입력한 pw값이 동일하다면 현재 창을 종료하면서 다음 화면을 띄움(selectFrame)
			dispose();
			new SelectFrame(e);
		}
	}
	public void moveToRegisterFrame() {
		new RegisterFrame();			//	회원가입 프레임 새엇ㅇ
		dispose();						//	로그인 프레임 종료
	}
}
