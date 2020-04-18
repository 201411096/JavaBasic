package t_teamproject.teamproject_02.temp.view.table;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

public class MyEmployeeTableModel extends AbstractTableModel{
	String columnName [] = {"아이디", "이름", "직책", "전화번호", "나이" , "급여", "입사일"};
	ArrayList data = new ArrayList();
	@Override
	public int getRowCount() {
		return data.size();
	}
	@Override
	public int getColumnCount() {
		return columnName.length;
	}
	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		ArrayList temp = (ArrayList)data.get(rowIndex);
		return temp.get(columnIndex);
	}
	@Override
	public String getColumnName(int col){
    	return columnName[col];
    }
	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		ArrayList temp = (ArrayList)data.get(rowIndex);
		temp.set(rowIndex, columnIndex);
		fireTableCellUpdated(rowIndex, columnIndex);
	}
	
	
}
