package game.server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.HashMap;

public class ChatServerThread implements Runnable {

	private Socket clientSocket;
	private ObjectInputStream objectInputStream;
	private ObjectOutputStream objectOutputStream;

	private String userID;
	private HashMap<String, ObjectOutputStream> threadHashMap;

	public ChatServerThread(Socket clientSocket, HashMap<String, ObjectOutputStream> threadHashMap) {
		this.clientSocket = clientSocket;
		this.threadHashMap = threadHashMap;
		try {
			System.out.printf("SYSTEM >> CLIENT CONNECTED%s\n", this.clientSocket.getInetAddress().toString());
			this.objectInputStream = new ObjectInputStream(this.clientSocket.getInputStream());
			this.objectOutputStream = new ObjectOutputStream(this.clientSocket.getOutputStream());
			this.userID = (String) this.objectInputStream.readObject();
			this.broadcast("SERVER >> " + this.userID + "CONNECTED");
			System.out.printf("SYSTEM >> CLIENT ID: %s CONNECTED\n", this.userID);
			synchronized (threadHashMap) {
				threadHashMap.put(this.userID, this.objectOutputStream);
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void run() {
		String payloadString;
		while (true) {
			try {
				payloadString = (String) this.objectInputStream.readObject();
				if (payloadString.equals("!quit")) {
					break;
				} else if (payloadString.indexOf("!to") > -1) {
					this.sendMessage(payloadString);
				} else {
					this.broadcast("USER " + this.userID + " >> " + payloadString);
				}
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				synchronized (this.threadHashMap) {
					this.threadHashMap.remove(this.userID);
				}
				this.broadcast("SERVER >> " + this.userID + " DISCONNECTED");
				System.out.printf("SYSTEM >> CLIENT ID: %s DISCONNECTED\n", this.userID);
				try {
					if (this.clientSocket != null) {
						this.clientSocket.close();
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

	}

	@SuppressWarnings("unused")
	public void broadcast(String message) {
		synchronized (this.threadHashMap) {
			try {
				for (ObjectOutputStream objectOutputStream : threadHashMap.values()) {
					this.objectOutputStream.writeObject(message);
					this.objectOutputStream.flush();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public void sendMessage(String message) {
		int begin = message.indexOf("") + 1;
		int end = message.indexOf("", begin);
		if (end != -1) {
			String userID = message.substring(begin, end);
			String msg = message.substring(end + 1);
			ObjectOutputStream objectOutputStream = threadHashMap.get(userID);
			try {
				if (objectOutputStream != null) {
					objectOutputStream.writeObject("PRIVATE (" + userID + ") >> " + msg);
					objectOutputStream.flush();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}