package t_teamproject_02;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class CliSocket implements Runnable{
	MainClientFrame mcf = null;
	
	Socket s;
	BufferedReader in;
	BufferedWriter out;
	String ip="192.168.56.1";
	int port=1234;
	
	String name="test";
	
	public CliSocket(MainClientFrame mcf) { //서버와 연결을 하면서 클라이언트 소켓의 이름을 보냄
		this.mcf=mcf;
		try {
			s= new Socket(ip, port);
			in = new BufferedReader(new InputStreamReader(s.getInputStream()));
			out = new BufferedWriter(new OutputStreamWriter(s.getOutputStream()));
			
			out.write("/socketname: "+name);
			out.flush();
			
			new Thread(this).start();
		} catch (Exception e) {
			e.printStackTrace();
		} 		
	}
	public void sendMessage(String msg) { //서버 소켓으로 보내면서 클라이언트 화면 초기화
		try {
			out.write(msg);
			mcf.clientChatPanel.sendTF.setText("");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {			//socket이 연결되있는동안 서버로부터 계속 읽고 클라이언트 화면에 계속 붙임
		while(s.isConnected())
		{
			String msg=null;
			try {
				msg=in.readLine();
			} catch (IOException e) {
				e.printStackTrace();
			}
			if(msg==null || msg.equals(""))
				mcf.clientChatPanel.ta.append(null);
			else
				mcf.clientChatPanel.ta.append(msg+"\n");
		}
	}
}
