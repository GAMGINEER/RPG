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
	}

	public void serverSetting() {
		try {
			System.out.println("서버생성중");
			server = new ServerSocket(SERVER_PORT);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		Runnable serR = new Runnable() {
			public void run() {
				while (true) {
					try {
						System.out.println("생성완료! 클라이언트 접속 대기중");
						client = server.accept();
						System.out.println("접속 완료");
					} catch (IOException e) {
						e.printStackTrace();
					}
					setStream();
				}
			}
		};
		Thread serT = new Thread(serR);
		serT.start();
	}

	public void setStream() {
		try {
			output = new ObjectOutputStream(client.getOutputStream());
			output.flush();
			System.out.println("아웃풋 열림");
			input = new ObjectInputStream(client.getInputStream());
			System.out.println("인풋 열림");
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
