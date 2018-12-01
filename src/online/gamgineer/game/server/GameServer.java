package online.gamgineer.game.server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class GameServer {
	private final int PORT = 8080;
	private ServerSocket server;
	private Socket client;
	
	//서버셋팅
	public void serverSetting() {
		try {
			while(true) {
				server = new ServerSocket(PORT);
				client = server.accept();
				joinClient reicever = new joinClient(client);
				reicever.start();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//클라이언트 접속 쓰레드
	class joinClient extends Thread {
		private ObjectOutputStream oos;
		private ObjectInputStream ois;
		
		public joinClient(Socket socket) {
			try {
				oos = new ObjectOutputStream(socket.getOutputStream());
				ois = new ObjectInputStream(socket.getInputStream());
			}catch(IOException e) {
				e.printStackTrace();
			}
		}
		
		@Override
		public void run() {
			
		}
	}
	
	public static void main(String[] args) {
		GameServer GS = new GameServer();
		GS.serverSetting();
	}
}
