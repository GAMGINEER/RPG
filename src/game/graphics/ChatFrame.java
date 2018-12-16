package game.graphics;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class ChatFrame extends JFrame implements ActionListener {
	private String ip;
	private final static int SERVER_PORT = 8100;
	private Socket client = null;
	private ObjectOutputStream oos;
	private ObjectInputStream ois;
	private String id;
	private ReceiveDataThread rt;
	private JButton btnConnect, send, exit;
	private JTextField txtIP, txtName, txtInput;
	private JTextArea txtList;
	private CardLayout cl;
	private JPanel p2;
	private GameFrame gameFrame;

	public ChatFrame(GameFrame gameFrame) {
//		this.setUndecorated(true); // 타이틀 바 삭제
//		this.setBackground(new Color(0, 0, 0, 30)); // 반투명한 창
		this.gameFrame = gameFrame;
		this.setLocationRelativeTo(this.gameFrame);
		this.setSize(300, 400);
		cl = new CardLayout();

		JPanel connect = new JPanel();
		connect.setLayout(new BorderLayout());

		connect.add("North", new JLabel("Chating layout", JLabel.CENTER));

		JPanel connectSub = new JPanel();

		connectSub.add(new JLabel("Server IP: "));
		txtIP = new JTextField("127.0.0.1", 20);
		connectSub.add(txtIP);

		connectSub.add(new JLabel("이름: "));
		txtName = new JTextField("korean", 17);
		connectSub.add(txtName);

		connect.add("Center", connectSub);

		btnConnect = new JButton("Server Access");
		connect.add("South", btnConnect);

		btnConnect.addActionListener(this);

		JPanel chat = new JPanel();
		chat.setLayout(new BorderLayout());

		chat.add("North", new JLabel("Chatting Program", JLabel.CENTER));

		txtList = new JTextArea(10, 35);
		chat.add("Center", txtList);

		JPanel chatSub = new JPanel();
		txtInput = new JTextField("", 25);
		send = new JButton("보내기");
		exit = new JButton("나가기");

		chatSub.add(txtInput);
		chatSub.add(send);
		chatSub.add(exit);

		exit.addActionListener(this);
		send.addActionListener(this);
		txtInput.addActionListener(this);
		chat.add("South", chatSub);

		p2 = new JPanel();
		p2.setLayout(cl);
		p2.add(connect, "접속창");
		p2.add(chat, "채팅창");

		add(p2);

		cl.show(p2, "접속창");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}

	public void init() throws IOException {
		ip = txtIP.getText();
		client = new Socket(ip, SERVER_PORT);
		oos = new ObjectOutputStream(client.getOutputStream());
		ois = new ObjectInputStream(client.getInputStream());

		id = txtName.getText();
		oos.writeObject(id);
		oos.flush();

		rt = new ReceiveDataThread();
		Thread t = new Thread(rt);
		t.start();

		cl.show(p2, "채팅창");
		txtInput.requestFocus();
	}

	public void actionPerformed(ActionEvent e) {
		try {
			if (e.getSource() == btnConnect)
				init();
			else if (e.getSource() == txtInput || e.getSource() == send) {
				String sendData = txtInput.getText();
				oos.writeObject(sendData);
				oos.flush();
			} else if (e.getSource() == exit)
				System.exit(0);
		} catch (IOException ie) {
			txtList.append(ie.getMessage() + "\n");
		}
	}

	class ReceiveDataThread implements Runnable {
		String receiveData;

		public void run() {
			try {
				while (true) {
					receiveData = (String) ois.readObject();
					txtList.append(receiveData + "\n");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}