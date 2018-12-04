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
			dos = new DataOutputStream(client.getOutputStream());
			dis = new DataInputStream(client.getInputStream());
		}catch(IOException e) {
			System.out.println("Error : "+e);
			interrupt();
		}
	}
}