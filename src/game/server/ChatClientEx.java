package game.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class ChatClientEx {

	private static final String SERVER_IP = "edu.gamgineer.online";
	private static final int SERVER_PORT = 8100;

	private Socket clientSocket = null;
	private BufferedReader bufferedReader;
	private ObjectOutputStream objectOutputStream;
	private ObjectInputStream objectInputStream;
	private String sendData;
	private String receiveData;

	private String userID;
	private ReceiveDataThread receiveDataThread;
	private boolean FLAG = false;

	public ChatClientEx(String userID) {
		try {
			this.userID = userID;
			this.clientSocket = new Socket(SERVER_IP, SERVER_PORT);
			this.bufferedReader = new BufferedReader(new InputStreamReader(System.in));
			this.objectInputStream = new ObjectInputStream(this.clientSocket.getInputStream());
			this.objectOutputStream = new ObjectOutputStream(this.clientSocket.getOutputStream());
			this.objectOutputStream.writeObject(this.userID);
			this.objectOutputStream.flush();
			this.receiveDataThread = new ReceiveDataThread(this.clientSocket, this.objectInputStream);
			Thread chatClientThread = new Thread(this.receiveDataThread);
			chatClientThread.start();
			while (true) {
				this.sendData = bufferedReader.readLine();
				this.objectOutputStream.writeObject(sendData);
				this.objectOutputStream.flush();
				if (this.sendData.equals("!quit")) {
					this.FLAG = true;
					break;
				}
			}
			System.out.printf("DISCONNECT\n");
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			if (!FLAG) {
				e.printStackTrace();
			}
		} finally {
			try {
				this.objectInputStream.close();
				this.objectOutputStream.close();
				this.clientSocket.close();
				System.exit(0);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) {
		if (args.length < 1) {
			System.out.print("userID");
			System.exit(0);
		}
		new ChatClientEx(args[0]);
	}

}
