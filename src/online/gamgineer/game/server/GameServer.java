package online.gamgineer.game.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class GameServer {
	private final int PORT = 8080;
	private ServerSocket server=null;
	private Socket client=null;
	private ExecutorService tf = Executors.newCachedThreadPool();

	// 서버셋팅
	public void serverSetting() {
		try {
			while (true) {
				server = new ServerSocket(PORT);
				client = server.accept();
				joinClient reicever = new joinClient(client);
				tf.execute(reicever);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// 클라이언트 접속 쓰레드
	public class joinClient extends Thread {
		private DataOutputStream dos=null;
		private DataInputStream dis=null;
		private Socket socket=null;

		public joinClient(Socket socket) {
			this.socket = socket;
		}

		@Override
		public void run() {
			try {
				dos = new DataOutputStream(socket.getOutputStream());
				dis = new DataInputStream(socket.getInputStream());
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
	}

	public static void main(String[] args) {
		GameServer GS = new GameServer();
		GS.serverSetting();
	}
}
