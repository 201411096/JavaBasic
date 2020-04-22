package t_teamproject.teamproject_02.view.table;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

public class MyProductTableModel extends AbstractTableModel{				//제품 테이블을 구성하는 모델 테이블
	private String columNames [] = {"메뉴번호", "메뉴그룹", "메뉴이름", "메뉴가격"};	//컬럼 이름 지정
	ArrayList data = new ArrayList();
	@Override
	public int getRowCount() {
		return data.size();
	}
	@Override
	public int getColumnCount() {
		return columNames.length;
	}
	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		ArrayList temp = (ArrayList)data.get(rowIndex);
		return temp.get(columnIndex);
	}
	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		ArrayList temp = (ArrayList)data.get(rowIndex);
		temp.set(rowIndex, columnIndex);
		fireTableCellUpdated(rowIndex, columnIndex);
	}
	@Override
	public String getColumnName(int col){
    	return columNames[col];
    }
	public void setColumnName(String[] columnName) {
		this.columNames = columnName;
	}
	public ArrayList getData() {
		return data;
	}
	public void setData(ArrayList data) {
		this.data = data;
	}
}
