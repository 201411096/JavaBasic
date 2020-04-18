package t_teamproject.teamproject_02.temp.view.panel;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import t_teamproject.teamproject_02.temp.view.ManagementFrame;
import t_teamproject.teamproject_02.temp.view.table.MyEmployeeTableModel;

public class EmployeeManageMentPanel extends JPanel{
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
	
	
	ImageIcon imageIcon = new ImageIcon("src\\t_teamproject\\teamproject_02\\temp\\imgs\\employee\\default.jpg");
//	ImageIcon imageIcon = new ImageIcon("src\\t_teamproject\\teamproject_02\\temp\\imgs\\employee\\abc123.jpg");
	JLabel employeeImageLabel;
	JButton imageUploadButton, updateEmployeeButton, deleteEmployeeButton;
	public EmployeeManageMentPanel(ManagementFrame managementFrame) {
		this.managementFrame = managementFrame;
		display();
		eventProc();
	}
	public void display() {
		//왼쪽 전체 화면
		left_panel = new JPanel();
		left_panel.setLayout(new BorderLayout());
		left_panel.setBorder(new TitledBorder("직원 검색"));
			//왼쪽 위 화면
			JPanel left_panel_up = new JPanel();
			String searchOption [] = {"이름", "급여"};
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
				employeeImageLabel.setIcon(imageIcon);
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
					String positionStringArray [] = {"MANAGER", "SALESMAN"};
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
	public void eventProc() {
		imageUploadButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("살려줘 야발");
				
			}
		});
	}

}
