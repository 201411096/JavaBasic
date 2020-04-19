package t_teamproject.teamproject_02.view.button;

import java.awt.Color;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import t_teamproject.teamproject_02.vo.Product;

public class MenuButton extends JButton{
	Product p;
	ImageIcon imageIcon;
	public MenuButton(Product p) {
		this.p=p;
		display();
		setImageIcon();
	}
	public void display() {
		setBackground(Color.white);
	}
	public void setImageIcon() {
		if(new File("src\\t_teamproject\\teamproject_02\\imgs\\food\\" + p.getId() + ".jpg").exists()) {
			imageIcon = new ImageIcon("src\\t_teamproject\\teamproject_02\\imgs\\food\\" + p.getId() + ".jpg");
			setIcon(imageIcon);
		}
		else {
			imageIcon = new ImageIcon("src\\t_teamproject\\teamproject_02\\imgs\\food\\default.jpg");
			setIcon(imageIcon);
		}
	}
}
