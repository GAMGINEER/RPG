package game.server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ObjectIOServer {
	private static final int SERVER_PORT = 8100;
	private ServerSocket server;
	private Socket client;
	private ObjectOutputStream output;
	private ObjectInputStream input;
	
	public ObjectIOServer() {
		serverSetting();
		setStream();
		dataReceive();
		dataSend();
	}
	
	public void serverSetting() {
		while(true) {
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
				Object obj = null;
				try {
					obj = (Object) input.readObject();
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
				Object obj = null;
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
}
