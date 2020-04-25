package t_teamproject.teamproject_02.view.panel;

import java.awt.BorderLayout;
import java.awt.Dimension;
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
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import t_teamproject.teamproject_02.dao.ProductDao;
import t_teamproject.teamproject_02.dao.ProductDaoImpl;
import t_teamproject.teamproject_02.dao.ProductManagementDao;
import t_teamproject.teamproject_02.dao.ProductManagementDaoImpl;
import t_teamproject.teamproject_02.view.frame.ManagementFrame;
import t_teamproject.teamproject_02.view.frame.ProductPurchaseFrame;
import t_teamproject.teamproject_02.view.table.MyProductTableModel;
import t_teamproject.teamproject_02.vo.Product;

public class ProductManagementPanel extends JPanel{
	ProductDao productDaoModel;
	ProductManagementDao productManagementDaoModel;
	ManagementFrame managementFrame;
	
	JPanel left_panel, right_panel;
	
	JComboBox jcomboBoxSearchOption;
	JTextField jtextFieldSearch;
	
	JTable productTable;
	MyProductTableModel myProductTableModel;
	
	JButton productPurchaseButton;
	
	JTextField jtextFieldProductId;
	JComboBox jcomboBoxProductGroupName;
	JTextField jtextFieldProductName;
	JTextField jtextFieldProductPrice;
	JTextArea jtextAreaProductDetail;
	
	ImageIcon imageIcon = new ImageIcon("src\\t_teamproject\\teamproject_02\\imgs\\food\\default.jpg");
	JLabel producteImageLabel;
	JButton imageUploadButton, registerProductButton, updateProductButton, deleteProductButton;
	public ProductManagementPanel(ManagementFrame managementFrame) {	//상위 프레임의 다른 패널의 요소를 사용하기 위해 상위프레임의 객체 정보를 받아옴
		this.managementFrame = managementFrame;
		display();
		eventProc();
		connectDB();
		searchProduct(); // 프레임을 생성하자마자 검색을 해서 모든값이 나와있는상태로 시작하게 만듬
	}
	public void display() {
		left_panel = new JPanel();
		left_panel.setLayout(new BorderLayout());
		left_panel.setBorder(new TitledBorder("제품 검색"));
			//왼쪽 위 화면
			JPanel left_panel_up = new JPanel();
			String searchOption [] = {"이름", "내용"};
			jcomboBoxSearchOption = new JComboBox(searchOption);
			jtextFieldSearch = new JTextField(30);
			left_panel_up.add(jcomboBoxSearchOption);
			left_panel_up.add(jtextFieldSearch);
			//왼쪽 아래 화면
			JPanel left_panel_down = new JPanel();
			left_panel_down.setLayout(new GridLayout());
			myProductTableModel = new MyProductTableModel();
			productTable = new JTable(myProductTableModel);
			left_panel_down.add(new JScrollPane(productTable));
			
		left_panel.add(left_panel_up, BorderLayout.NORTH);
		left_panel.add(left_panel_down, BorderLayout.CENTER);
		
		//오른쪽 전체 화면		
		right_panel = new JPanel();
		right_panel.setLayout(new BorderLayout());
		right_panel.setBorder(new TitledBorder("제품 정보"));
			//오른쪽 위 화면
			JPanel right_panel_north = new JPanel();
			right_panel_north.setLayout(new GridLayout(1, 1));
			productPurchaseButton = new JButton("제품 주문");
			right_panel_north.add(productPurchaseButton);
			//오른쪽 가운데 화면
			JPanel right_panel_main = new JPanel();
			right_panel_main.setLayout(new GridLayout(3, 1));
				JPanel right_panel_main_north = new JPanel(); //이미지들어갈곳
				right_panel_main_north.setLayout(new GridLayout(1,1));
				producteImageLabel = new JLabel();
				producteImageLabel.setPreferredSize(new Dimension(400, 300));
				producteImageLabel.setIcon(imageIcon);
				producteImageLabel.setHorizontalAlignment(JLabel.CENTER);
				right_panel_main_north.add(producteImageLabel);
				
				JPanel right_panel_main_center = new JPanel(); // 필드값들
				right_panel_main_center.setLayout(new GridLayout(4, 2));
				
				right_panel_main_center.add(new JLabel("제품번호"));
				jtextFieldProductId = new JTextField();
				right_panel_main_center.add(jtextFieldProductId);
				right_panel_main_center.add(new JLabel("제품이름"));
				jtextFieldProductName = new JTextField();
				right_panel_main_center.add(jtextFieldProductName);
				right_panel_main_center.add(new JLabel("그룹이름"));
				String productGroupStringArray[] = {"메인메뉴", "사이드메뉴", "음료수", "세트메뉴"};
				jcomboBoxProductGroupName = new JComboBox(productGroupStringArray);
				right_panel_main_center.add(jcomboBoxProductGroupName);
				right_panel_main_center.add(new JLabel("제품가격"));
				jtextFieldProductPrice = new JTextField();
				right_panel_main_center.add(jtextFieldProductPrice);
							
				JPanel right_panel_main_south = new JPanel(); //설명 들어갈 곳
				right_panel_main_south.setLayout(new GridLayout(1,1));
				jtextAreaProductDetail = new JTextArea();

				right_panel_main_south.add(new JScrollPane(jtextAreaProductDetail));
			right_panel_main.add(right_panel_main_north);
			right_panel_main.add(right_panel_main_center);
			right_panel_main.add(right_panel_main_south);
			
			//오른쪽 아래 화면
			JPanel right_panel_south = new JPanel(); // 버튼 들어갈 패널
			right_panel_south.setLayout(new GridLayout(1,4));
			imageUploadButton = new JButton("이미지 등록");
			registerProductButton = new JButton("제품 등록");
			updateProductButton = new JButton("제품 수정");
			deleteProductButton = new JButton("제품 삭제");
			right_panel_south.add(imageUploadButton);
			right_panel_south.add(registerProductButton);
			right_panel_south.add(updateProductButton);
			right_panel_south.add(deleteProductButton);
			
		right_panel.add(right_panel_north, BorderLayout.NORTH);
		right_panel.add(right_panel_main, BorderLayout.CENTER);
		right_panel.add(right_panel_south, BorderLayout.SOUTH);	
			
		setLayout(null);
		left_panel.setBounds(0, 0, 1500, 960);
		right_panel.setBounds(1500, 0, 420, 960);
		add(left_panel);
		add(right_panel);
			
	}
	public void eventProc() {
		EventHandler eventHandler = new EventHandler();
		imageUploadButton.addActionListener(eventHandler);		// 이미지버튼 연결
		jtextFieldSearch.addActionListener(eventHandler);		// 검색 텍스트필드 연결
		registerProductButton.addActionListener(eventHandler);	// 정복 등록 버튼 연결
		updateProductButton.addActionListener(eventHandler);	// 정보 업데이트 버튼 연결
		deleteProductButton.addActionListener(eventHandler);	// 직원정보 삭제 버튼 연결
		productPurchaseButton.addActionListener(eventHandler);	// 제품 주문 버튼 연결
		productTable.addMouseListener(new MouseAdapter() {			
			@Override
			public void mouseClicked(MouseEvent e) {
				int row = productTable.getSelectedRow();
				int pid = Integer.parseInt((String)productTable.getValueAt(row, 0)); //맨앞의 값만 가져옴
				Product p = new Product();
				try {
					p=productDaoModel.selectByID(pid);								// 클릭한 행의 제품 식별번호(pid)에 해당하는 직원정보를 가져옴
				}catch(Exception e1) {
					e1.printStackTrace();
				}
				jtextFieldProductId.setText(Integer.toString(p.getId()));			//db에서 가져온 제품정보를 옆에 정보창에 띄우는 코드 시작
				jtextFieldProductName.setText(p.getName());
				jtextFieldProductPrice.setText(Integer.toString(p.getPrice()));
				jtextAreaProductDetail.setText(p.getDetail());
				jcomboBoxProductGroupName.setSelectedItem(p.getGroupName());		//db에서 가져온 제품정보를 옆에 정보창에 띄우는 코드 끝
				
				imageIcon = new ImageIcon("src\\t_teamproject\\teamproject_02\\imgs\\food\\" + p.getId() + ".gif");
				if(new File("src\\t_teamproject\\teamproject_02\\imgs\\food\\" + p.getId() + ".gif").exists()) {	//프로젝트 안의 지정된 경로에 제품의 pid.gif파일이 있다면 그 파일을 seticon()
					producteImageLabel.setIcon(imageIcon);
				}
				else {
					imageIcon = new ImageIcon("src\\t_teamproject\\teamproject_02\\imgs\\food\\default.jpg");		//프로젝트 안의 지정된 경로에 제품의 pid.gif파일이 없다면 기본 이미지파일을 seticon()
					producteImageLabel.setIcon(imageIcon);
				}
			}
		});
	}
	public void connectDB() {
		try {
			productDaoModel = new ProductDaoImpl();
			productManagementDaoModel = new ProductManagementDaoImpl();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	class EventHandler implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource()==imageUploadButton) {
				imageUpload();
			}else if(e.getSource()==jtextFieldSearch) {
				searchProduct();
			}else if(e.getSource()==registerProductButton){
				registerProduct();
			}else if(e.getSource()==updateProductButton) {
				updateProduct();
			}else if(e.getSource()==deleteProductButton) {
				deleteProduct();
			}else if(e.getSource()==productPurchaseButton) {
				openPurchaseFrame();
			}
		}		
	}
	/* 함수이름 : imageUpload
	 * 인자값 : 없음
	 * 반환값 : 없음
	 * 함수설명 : 이미지 업로드 버튼이 눌렸을 경우 JFileChooser를 띄움
	 * 			ㄴ JFileChooser로 선택한 이미지를 프로젝트의 지정된 경로에 저장
	 * 			ㄴ 제품정보의 경우 제품 식별번호(pid)와 같은 이름일 경우 프로그램 재실행시 해당 제품에 대한 정보에서 이미지를 볼 수 있음
	 * 함수 실행 시기 : 이미지 업로드 버튼을 클릭할경우 함수 실행
	 */
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

				FileOutputStream out = new FileOutputStream("src\\t_teamproject\\teamproject_02\\imgs\\food\\"+jfc.getSelectedFile().getName());
				JOptionPane.showMessageDialog(null, "이미지 업로드는 프로그램 재 실행 후 적용됩니다.");
				out.write(bytes);
				out.close();
			}catch (Exception e1) {
				e1.printStackTrace();
			}
		}
	}
	/* 함수이름 : searchProduct
	 * 인자값 : 없음
	 * 반환값 : 없음
	 * 함수설명 : 검색옵션(제품이름, 제품내용), 검색에 사용할 단어를 가져와서 그에 해당하는 제품정보들을 가져옴
	 * 			ㄴ 제품검색 테이블의 정보가 바뀌었음을 abstractTableModel을 통해서 알려줌
	 */
	public void searchProduct() {
		int searchOption = jcomboBoxSearchOption.getSelectedIndex();
		String searchWord = jtextFieldSearch.getText();
		try {
			ArrayList data = productDaoModel.searchProduct(searchOption, searchWord);
			myProductTableModel.setData(data);
			productTable.setModel(myProductTableModel);
			myProductTableModel.fireTableDataChanged();
		} catch (Exception e) {
		}
	}
	/* 함수이름 : registerProduct
	 * 인자값 : 없음
	 * 반환값 : 없음
	 * 함수설명 : 제품정보관리 화면에서 제품을 등록함
	 * 함수추가설명 : loginFrame(로그인 화면에서부터 전달받아왔던 로그인한 직원의 직급이 ADMIN일 경우에만 등록이 가능
	 * 			ㄴ DB 부분에서의 예외문제 발생시 오류 발생 처리(return값으로 0이 아닌 값을 받았을 경우)
	 */
	public void registerProduct() {
		if(managementFrame.getEmployee().getPosition().equals("ADMIN")) {
			Product p = new Product();
			p.setDetail(jtextAreaProductDetail.getText());
			p.setGroupName(jcomboBoxProductGroupName.getSelectedItem().toString());
			p.setId(Integer.parseInt(jtextFieldProductId.getText()));
			p.setName(jtextFieldProductName.getText());
			p.setPrice(Integer.parseInt(jtextFieldProductPrice.getText()));
			try {
				int result = productDaoModel.insertProduct(p);
				if(result==0) {
					searchProduct(); // 그대로 검색해서 새로고침 효과를 얻음
					JOptionPane.showMessageDialog(null, "제품 정보가 등록되었습니다. \n제품번호는 입력한번호와 관계없이 지정됩니다.");
				}else {
					JOptionPane.showMessageDialog(null, "제품 정보 등록에 오류가 발생했습니다.");
				}
			}catch(Exception e) {
				e.printStackTrace();
			}
		}else {
			JOptionPane.showMessageDialog(null, "제품 정보 등록은 관리자만 할 수 있습니다.");
		}

	}
	/* 함수이름 : updateProduct
	 * 인자값 : 없음
	 * 반환값 : 없음
	 * 함수설명 : 제품정보관리 화면에서 직원을 수정함
	 * 함수추가설명 : loginFrame(로그인 화면에서부터 전달받아왔던 로그인한 직원의 직급이 ADMIN일 경우에만 등록이 가능
	 * 	  			ㄴ 하나도 수정된 사항이 없을 경우 messageDialog 팝업(db부분의 함수의 return 값이 0일경우)
	 */
	public void updateProduct() {
		if(managementFrame.getEmployee().getPosition().equals("ADMIN")) {
			Product p = new Product();
			p.setDetail(jtextAreaProductDetail.getText());
			p.setGroupName(jcomboBoxProductGroupName.getSelectedItem().toString());
			p.setId(Integer.parseInt(jtextFieldProductId.getText()));
			p.setName(jtextFieldProductName.getText());
			p.setPrice(Integer.parseInt(jtextFieldProductPrice.getText()));
			try {				
				int result = productDaoModel.updateProduct(p);
				if(result==0) {
					JOptionPane.showMessageDialog(null, "변경사항이 없습니다.");
				}else {
					searchProduct(); // 그대로 검색을 다시해서 새로고침 효과를 얻음
					JOptionPane.showMessageDialog(null, "제품 정보가 업데이트 되었습니다.");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else {
			JOptionPane.showMessageDialog(null, "제품 정보 수정은 관리자만 할 수 있습니다.");
		}
	}
	/* 함수이름 : deleteProduct
	 * 인자값 : 없음
	 * 반환값 : 없음
	 * 함수설명 : 제품정보관리 화면에서 제품정보를 삭제함
	 * 함수추가설명 : loginFrame(로그인 화면에서부터 전달받아왔던 로그인한 직원의 직급이 ADMIN일 경우에만 등록이 가능
	 * 	  			ㄴ 하나도 수정된 사항이 없을 경우 messageDialog 팝업(db부분의 함수의 return 값이 0일경우)
	 */
	public void deleteProduct() {
		if(managementFrame.getEmployee().getPosition().equals("ADMIN")) {
			int pid = Integer.parseInt(jtextFieldProductId.getText());
			try {
				int result = productDaoModel.deleteProduct(pid); //product에서 pid에 해당하는 값 삭제
				if(result==0) {
					JOptionPane.showMessageDialog(null, "변경사항이 없습니다.");
				}else {
					searchProduct(); // 그대로 검색을 다시해서 새로고침 효과를 얻음
					JOptionPane.showMessageDialog(null, "제품 정보가 업데이트 되었습니다.");
				}	
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else {
			JOptionPane.showMessageDialog(null, "제품 정보 삭제는 관리자만 할 수 있습니다.");
		}
	}
	/* 함수이름 : openPurchaseFrame
	 * 인자값 : 없음
	 * 반환값 : 없음
	 * 함수설명 : 제품 구매창을 띄움
	 */
	public void openPurchaseFrame() {
		new ProductPurchaseFrame();

	}
}
