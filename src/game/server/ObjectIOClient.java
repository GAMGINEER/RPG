package game.server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import game.io.ObjectSave;
import game.object.Player;
import game.save.Payload;
import game.save.Save;

import java.util.Scanner;

public class ObjectIOClient {
	private static final int SERVER_PORT = 8100;
	private static final String SERVER_IP = "edu.gamgineer.online";
	private Socket client;
	private ObjectOutputStream output;
	private ObjectInputStream input;
	private Scanner sc = new Scanner(System.in);

	public ObjectIOClient() {
		connect();
		setStream();
		dataSend();
		dataReceive();
	}

	public void connect() {
		try {
			client = new Socket(SERVER_IP, SERVER_PORT);
		} catch (IOException e) {
			e.printStackTrace();
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
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
				Save save = inputObj.getSave();
				Player player = inputObj.getPlayer();
				try {
					ObjectSave.Output(save, "save");
					ObjectSave.Output(player, "player");
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
				Payload obj = new Payload(sc.next());
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
