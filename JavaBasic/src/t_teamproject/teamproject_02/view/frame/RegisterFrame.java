package t_teamproject.teamproject_02.view.frame;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import t_teamproject.teamproject_02.dao.EmployeeDao;
import t_teamproject.teamproject_02.dao.EmployeeDaoImpl;
import t_teamproject.teamproject_02.vo.Employee;

public class RegisterFrame extends JFrame{	// 직원등록창(직원이 직접 등록하는 경우)
	EmployeeDao employeeDaomodel;
	
	JTextField jtextFieldEmployeeName;
	JTextField jtextFieldEmployeeTel;
	JTextField jtextFieldEmployeeId;
	JPasswordField jpasswordField, jpasswordField2;
	JTextField jtextFieldAge;
	
	JButton registerEmployeeButton, cancelButton;
	
	public RegisterFrame() {
		display();
		eventProc();
		connectDB();
	}
	public void display() {
		setLayout(null);
		
		JPanel north_panel = new JPanel();
			JLabel north_center_label = new JLabel("직원 정보 입력");
			north_panel.add(north_center_label);
		JPanel center_panel = new JPanel();
		center_panel.setLayout(new GridLayout(6, 2));
			center_panel.add(new JLabel("아이디"));
			jtextFieldEmployeeId = new JTextField();
			center_panel.add(jtextFieldEmployeeId);
			center_panel.add(new JLabel("비밀번호"));
			jpasswordField = new JPasswordField();
			center_panel.add(jpasswordField);
			center_panel.add(new JLabel("비밀번호확인"));
			jpasswordField2 = new JPasswordField();
			center_panel.add(jpasswordField2);
			center_panel.add(new JLabel("이름"));
			jtextFieldEmployeeName = new JTextField();
			center_panel.add(jtextFieldEmployeeName);
			center_panel.add(new JLabel("전화번호"));
			jtextFieldEmployeeTel = new JTextField();
			center_panel.add(jtextFieldEmployeeTel);
			center_panel.add(new JLabel("나이"));
			jtextFieldAge = new JTextField();
			center_panel.add(jtextFieldAge);	

		JPanel south_panel = new JPanel();
		south_panel.setLayout(new GridLayout(1, 2));
			registerEmployeeButton = new JButton("직원 정보 등록");
			cancelButton = new JButton("취소");
		south_panel.add(registerEmployeeButton);
		south_panel.add(cancelButton);

		north_panel.setBounds(0, 0, 400, 50);
		add(north_panel);
		center_panel.setBounds(0, 50, 400, 360);
		add(center_panel);
		south_panel.setBounds(0, 410, 400, 50);
		add(south_panel);
		
		setTitle("직원 등록 창");
		setBounds(750, 200, 400, 500);
		setVisible(true);
		setBackground(Color.white);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	public void eventProc() {
		EventHandler eventHandler = new EventHandler();
		registerEmployeeButton.addActionListener(eventHandler);
		cancelButton.addActionListener(eventHandler);
	}
	public void connectDB() {
		try {
			employeeDaomodel = new EmployeeDaoImpl();
		}catch (Exception e) {
			e.printStackTrace();
		}		
	}
	class EventHandler implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource()==registerEmployeeButton) {
				registerEmployee();
			}else if(e.getSource()==cancelButton) {
				registerCancel();
			}
		}		
	}
	/* 함수이름 : registerEmployee
	 * 인자값 : 없음
	 * 반환값 : 없음
	 * 함수설명 : 입력한 내용대로 직원정보 등록(회원가입)
	 */
	public void registerEmployee(){
		String pw1 = String.valueOf(jpasswordField.getPassword());
		String pw2 = String.valueOf(jpasswordField2.getPassword());
		if(jtextFieldEmployeeId.getText().isEmpty() || pw1.equals(null) || pw1.equals("") || jtextFieldEmployeeName.getText().isEmpty() || jtextFieldEmployeeTel.getText().isEmpty() || jtextFieldAge.getText().isEmpty()){
			JOptionPane.showMessageDialog(null, "값을 모두 입력해주세요");
		}
		else if(!pw1.equals(pw2))			//비밀번호와 비밀번호확인이 같은지 확인
		{
			JOptionPane.showMessageDialog(null, "비밀번호와 비빌먼호확인이 일치하지 않습니다.");
		}else{
			Employee emp = new Employee();
			emp.setId(jtextFieldEmployeeId.getText());
			emp.setName(jtextFieldEmployeeName.getText());
			emp.setPassword(String.valueOf(jpasswordField.getPassword()));
			emp.setTel(jtextFieldEmployeeTel.getText());
			emp.setAge(Integer.parseInt(jtextFieldAge.getText()));
			emp.setPosition("SALESMAN"); 	// 직접 직원정보 등록시(회원가입시) 직급은 salesman으로 고정
			try {
				int result = employeeDaomodel.insertEmployeeWithOutDate(emp);
				if(result == 0) {
					JOptionPane.showMessageDialog(null, "직원 정보가 등록되었습니다.");
					dispose();
					new LoginFrame();
				}else {
					JOptionPane.showMessageDialog(null, "이미 등록된 아이디 혹은 전화번호입니다.");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
		
	}
	/* 함수이름 : registerCancel
	 * 인자값 : 없음
	 * 반환값 : 없음
	 * 함수설명 : 로그인 창(LoginFrame)을 띄우면서 현재창을 닫음
	 */
	public void registerCancel(){
		new LoginFrame();
		dispose(); // 현재 창 비활성화
	}
}
