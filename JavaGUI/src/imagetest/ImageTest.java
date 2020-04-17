package imagetest;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JTextField;

public class ImageTest extends JFrame{
	ImageIcon i = new ImageIcon("src\\imagetest\\image\\employee\\abc123.jpg");
	JButton image=null;
	public ImageTest() {
		setLayout(new GridLayout(3,1));
		image = new JButton("");
		image.setIcon(i);
		JTextField jt = new JTextField();
		JButton bfilechooser = new JButton("a");
		add(image);
		add(jt);
		add(bfilechooser);
		setVisible(true);
		setSize(1920, 1080);
		jt.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String temp = jt.getText();
				String path ="src\\imagetest\\image\\employee\\"+temp;
				ImageIcon i2 = new ImageIcon(path);
				image.setIcon(i2);
			}
		});
		image.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//jfc 사용 형변환은 되지 않음
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
		
						FileOutputStream out = new FileOutputStream("src\\imagetest\\image\\employee\\" + "fff.jpg");
						out.write(bytes);
						out.close();
					}catch (Exception e1) {
						e1.printStackTrace();
					}
				}
			}
		});
	}
	
	public static void main(String[] args) {
		ImageTest i = new ImageTest();
	}
}
