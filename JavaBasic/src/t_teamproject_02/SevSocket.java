package t_teamproject_02;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Iterator;

public class SevSocket implements Runnable{
	MainServerFrame msf = null;
	
	ServerSocket ss = null;
	Socket s = null;
	int port=1234;
	HashMap<String, Socket> clientMap = new HashMap<String, Socket>();
	
	public SevSocket(MainServerFrame msf) {
		this.msf=msf;
		try {
			ss = new ServerSocket(port);
		} catch (Exception e) {
			e.printStackTrace();
		}
		new Thread(this).start();
	}
	public void run() {
		try {
			s = ss.accept();
			CliSocketInServer clisocketinserver = new CliSocketInServer(s, this);
			clisocketinserver.start();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void addClient(String name, Socket s){
		sendMessage(name + "님이 접속하셨습니다.");
		clientMap.put(name, s);
	}

	public void removeClient(String name) {
		sendMessage(name + "님이 나가셨습니다.");
		clientMap.remove(name);
	}

	// 메시지 내용 전파
	public void sendMessage(String msg) {
		Iterator<String> it = clientMap.keySet().iterator();
		String key = "";
		while (it.hasNext()) {
			key = it.next();
			try {
				clientMap.get(key).getOutputStream().write(msg.getBytes());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
}
