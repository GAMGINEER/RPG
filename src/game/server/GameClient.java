package game.server;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class GameClient {

	private Socket clientSocket;
	private InputStream inputStream;
	private ObjectInputStream objectInputStream;
	private OutputStream outputStream;
	private ObjectOutputStream objectOutputStream;

	public GameClient() {
		connect();
		streamSetting();
		dataRecv();
		dataSend();
	}

	public void connect() {
		try {
			System.out.printf("SYSTEM >> CONNECTING...\n");
			this.clientSocket = new Socket("edu.gamgineer.online", 8100);
			System.out.printf("SYSTEM >> CONNECTED!\n");
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void streamSetting() {
		try {
			this.inputStream = this.clientSocket.getInputStream();
			this.outputStream = this.clientSocket.getOutputStream();
			this.objectOutputStream = new ObjectOutputStream(this.outputStream);
			this.objectOutputStream.flush();
			this.objectInputStream = new ObjectInputStream(this.inputStream);
		} catch (IOException e) {
			System.out.printf("%s\n", e.toString());
		}
	}

	public void dataRecv() {
		new Thread(new Runnable() {
			boolean isThread = true;

			@Override
			public void run() {
				System.out.printf("SYSTEM >> DATA RECV MODULE READY\n");
				while (isThread) {
					try {
						Object payload = objectInputStream.readObject();
						String payloadString = (String) payload;
						if (payloadString.equals("!quit")) {
							this.isThread = false;
							System.out.printf("SYSTEM >> SERVER SENT EXIT SIGNAL!\n");
							System.exit(0);
						} else {
							System.out.printf("SERVER >> %s\n", payloadString);
						}
					} catch (IOException e) {
						e.printStackTrace();
						System.exit(1);
					} catch (ClassNotFoundException e) {
						e.printStackTrace();
						System.exit(1);
					}
				}
			}
		}).start();
	}

	public void dataSend() {
		new Thread(new Runnable() {
			Scanner in = new Scanner(System.in);
			boolean isThread = true;

			@Override
			public void run() {
				System.out.printf("SYSTEM >> DATA SEND MODULE READY\n");
				while (isThread) {
					try {
						String payloadString = in.nextLine();
						Object payload = (Object) payloadString;
						// if (payloadString.equals("!quit")) {
						// this.isThread = false;
						// System.exit(0);
						// } else {
						objectOutputStream.writeObject(payload);
						objectOutputStream.flush();
						// }
					} catch (IOException e) {
						e.printStackTrace();
						System.exit(1);
					}
				}
			}
		}).start();
	}

	public static void main(String[] args) {
		new GameClient();
	}

}