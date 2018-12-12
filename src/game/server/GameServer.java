package game.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class GameServer {

	private static final int SERVER_PORT = 8080;

	private ServerSocket serverSocket;
	private Socket clientSocket;
	private DataInputStream dataInputStream;
	private DataOutputStream dataOutputStream;

	public GameServer() {
		serverSetting();
		streamSetting();
		dataRecv();
		dataSend();
	}

	private void serverSetting() {
		try {
			serverSocket = new ServerSocket(SERVER_PORT);
			System.out.printf("SERVER CREATED\n");

			clientSocket = serverSocket.accept();
			System.out.printf("CLIENT CONNECTED\n");

		} catch (IOException e) {
			System.out.printf("%s\n", e);
		}
	}

	private void streamSetting() {
		try {
			dataInputStream = new DataInputStream(clientSocket.getInputStream());
			dataOutputStream = new DataOutputStream(clientSocket.getOutputStream());
		} catch (IOException e) {
			System.out.printf("%s\n", e);
			System.exit(1);
		}
	}

	private void dataRecv() {
		new Thread(() -> {
			boolean isThread = true;
			while (isThread) {
				try {
					String payload = dataInputStream.readUTF();
					if (payload.equals("!quit")) {
						isThread = false;
						System.out.printf("SYSTEM >> CLIENT SENT EXIT SIGNAL!\n");
						System.exit(0);
					} else {
						System.out.printf("CLIENT >> %s\n", payload);
					}
				} catch (IOException e) {
					System.out.printf("%s\n", e);
					System.exit(1);
				}
			}
		}).start();
	}

	private void dataSend() {
		new Thread(() -> {
			boolean isThread = true;
			Scanner systemInputStream = new Scanner(System.in);
			String payload;
			while (isThread) {
				try {
					payload = systemInputStream.nextLine();
					if (payload.equals("!quit")) {
						isThread = false;
						dataOutputStream.writeUTF("!quit");
						System.exit(0);
					} else {
						dataOutputStream.writeUTF(payload);
						dataOutputStream.flush();
					}
				} catch (IOException e) {
					System.out.printf("%s\n", e);
					System.exit(1);
				}
			}
			systemInputStream.close();
		}).start();
	}

	public static void main(String[] args) {
		new GameServer();
	}

}
