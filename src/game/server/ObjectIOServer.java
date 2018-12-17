package game.server;

import game.io.ObjectSave;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import game.save.Payload;
import java.util.Scanner;

public class ObjectIOServer {
	private static final int SERVER_PORT = 8099;
	private ServerSocket server;
	private Socket client;
	private ObjectOutputStream output;
	private ObjectInputStream input;
	private Scanner sc = new Scanner(System.in);

	public ObjectIOServer() {
		serverSetting();
		setStream();
	}

	public void serverSetting() {
		while (true) {
			try {
				server = new ServerSocket(SERVER_PORT);
				client = server.accept();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public void setStream() {
		try {
			output = new ObjectOutputStream(client.getOutputStream());
			output.flush();
			input = new ObjectInputStream(client.getInputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void dataReceive() {
		Runnable rDr = new Runnable() {
			@Override
			public void run() {
				Payload inputObj = null;
				try {
					inputObj = (Payload) input.readObject();
					ObjectSave.Output(inputObj, inputObj.getUserID());
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		};
		Thread tDr = new Thread(rDr);
		tDr.start();
	}

	public void dataSend() {
		Runnable rDs = new Runnable() {
			@Override
			public void run() {
				Payload obj = new Payload("201802058");
				try {
					output.writeObject(obj);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		};
		Thread tDs = new Thread(rDs);
		tDs.start();
	}
	
	public static void main(String[] args) {
		new ObjectIOServer();
	}
}
