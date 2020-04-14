package t_teamproject_02;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.net.Socket;

public class MainServer {
	public static void main(String[] args) {
		Configuration config = Configuration.getInstance();
		config.getTotalConfiguration();
//		config.setCol_seat_num(7);
//		config.setRow_seat_num(7);
		if(config.getInitialize()==0)
			config.initialize();
		
		MainServerFrame m = new MainServerFrame();
	}
}
