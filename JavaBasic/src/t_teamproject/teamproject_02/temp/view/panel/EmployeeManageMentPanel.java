package t_teamproject.teamproject_02.temp.view.panel;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;

import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import t_teamproject.teamproject_02.temp.view.ManagementFrame;
import t_teamproject.teamproject_02.temp.view.table.MyEmployeeTableModel;

public class EmployeeManageMentPanel extends JPanel{
	JPanel left_panel, right_panel;
	
	JComboBox jcomboBoxSearchOption;
	JTextField jtextFieldSearch;
	
	JTable employeeTable;
	MyEmployeeTableModel myEmployeeTableModel;
	
	ManagementFrame managementFrame;
	public EmployeeManageMentPanel(ManagementFrame managementFrame) {
		this.managementFrame = managementFrame;
		display();
	}
	public void display() {
		GridBagLayout gbl = new GridBagLayout();
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.BOTH;
		setLayout(gbl);
//		setLayout(new GridLayout(1, 2));
		//왼쪽 전체 화면
		left_panel = new JPanel();
		
		//오른쪽 전체 화면		
		
		right_panel = new JPanel();
		right_panel.setLayout(new BorderLayout());
		right_panel.setBorder(new TitledBorder("직원 검색"));
			//오른쪽 위 화면
			JPanel right_panel_up = new JPanel();
			String searchOption [] = {"이름", "급여"};
			jcomboBoxSearchOption = new JComboBox(searchOption);
			jtextFieldSearch = new JTextField(30);
			right_panel_up.add(jcomboBoxSearchOption);
			right_panel_up.add(jtextFieldSearch);
			//오른쪽 아래 화면
			JPanel right_panel_down = new JPanel();
			right_panel_down.setLayout(new GridLayout());
			myEmployeeTableModel = new MyEmployeeTableModel();
			employeeTable = new JTable(myEmployeeTableModel);
			right_panel_down.add(new JScrollPane(employeeTable));
		
		right_panel.add(right_panel_up, BorderLayout.NORTH);
		right_panel.add(right_panel_down, BorderLayout.CENTER);
		
		addGrid(gbl, gbc, left_panel, 0, 0, 2, 1, 1, 1);
		addGrid(gbl, gbc, right_panel, 2, 0, 1, 1, 1, 1);

	}

	public void addGrid(GridBagLayout gbl, GridBagConstraints gbc, Component c, 
            int gridx, int gridy, int gridwidth, int gridheight, int weightx, int weighty) {
      gbc.gridx = gridx;
      gbc.gridy = gridy;
      gbc.gridwidth = gridwidth;
      gbc.gridheight = gridheight;
      gbc.weightx = weightx;
      gbc.weighty = weighty;
      gbl.setConstraints(c, gbc);
      add(c);
	}
}
