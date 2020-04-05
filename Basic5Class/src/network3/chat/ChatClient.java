package network3.chat;

import java.awt.*;
import java.awt.event.*;
import java.net.*;
import java.io.*;
import javax.swing.*;
import java.util.*;

class ChatClient implements ActionListener, Runnable {
	JFrame f;

	JTextField connTF, sendTF;
	JButton connB, sendB;
	JTextArea ta;
	
	Socket s;
	BufferedReader in;
	OutputStream out;

	// 추가0 : 대화명을 바꾸기
	JTextField changeNameTF;
	JButton    changeNameB;
	// 추가2 : 방인원의 대명 보여주기
	// 변수 선언
	JList  memberList;
	Vector list;
	
	public ChatClient() {
		f = new JFrame("Chat Client");
		

		connTF = new JTextField();
		sendTF = new JTextField();
		connB = new JButton("접 속");
		sendB = new JButton("확 인");
		ta = new JTextArea(15,40);
		
		// 추가0: 대화명 바꾸기
		changeNameTF	= new JTextField("guest", 10);
		changeNameB		= new JButton("바꾸기");
		JPanel p_changeName = new JPanel();
		p_changeName.add( new JLabel("대화명 : "),"West" );
		p_changeName.add(changeNameTF, "Center");
		p_changeName.add(changeNameB, "East");
		
		JPanel p_serverName = new JPanel();
		p_serverName.setLayout( new BorderLayout() );
		p_serverName.add( new JLabel("서버입력 : "),"West" );
		p_serverName.add(connTF, "Center");
		p_serverName.add(connB, "East");

		JPanel p_north = new JPanel();
		p_north.setLayout( new GridLayout(1, 2));
		p_north.add( p_changeName );
		p_north.add( p_serverName );

		JPanel p2 = new JPanel();
		p2.setLayout( new BorderLayout() );
		p2.add( new JLabel("메세지입력 : "),"West" );
		p2.add(sendTF,"Center");
		p2.add(sendB, "East");
		
		// 추가2 : 방인원의 대명 보여주기
		memberList = new JList();
		list		= new Vector();
		JPanel  p_east = new JPanel();
		p_east.setLayout( new BorderLayout());
		p_east.add("North", new JLabel("   우 리 방 멤 버   "));
		p_east.add("Center", memberList );

		f.getContentPane().add("North", p_north);
		f.getContentPane().add("Center", new JScrollPane(ta));
		f.getContentPane().add("South", p2);
		f.getContentPane().add("East", p_east);
		
		//f.setSize(500, 300);
		f.pack();
		f.setVisible(true);

		connTF.addActionListener(this);
		connB.addActionListener(this);
		sendTF.addActionListener(this);
		sendB.addActionListener(this);

		//  추가0: 대화명 바꾸기
		changeNameTF.addActionListener(this);
		changeNameB.addActionListener(this);
	}// 생성자 종료
	
	public void actionPerformed( ActionEvent e ) {
		Object o = e.getSource();

		if( o == connTF || o == connB ) {
			connProc();
		}
		
		else if( o == sendTF || o == sendB ) {
			sendProc();
		}

		else if( o == changeNameTF || o == changeNameB ) {
			changeNameProc();
		}
	} // actionPerformed ends
	

	void changeNameProc(){
		String nickname = "/name	"+changeNameTF.getText() +"\n";
		try {
			out.write(nickname.getBytes());
		} catch (IOException e) {
			ta.append("이름 변경 실패 : " + e.toString());
		}
	}

	void connProc() {
		String ip = connTF.getText();
		try {
			s = new Socket(ip, 1234);
			out = s.getOutputStream();
			in = new BufferedReader(new InputStreamReader(s.getInputStream())); // reader는 문자형 stream은 byte
			
			new Thread(this).start(); //쓰레드 시작 (입력 받아온 것들)
		} catch (Exception e) {
			ta.setText("접속 실패 :" + e.toString());
			e.printStackTrace();
		}
		
	} // connProc ends
	
	public void run() {			 // eventProc에서 실행
		while(s.isConnected()) { // 소켓이 열려있는 동안 계속 반복
			String msg=null; 
			
			try {
				msg = in.readLine();
			} catch (IOException e) {
				e.printStackTrace();
			} //한줄씩 읽어들임 (\n전까지 읽어냄)
			
			ta.append(msg+"\n");
		}
	}


	void sendProc() { 
		String msg = sendTF.getText() + "\n";
		try {												// try-catch 단축키 alt+shift+z
			out.write(msg.getBytes()); 
		} catch (IOException e) {
			ta.append("쓰기실패:" + e.toString());
			e.printStackTrace();
		} // getBytes -> string을 byte 배열로 바꿔줌
		sendTF.setText(null);
		
		ta.setCaretPosition(ta.getText().length()); //scroll 밑으로..? 확인 x
		ta.requestFocus();
	}// sendProc ends
	
	
	
	public static void main(String [] args ) {
		new ChatClient();
	}
	
}// ChatClient ends
