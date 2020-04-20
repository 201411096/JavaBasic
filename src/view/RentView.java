package  view;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.table.AbstractTableModel;

import model.dao.RentModel;



public class RentView extends JPanel 
{
	JTextField tfRentTel, tfRentCustName, tfRentVideoNum;
	JButton bRent;
	
	JTextField tfReturnVideoNum;
	JButton bReturn;
	
	JTable tableRecentList;	
	RentTableModel rentTM;
	
	//모델단 변수 선언
	RentModel model;
	
	//==============================================
	//	 생성자 함수
	public RentView(){
		addLayout();	//화면구성
		eventProc();	
		connectDB();	//DB연결
		
		selectList();
	}
	void selectList() {
		try {
			ArrayList data = model.selectList();
			rentTM.data = data;
			tableRecentList.setModel(rentTM); // 모델 설정
			rentTM.fireTableDataChanged();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	// DB 연결
	void connectDB(){
		try {
			model = new RentModel();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	// 이벤트 등록
	public void eventProc(){
		BtnHandler btnHandler = new BtnHandler();
		tfRentTel.addActionListener(btnHandler);
		bRent.addActionListener(btnHandler);
		bReturn.addActionListener(btnHandler);
	}
	class BtnHandler implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			Object evt = e.getSource();
			if(evt==tfRentTel) {
				selectByTel();
			}else if(evt==bRent) { //대여처리를 한 후에 목록을 갱신
				rentVideo();
				selectList();
			}else if(evt==bReturn) {//반납처리를 한 후에 목록을 갱신
				returnVideo();
				selectList();
			}
		}
	}	
	/*	객체 생성 및 화면 구성   */
	void addLayout(){
		setLayout(new BorderLayout());
		tfRentTel = new JTextField();
		tfRentCustName = new JTextField();
		tfRentVideoNum = new JTextField();
		bRent = new JButton("대여");
		tfReturnVideoNum = new JTextField(15);
		bReturn = new JButton("반납");
		rentTM = new RentTableModel();
		tableRecentList = new JTable(rentTM);
		
		JPanel north_panel = new JPanel();
		north_panel.setLayout(new GridLayout(1,2));
			JPanel north_left_panel = new JPanel();
			north_left_panel.setBorder(new TitledBorder("대여"));
			north_left_panel.setLayout(new GridLayout(4,2));
			north_left_panel.add(new JLabel("전 화 번 호"));
			north_left_panel.add(tfRentTel);
			north_left_panel.add(new JLabel("고 객 명"));
			north_left_panel.add(tfRentCustName);
			north_left_panel.add(new JLabel("비디오 번호"));
			north_left_panel.add(tfRentVideoNum);
			north_left_panel.add(bRent);
			
			JPanel north_right_panel = new JPanel();
			north_right_panel.setBorder(new TitledBorder("반납"));
			north_right_panel.add(new JLabel("비디오 번호"));
			north_right_panel.add(tfReturnVideoNum);
			north_right_panel.add(bReturn);
		
		north_panel.add(north_left_panel);
		north_panel.add(north_right_panel);
		
		JPanel center_panel = new JPanel();
		center_panel.setLayout(new GridLayout(1, 1));
		center_panel.add(new JScrollPane(tableRecentList));
		
		add(north_panel, BorderLayout.NORTH);
		add(center_panel, BorderLayout.CENTER);
	}

	class RentTableModel extends AbstractTableModel { 
		ArrayList data = new ArrayList();
		String [] columnNames = {"비디오번호","제목","고객명","전화번호","반납예정일","반납여부"};
			public int getColumnCount() { 
		        return columnNames.length; 
		    } 
		    public int getRowCount() { 
		        return data.size(); 
		    } 
		    public Object getValueAt(int row, int col) { 
				ArrayList temp = (ArrayList)data.get( row );
		        return temp.get( col ); 
		    }	    
		    public String getColumnName(int col){
		    	return columnNames[col];
		    }
	}
	void selectByTel() {
		try {
			String name = model.selectByTel(tfRentTel.getText());
			tfRentCustName.setText(name);
		} catch (Exception e) {
			e.printStackTrace();
		}	
	}
	void rentVideo() {
		String tel = tfRentTel.getText();
		int vnum = Integer.parseInt(tfRentVideoNum.getText());		
		try {
			model.rentVideo(tel, vnum);
		} catch (Exception e) {
			e.printStackTrace();
		}
		tfRentTel.setText("");
		tfRentCustName.setText("");
		tfRentVideoNum.setText("");
	}
	void returnVideo() {
		int vnum = Integer.parseInt(tfReturnVideoNum.getText());
		try {
			model.returnVideo(vnum);
		} catch (Exception e) {
			e.printStackTrace();
		}
		tfReturnVideoNum.setText("");
	}
}