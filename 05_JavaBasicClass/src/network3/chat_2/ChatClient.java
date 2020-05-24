package network3.chat_2;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.StringTokenizer;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

class ChatClient implements ActionListener, Runnable{
	JFrame f;

	JTextField connTF, sendTF;
	JButton connB, sendB;
	JTextArea ta;
	
	Socket s;
	BufferedReader in;
	BufferedWriter out;
//	OutputStream out;
	
	int port=1234;

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
		list		= new Vector();
		memberList = new JList(list);
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
		try {
			out.write("/name " + changeNameTF.getText() + "\n");
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	void connProc() {
		try {
			s=new Socket(connTF.getText(), port);
			in = new BufferedReader(new InputStreamReader(s.getInputStream()));
			out = new BufferedWriter(new OutputStreamWriter(s.getOutputStream()));
			
			new Thread(this).start();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	} // connProc ends
	
	public void run() {
		while(s.isConnected())
		{
			String msg=null;
			try {
				msg=updateMemberList(in.readLine()); // 멤버 목록을 보내주는 메세지인지 확인
//				msg=in.readLine();
			} catch (IOException e) {
				ta.append("읽기 실패");
				e.printStackTrace();
			}
			if(msg==null || msg.equals(""))
				ta.append(null);
			else
				ta.append(msg+"\n");
		}
	}
	String updateMemberList(String msg) {	// 멤버 목록을 갱신해주는 함수
		StringTokenizer st = new StringTokenizer(msg);
		if(st.nextToken().equals("/member"))
		{
			list.removeAllElements();
			while(st.hasMoreTokens())
			{
				list.add(st.nextToken());
			}
			memberList.setListData(list);
			memberList.revalidate();
			return "";
		}
		return msg;
	}

	void sendProc() {
		try {
			out.write(sendTF.getText() + "\n");
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
		sendTF.setText(null);
	}// sendProc ends
	
	public static void main(String [] args ) {
		new ChatClient();
	}
	
}// ChatClient ends
			
			

	
		
