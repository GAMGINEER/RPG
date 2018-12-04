package online.gamgineer.game.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class GameServer extends Thread {
	private final int PORT = 8080;
	private DataOutputStream dos=null;
	private DataInputStream dis=null;
	private ServerSocket server=null;
	private Socket client=null;

	// 서버셋팅
	@Override
	public void run() {
		try {
			while (true) {
				server = new ServerSocket(PORT);
				client = server.accept();
				openStream(client);
			}
		} catch (IOException e) {
			System.out.println("Error : "+e);
			interrupt();
		}
	}
	
	public void openStream(Socket socket) {
		try {
			dos = new DataOutputStream(socket.getOutputStream());
			dis = new DataInputStream(socket.getInputStream());
		} catch (IOException e) {
			System.out.println("StreamError : "+e);
			interrupt();
		}
	}

	public static void main(String[] args) {
		new GameServer();
	}
}
