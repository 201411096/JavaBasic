package t_teamproject_02;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class CliSocketInServer extends Thread{
	Socket s = null;
	BufferedReader in = null;
	BufferedWriter out = null;
	SevSocket sevsocket= null;
	String name;
	String msg;
	
	public CliSocketInServer(Socket socket, SevSocket sevsocket) throws IOException {
		s=socket;
		this.sevsocket=sevsocket;
		in = new BufferedReader(new InputStreamReader(s.getInputStream()));
		out = new BufferedWriter(new OutputStreamWriter(s.getOutputStream()));
		name = in.readLine();
		sevsocket.addClient(name, s);
	}
	
	public void run() {
		while(true)
		{
			try {
				if(in!=null) {
					msg=in.readLine();
					sevsocket.sendMessage(msg);
					sevsocket.msf.serverChatPanel.ta.append(msg);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
				
		}
	}

}
