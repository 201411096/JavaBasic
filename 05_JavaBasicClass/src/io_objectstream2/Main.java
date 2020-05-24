package io_objectstream2;
import javax.swing.JFrame;
import javax.swing.JLabel;

//config test코드
public class Main {
	public static void main(String[] args) {
		Configuration config = Configuration.getInstance();
		config.setCol_seat_num(4);
		config.setRow_seat_num(4);

		System.out.println(config.getCol_seat_num());
		System.out.println(config.getRow_seat_num());
		config.setTotalConfiguration();
		config.getTotalConfiguration();
		config.setCol_seat_num(3);
		System.out.println(config.getCol_seat_num());
		System.out.println(config.getRow_seat_num());
	}
} 
