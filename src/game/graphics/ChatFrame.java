package game.graphics;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
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

import game.server.ReceiveDataThread;

public class ChatFrame extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	private String ip;
	private final static int SERVER_PORT = 8100;
	private Socket client = null;
	private ObjectOutputStream oos;
	private ObjectInputStream ois;
	private String id;
	private ReceiveDataThread rt;
	private JButton btnConnect, send, exit;
	private JTextField txtIP, txtName, txtInput;
	public static JTextArea txtList;
	private CardLayout cl;
	private JPanel p2;
	private GameFrame gameFrame;
	private static int line = 0;

	public ChatFrame(GameFrame gameFrame) {
		this.setAlwaysOnTop(true); //항상 다른 JFrame보다 위에 있도록 설정
		this.gameFrame = gameFrame;
		this.setLocationRelativeTo(this.gameFrame.getItemPanel());
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setBounds((dim.width/2)+(gameFrame.getWidth()/5), (dim.height/2)-(gameFrame.getHeight()/30), 250, 400);
		cl = new CardLayout();

		JPanel connect = new JPanel();
		connect.setLayout(new BorderLayout());

		connect.add("North", new JLabel("Chating layout", JLabel.CENTER));

		JPanel connectSub = new JPanel();

		connectSub.add(new JLabel("Server IP: "));
		txtIP = new JTextField("edu.gamgineer.online", 20);
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
		txtInput = new JTextField("", 20);
		send = new JButton("보내기");
		exit = new JButton("나가기");

		chatSub.add(txtInput);
		chatSub.add(send);
		chatSub.add(exit);
		chatSub.setPreferredSize(new Dimension(250, 60));

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
		setVisible(true);
		
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				dispose();
				System.exit(0);
			}
		});
	}

	public void init() throws IOException {
		ip = txtIP.getText();
		client = new Socket(ip, SERVER_PORT);
		oos = new ObjectOutputStream(client.getOutputStream());
		ois = new ObjectInputStream(client.getInputStream());

		id = txtName.getText();
		oos.writeObject(id);
		oos.flush();

		rt = new ReceiveDataThread(client, ois);
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
				txtInput.setText("");
				this.line++;
				if(line>=20) {
					txtList.setText("");
					line=0;
				}
			} else if (e.getSource() == exit)
				System.exit(0);
		} catch (IOException ie) {
			txtList.append(ie.getMessage() + "\n");
		}
	}

//	class ReceiveThread implements Runnable {
//		String receiveData;
//
//		public void run() {
//			while (true) {
//				try {
//					receiveData = (String) ois.readObject();
//					txtList.append(receiveData + "\n");
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		}
//	}

}