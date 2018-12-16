package game.server;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;

public class ChatServerEx {

	private static final int SERVER_PORT = 8100;

	private ServerSocket serverSocket;
	private Socket clientSocket;

	private HashMap<String, ObjectOutputStream> threadHashMap;
	

	public ChatServerEx() {
		try {
			this.serverSocket = new ServerSocket(SERVER_PORT);
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.printf("SYSTEM >> SERVER CREATED\n");
		this.threadHashMap = new HashMap<String, ObjectOutputStream>();
		while (true) {
			try {
				this.clientSocket = this.serverSocket.accept();
				ChatServerThread clientThread = new ChatServerThread(this.clientSocket, this.threadHashMap);
				Thread chatServerThread = new Thread(clientThread);
				chatServerThread.start();
			} catch (IOException e) {
				e.printStackTrace();
				System.exit(0);
			}
		}
	}

	public static void main(String[] args) {
		new ChatServerEx();
	}

}
