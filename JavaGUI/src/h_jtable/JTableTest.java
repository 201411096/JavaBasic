package h_jtable;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;

public class JTableTest extends JFrame {

   JTable table;
   JButton btn;
   MyTableModel myTM;

   public JTableTest() {
      // 객체생성
      myTM = new MyTableModel(); // JTable의 데이타와 컬럼명 ( Model )
      table = new JTable(myTM); // JTable의 View
      btn = new JButton("확인");
      // 화면구성
      add(new JScrollPane(table), BorderLayout.CENTER);
      add(btn, BorderLayout.NORTH);

      // 화면출력
      // setBounds( x, y, width,height);
      setBounds(100, 100, 500, 400);
      setVisible(true);
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

      // 이벤트처리
      btn.addActionListener(new ActionListener() {

         @Override
         public void actionPerformed(ActionEvent e) {
            change();
         }
      });
      table.addMouseListener(new MouseAdapter(){
         public void mouseClicked(MouseEvent e ) {
            int row = table.getSelectedRow();
            int col = table.getSelectedColumn();
            System.out.println(row + ":" +col+ " 이벤트 발생");
         }
      });
   }

   void change() {
      ArrayList data = new ArrayList();
      for(int i = 0 ; i < 5; i ++ ) {
         ArrayList temp = new ArrayList();
         for (int j = 0;j<4 ; j++) { 
            if(j==0) temp.add(new Boolean(false));
            temp.add(new Integer(j+1)); //int ->integer 자동변환
         }
         data.add(temp);
      }
      myTM.data = data;
      table.setModel(myTM);
      myTM.fireTableDataChanged(); // 모델측에서 화면(뷰)한테 내용변경됨을 알려줘야 함
   }

   class MyTableModel extends AbstractTableModel {
      String[] columnName = { "하나", "둘", "삼", "사" };
      ArrayList data = new ArrayList();

      public int getColumnCount() {
         return columnName.length;
      }

      public int getRowCount() {
         return data.size();
      }

      public Object getValueAt(int rowIndex, int columnIndex) {
         ArrayList temp = (ArrayList) data.get(rowIndex);
         return temp.get(columnIndex);
      }

      public String getColumnName(int c) {
         return columnName[c];
      }

      public Class getColumnClass(int c) {
         return getValueAt(0, c).getClass();
      }
      
      public boolean isCellEditable(int row , int col) {
         if(col < 2) return true;
         else return false;
      }
      
      public void setValueAt(Object value, int row, int col) {
         ArrayList temp = (ArrayList)data.get(row);
         temp.set(col, value);
         fireTableCellUpdated(row, col);
      }
   }

   public static void main(String[] args) {
      JTableTest jt = new JTableTest();

   }

}