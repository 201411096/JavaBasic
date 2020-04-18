package t_teamproject.teamproject_02.temp.view.panel;

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
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;

import t_teamproject.teamproject_02.temp.dao.EmployeeDao;
import t_teamproject.teamproject_02.temp.dao.EmployeeDaoImpl;
import t_teamproject.teamproject_02.temp.view.ManagementFrame;
import t_teamproject.teamproject_02.temp.view.table.MyEmployeeTableModel;
import t_teamproject.teamproject_02.temp.vo.Employee;

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
	
	ImageIcon imageIcon = new ImageIcon("src\\t_teamproject\\teamproject_02\\temp\\imgs\\employee\\default.jpg");
//	ImageIcon imageIcon = new ImageIcon("src\\t_teamproject\\teamproject_02\\temp\\imgs\\employee\\abc123.jpg");
	JLabel employeeImageLabel;
	JButton imageUploadButton, updateEmployeeButton, deleteEmployeeButton;
	public EmployeeManageMentPanel(ManagementFrame managementFrame) {
		this.managementFrame = managementFrame;
		display();
		eventProc();
		connectDB();
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
				employeeImageLabel.setPreferredSize(new Dimension(300, 300));
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
			right_panel_south.setLayout(new GridLayout(1, 3));
			imageUploadButton = new JButton("이미지 등록");
			updateEmployeeButton = new JButton("직원정보 수정");
			deleteEmployeeButton = new JButton("직원정보 삭제");
			right_panel_south.add(imageUploadButton);
			right_panel_south.add(updateEmployeeButton);
			right_panel_south.add(deleteEmployeeButton);
			
		right_panel.add(right_panel_north_wrapper, BorderLayout.NORTH);
//		right_panel.add(right_panel_center, BorderLayout.CENTER);
		right_panel.add(right_panel_south, BorderLayout.SOUTH);
		
		setLayout(null);
		left_panel.setBounds(0, 0, 1600, 960);
		right_panel.setBounds(1600, 0, 320, 960);
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
		imageUploadButton.addActionListener(eventHandler);
		jtextFieldSearch.addActionListener(eventHandler);
		
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
				jtextFieldHire_date.setText(emp.getHire_date());
				jtextFieldSal.setText(Integer.toString(emp.getSal()));
				jtextFieldAge.setText(Integer.toString(emp.getAge()));
				jComboBoxEmployeePosition.setSelectedItem(emp.getPosition());
				System.out.println(emp.getPosition());
				imageIcon = new ImageIcon("src\\t_teamproject\\teamproject_02\\temp\\imgs\\employee\\" + emp.getId() + ".jpg");
				if(new File("src\\t_teamproject\\teamproject_02\\temp\\imgs\\employee\\" + emp.getId() + ".jpg").exists()) {
					employeeImageLabel.setIcon(imageIcon);
				}
				else {
					imageIcon = new ImageIcon("src\\t_teamproject\\teamproject_02\\temp\\imgs\\employee\\default.jpg");
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
				System.out.println("???");
				byte [] bytes = new byte[(int)file.length()];
				DataInputStream in = new DataInputStream(new FileInputStream(file));
				in.readFully(bytes);
				in.close();

				FileOutputStream out = new FileOutputStream("src\\t_teamproject\\teamproject_02\\temp\\imgs\\employee\\"+jfc.getSelectedFile().getName());
				JOptionPane.showMessageDialog(null, "이미지 업로드는 프로그램 재 실행 후 적용됩니다.");
				out.write(bytes);
				out.close();
				// 왜 적용이 안되는지 모름
//				imageIcon = new ImageIcon("src\\t_teamproject\\teamproject_02\\temp\\imgs\\employee\\"+jfc.getSelectedFile().getName());
//				System.out.println(jfc.getSelectedFile().getName());
//				employeeImageLabel.setIcon(imageIcon);
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
}
