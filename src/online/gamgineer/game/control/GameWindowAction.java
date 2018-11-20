package online.gamgineer.game.control;

import java.awt.event.WindowAdapter;

import java.awt.event.WindowEvent;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import online.gamgineer.game.gui.GamePanel;
import online.gamgineer.game.io.*;

public class GameWindowAction extends WindowAdapter {

	private GamePanel gp;

	public GameWindowAction(GamePanel gp) {
		this.gp = gp;
	}

	@Override
	public void windowClosed(WindowEvent e) {
		System.out.println("!!\tCLOSED");
	}

	@Override
	public void windowClosing(WindowEvent we) {
		Scanner inputStreamScanner = new Scanner(System.in);
		System.out.println("!!\tCLOSING");
		try {
			@SuppressWarnings("unused")
			GamePanel saveData = (GamePanel) DatabaseIO.Input("SaveData");
			System.out.println("!!\t세이브 데이터가 존재합니다.");
			System.out.print("!!\t덮어쓰기 하시겠습니까?\n>> ");
			if (inputStreamScanner.next().toLowerCase().equals("y")) {
				DatabaseIO.Output(gp, "SaveData");
			} else {
				System.out.println("!!\t저장되지 않았습니다.");
			}
		} catch (FileNotFoundException e) {
			System.out.print("!!\t저장하시겠습니까?\n>> ");
			if (inputStreamScanner.next().toLowerCase().equals("y")) {
				try {
					DatabaseIO.Output((Object) gp, "SaveData");
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			} else {
				System.out.println("!!\t저장되지 않았습니다.");
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (NullPointerException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		inputStreamScanner.close();
	}

}
