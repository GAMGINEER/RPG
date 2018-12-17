package game.server;

import game.graphics.ChatFrame;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

public class ReceiveDataThread implements Runnable {

	private Socket clientSocket;
	private ObjectInputStream objectInputStream;
	private String receiveData;

	public ReceiveDataThread(Socket clientSocket, ObjectInputStream objectInputStream) {
		this.clientSocket = clientSocket;
		this.objectInputStream = objectInputStream;
	}

	@Override
	public void run() {
		try {
			while ((this.receiveData = (String) this.objectInputStream.readObject()) != null) {
				ChatFrame.txtList.append(receiveData+"\n");
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				this.objectInputStream.close();
				this.clientSocket.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
