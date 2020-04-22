package t_teamproject.teamproject_02.view.panel;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import t_teamproject.teamproject_02.dao.EmployeeDao;
import t_teamproject.teamproject_02.dao.EmployeeDaoImpl;
import t_teamproject.teamproject_02.view.frame.ManagementFrame;
import t_teamproject.teamproject_02.view.table.MyEmployeeTableModel;
import t_teamproject.teamproject_02.vo.Employee;

public class EmployeeManageMentPanel extends JPanel{
	EmployeeDao employeeDaomodel;
	
	ManagementFrame managementFrame;
	
	JPanel left_panel, right_panel;
	
	JComboBox jcomboBoxSearchOption;
	JTextField jtextFieldSearch;
	
	JTable employeeTable;
	MyEmployeeTableModel myEmployeeTableModel;
	
	JTextField jtextFieldEmployeeName;
	JComboBox  jComboBoxEmployeePosition;
	JTextField jtextFieldEmployeeTel;
	JTextField jtextFieldEmployeeId;
	JTextField jtextFieldHire_date;
	JTextField jtextFieldSal;
	JTextField jtextFieldAge;
	
	ImageIcon imageIcon = new ImageIcon("src\\t_teamproject\\teamproject_02\\imgs\\employee\\default.jpg");
//	ImageIcon imageIcon = new ImageIcon("src\\t_teamproject\\teamproject_02\\temp\\imgs\\employee\\abc123.jpg");
	JLabel employeeImageLabel;
	JButton imageUploadButton, registerEmployeeButton, updateEmployeeButton, deleteEmployeeButton;
	public EmployeeManageMentPanel(ManagementFrame managementFrame) {
		this.managementFrame = managementFrame;
		display();
		eventProc();
		connectDB();
		searchEmployee(); // 프레임을 생성하자마자 검색을 해서 모든값이 나와있는상태로 시작하게 만듬
	}
	public void display() {
		//왼쪽 전체 화면
		left_panel = new JPanel();
		left_panel.setLayout(new BorderLayout());
		left_panel.setBorder(new TitledBorder("직원 검색"));
			//왼쪽 위 화면
			JPanel left_panel_up = new JPanel();
			String searchOption [] = {"이름", "아이디"};
			jcomboBoxSearchOption = new JComboBox(searchOption);
			jtextFieldSearch = new JTextField(30);
			left_panel_up.add(jcomboBoxSearchOption);
			left_panel_up.add(jtextFieldSearch);
			//왼쪽 아래 화면
			JPanel left_panel_down = new JPanel();
			left_panel_down.setLayout(new GridLayout());
			myEmployeeTableModel = new MyEmployeeTableModel();
			employeeTable = new JTable(myEmployeeTableModel);
			left_panel_down.add(new JScrollPane(employeeTable));
		
		left_panel.add(left_panel_up, BorderLayout.NORTH);
		left_panel.add(left_panel_down, BorderLayout.CENTER);


		
		//오른쪽 전체 화면		
		right_panel = new JPanel();
		right_panel.setLayout(new BorderLayout());
		right_panel.setBorder(new TitledBorder("직원 정보"));
			//오른쪽 위 화면
			JPanel right_panel_north_wrapper = new JPanel();
			right_panel_north_wrapper.setLayout(new GridLayout(2, 1));
				JPanel right_panel_north = new JPanel();
	
				employeeImageLabel = new JLabel();
				employeeImageLabel.setPreferredSize(new Dimension(400, 300));
				employeeImageLabel.setIcon(imageIcon);
				employeeImageLabel.setHorizontalAlignment(JLabel.CENTER);
				right_panel_north.add(employeeImageLabel);
				//오른쪽 가운데 화면
				JPanel right_panel_center = new JPanel();
				right_panel_center.setLayout(new FlowLayout());			
					JPanel right_panel_center_wrapper = new JPanel();
					right_panel_center_wrapper.setLayout(new GridLayout(7, 2));
					right_panel_center_wrapper.add(new JLabel("이름"));
					jtextFieldEmployeeName = new JTextField();
					right_panel_center_wrapper.add(jtextFieldEmployeeName);
					right_panel_center_wrapper.add(new JLabel("직책"));
					String positionStringArray [] = {"ADMIN", "MANAGER", "SALESMAN"};
					jComboBoxEmployeePosition = new JComboBox(positionStringArray);
					right_panel_center_wrapper.add(jComboBoxEmployeePosition);
					right_panel_center_wrapper.add(new JLabel("전화번호"));
					jtextFieldEmployeeTel = new JTextField();
					right_panel_center_wrapper.add(jtextFieldEmployeeTel);
					right_panel_center_wrapper.add(new JLabel("아이디"));
					jtextFieldEmployeeId = new JTextField();
					right_panel_center_wrapper.add(jtextFieldEmployeeId);
					right_panel_center_wrapper.add(new JLabel("입사일자"));
					jtextFieldHire_date = new JTextField();
					right_panel_center_wrapper.add(jtextFieldHire_date);
					right_panel_center_wrapper.add(new JLabel("급여"));
					jtextFieldSal = new JTextField();
					right_panel_center_wrapper.add(jtextFieldSal);
					right_panel_center_wrapper.add(new JLabel("나이"));
					jtextFieldAge = new JTextField();
					right_panel_center_wrapper.add(jtextFieldAge);
				right_panel_center.add(right_panel_center_wrapper);
			right_panel_north_wrapper.add(right_panel_north);
			right_panel_north_wrapper.add(right_panel_center_wrapper);
			
			//오른쪽 아래 화면
			JPanel right_panel_south = new JPanel();
			right_panel_south.setLayout(new GridLayout(1, 4));
			imageUploadButton = new JButton("이미지 등록");
			registerEmployeeButton = new JButton("직원 등록");
			updateEmployeeButton = new JButton("직원 수정");
			deleteEmployeeButton = new JButton("직원 삭제");
			right_panel_south.add(imageUploadButton);
			right_panel_south.add(registerEmployeeButton);
			right_panel_south.add(updateEmployeeButton);
			right_panel_south.add(deleteEmployeeButton);
			
		right_panel.add(right_panel_north_wrapper, BorderLayout.NORTH);
//		right_panel.add(right_panel_center, BorderLayout.CENTER);
		right_panel.add(right_panel_south, BorderLayout.SOUTH);
		
		setLayout(null);
		left_panel.setBounds(0, 0, 1500, 960);
		right_panel.setBounds(1500, 0, 420, 960);
		add(left_panel);
		add(right_panel);

	}
	public void connectDB() {
		try {
			employeeDaomodel = new EmployeeDaoImpl();
		}catch (Exception e) {
			e.printStackTrace();
		}		
	}	
	public void eventProc() {
		EventHandler eventHandler = new EventHandler();
		imageUploadButton.addActionListener(eventHandler); // 이미지버튼 연결
		jtextFieldSearch.addActionListener(eventHandler); // 검색 텍스트필드 연결
		registerEmployeeButton.addActionListener(eventHandler); // 정복 등록 버튼 연결
		updateEmployeeButton.addActionListener(eventHandler); // 정보 업데이트 버튼 연결
		deleteEmployeeButton.addActionListener(eventHandler); // 직원정보 삭제 버튼 연결
 		
		employeeTable.addMouseListener(new MouseAdapter() {			
			@Override
			public void mouseClicked(MouseEvent e) {
				int row = employeeTable.getSelectedRow();
				String eid = (String)employeeTable.getValueAt(row, 0); // 첫번째 아이디값만 가져옴
				Employee emp = new Employee();
				try {
					emp = employeeDaomodel.selectByID(eid);
				}catch(Exception e1) {
					e1.printStackTrace();
				}
				jtextFieldEmployeeId.setText(emp.getId());
				jtextFieldEmployeeName.setText(emp.getName());
				jtextFieldEmployeeTel.setText(emp.getTel());
				jtextFieldHire_date.setText(emp.getHire_date().substring(0, 11));
				jtextFieldSal.setText(Integer.toString(emp.getSal()));
				jtextFieldAge.setText(Integer.toString(emp.getAge()));
				jComboBoxEmployeePosition.setSelectedItem(emp.getPosition());
				
				imageIcon = new ImageIcon("src\\t_teamproject\\teamproject_02\\imgs\\employee\\" + emp.getId() + ".gif");
				if(new File("src\\t_teamproject\\teamproject_02\\imgs\\employee\\" + emp.getId() + ".gif").exists()) {
					employeeImageLabel.setIcon(imageIcon);
				}
				else {
					imageIcon = new ImageIcon("src\\t_teamproject\\teamproject_02\\imgs\\employee\\default.jpg");
					employeeImageLabel.setIcon(imageIcon);
				}				
			}
		});
	}
	
	class EventHandler implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource()==imageUploadButton) {
				imageUpload();
			}else if(e.getSource()==jtextFieldSearch) {
				searchEmployee();
			}else if(e.getSource()==registerEmployeeButton){
				registerEmployee();
			}else if(e.getSource()==updateEmployeeButton) {
				updateEmployee();
			}else if(e.getSource()==deleteEmployeeButton) {
				deleteEmployee();
			}
		}		
	}
	public void imageUpload() {
		JFileChooser jfc = new JFileChooser();
		int result = jfc.showSaveDialog(null);
		if(result==0)
		{
			File file = jfc.getSelectedFile();
			try {
				byte [] bytes = new byte[(int)file.length()];
				DataInputStream in = new DataInputStream(new FileInputStream(file));
				in.readFully(bytes);
				in.close();
				String fileName = jfc.getSelectedFile().getName();//
//				FileOutputStream out = new FileOutputStream("src\\t_teamproject\\teamproject_02\\imgs\\employee\\"+jfc.getSelectedFile().getName());
				FileOutputStream out = new FileOutputStream("src\\t_teamproject\\teamproject_02\\imgs\\employee\\"+fileName);
				JOptionPane.showMessageDialog(null, "이미지 업로드는 프로그램 재 실행 후 적용됩니다.");
				
				out.write(bytes);
				out.close();
				// 왜 적용이 안되는지 모름 //이미지가 런타임상에서 잡히질 않음
				
//				imageIcon = new ImageIcon(getClass().getResource("src\\t_teamproject\\teamproject_02\\temp\\imgs\\employee\\" + fileName));
//				imageIcon = new ImageIcon("src\\t_teamproject\\teamproject_02\\temp\\imgs\\employee\\"+fileName);
//				System.out.println(jfc.getSelectedFile().getName());
//				employeeImageLabel.setIcon(imageIcon);
//				managementFrame.repaint();
//				managementFrame.revalidate();
			}catch (Exception e1) {
				e1.printStackTrace();
			}
		}
	}
	public void searchEmployee() {
		int searchOption = jcomboBoxSearchOption.getSelectedIndex();
		String searchWord = jtextFieldSearch.getText();
		try {
			ArrayList data = employeeDaomodel.searchEmployee(searchOption, searchWord);
			myEmployeeTableModel.setData(data);
			employeeTable.setModel(myEmployeeTableModel);
			myEmployeeTableModel.fireTableDataChanged();
		}catch(Exception e){
			System.out.println("EmployeeManageMentPanel_searchEmployee 에러");
			e.printStackTrace();
		}
	}
	public void registerEmployee() {
		if(managementFrame.getEmployee().getPosition().equals("ADMIN")) {
			Employee vo = new Employee();
			vo.setId(jtextFieldEmployeeId.getText());
			vo.setPassword(jtextFieldEmployeeId.getText()); // 직원관리 창에서 등록시 비밀번호는 아이디와 같은 string으로 등록되게 됨
			vo.setName(jtextFieldEmployeeName.getText());
			vo.setPosition(jComboBoxEmployeePosition.getSelectedItem().toString());
			vo.setTel(jtextFieldEmployeeTel.getText());
			vo.setHire_date(jtextFieldHire_date.getText());
			vo.setSal(Integer.parseInt(jtextFieldSal.getText()));
			vo.setAge(Integer.parseInt(jtextFieldAge.getText()));
			try {
				int result = employeeDaomodel.insertEmployee(vo);
				if(result==0) {
					searchEmployee(); // 그대로 검색을 다시해서 새로고침 효과를 얻음
					JOptionPane.showMessageDialog(null, "직원 정보가 등록되었습니다.");
				}else {
					JOptionPane.showMessageDialog(null, "직원 정보 등록에 오류가 발생하였습니다.");
				}

			} catch (Exception e) {
				e.printStackTrace();
			}	

		}else {
			JOptionPane.showMessageDialog(null, "직원 정보 등록은 관리자만 할 수 있습니다.");
		}
	}
	public void updateEmployee() {
		if(managementFrame.getEmployee().getPosition().equals("ADMIN")) {
			Employee vo = new Employee();
			vo.setId(jtextFieldEmployeeId.getText());
			vo.setName(jtextFieldEmployeeName.getText());
			vo.setPosition(jComboBoxEmployeePosition.getSelectedItem().toString());
			vo.setTel(jtextFieldEmployeeTel.getText());
			vo.setHire_date(jtextFieldHire_date.getText());
			vo.setSal(Integer.parseInt(jtextFieldSal.getText()));
			vo.setAge(Integer.parseInt(jtextFieldAge.getText()));
			try {				
				int result = employeeDaomodel.updateEmployee(vo);
				if(result==0) {
					JOptionPane.showMessageDialog(null, "변경사항이 없습니다.");
				}else {
					searchEmployee(); // 그대로 검색을 다시해서 새로고침 효과를 얻음
					JOptionPane.showMessageDialog(null, "직원 정보가 업데이트 되었습니다.");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else {
			JOptionPane.showMessageDialog(null, "직원 정보 수정은 관리자만 할 수 있습니다.");
		}
	}
	public void deleteEmployee() {
		if(managementFrame.getEmployee().getPosition().equals("ADMIN")) {
			String eid = jtextFieldEmployeeId.getText();
			try {
				int result = employeeDaomodel.deleteEmployee(eid);
				if(result==0) {
					JOptionPane.showMessageDialog(null, "변경사항이 없습니다.");
				}else {
					searchEmployee(); // 그대로 검색을 다시해서 새로고침 효과를 얻음
					JOptionPane.showMessageDialog(null, "직원 정보가 업데이트 되었습니다.");
				}	
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else {
			JOptionPane.showMessageDialog(null, "직원 정보 삭제는 관리자만 할 수 있습니다.");
		}
	}
}
