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
	private static final int SERVER_PORT = 8099;
	private static final String SERVER_IP = "127.0.0.1";
	private Socket client;
	private ObjectOutputStream output;
	private ObjectInputStream input;
	private Scanner sc = new Scanner(System.in);
	private ObjectIOServer os;

	public ObjectIOClient() {
		connect();
		setStream();
		System.out.println("1.서버에저장  2.서버에서로드");
		int k = sc.nextInt();
		switch(k) {
		case 1:
			dataSend();
			os.dataReceive();
			break;
		case 2:
			os.dataSend();
			dataReceive();
			break;
		}
	}

	public void connect() {
		try {
			client = new Socket(SERVER_IP, SERVER_PORT);
			System.out.println("접속 완료!");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void setStream() {
		try {
			output = new ObjectOutputStream(client.getOutputStream());
			output.flush();
			System.out.println("아웃풋 열림");
			input = new ObjectInputStream(client.getInputStream());
			System.out.println("인풋열림");
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
					ObjectSave.Output(save, "map");
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
		new ObjectIOClient();
	}
}
