package online.gamgineer.game.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Client extends Thread {
	private final int PORT = 8080;
	private final String HOST = "edu.gamgineer.online";
	private Socket client=null;
	private DataOutputStream dos=null;
	private DataInputStream dis = null;
	
	//서버와 연결
	@Override
	public void run() {
		try {
			client = new Socket(HOST, PORT);
			openStream(client);
		}catch(IOException e) {
			System.out.println("Error : "+e);
			interrupt();
		}
	}
	
	public void openStream(Socket socket) {
		try {
			dos = new DataOutputStream(socket.getOutputStream());
			dis = new DataInputStream(socket.getInputStream());
		}catch(IOException e) {
			System.out.println("StreamError : "+e);
			interrupt();
		}
	}
}