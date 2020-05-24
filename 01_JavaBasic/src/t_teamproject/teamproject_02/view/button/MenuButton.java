package t_teamproject.teamproject_02.view.button;

import java.awt.Color;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import t_teamproject.teamproject_02.vo.Product;

public class MenuButton extends JButton{	//	제품 정보가 담긴 버튼(계산창에서)
	Product p;
	ImageIcon imageIcon;
	public MenuButton(Product p) {
		this.p=p;
		display();
		eventProc();
	}
	public void display() {					//	화면 배치시에 제품 이미지 세팅
		setBackground(Color.white);
		setImageIcon();
	}
	public void setImageIcon() {			//  제품의 식별번호(pid)와 같은이름의 .gif파일이 프로젝트의 지정된 경로에 존재할 경우 해당 이미지 사용  (jfc를 사용한 이미지 등록버튼을 이용하여 이미지를 지정된 경로에 저장 가능)
		if(new File("src\\t_teamproject\\teamproject_02\\imgs\\food\\" + p.getId() + ".gif").exists()) {
			imageIcon = new ImageIcon("src\\t_teamproject\\teamproject_02\\imgs\\food\\" + p.getId() + ".gif");
			setIcon(imageIcon);
		}
		else {								//  제품의 식별번호(pid)와 같은이름의 .gif파일이 프로젝트의 지정된 경로에 존재하지 않을 경우 기본 이미지 사용
			imageIcon = new ImageIcon("src\\t_teamproject\\teamproject_02\\imgs\\food\\default.jpg");
			setIcon(imageIcon);
		}
	}
	public void eventProc() {
		this.setToolTipText(p.getDetail());
	}
	public Product getP() {
		return p;
	}
	public void setP(Product p) {
		this.p = p;
	}
	
}
