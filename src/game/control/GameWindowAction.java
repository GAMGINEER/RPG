package game.control;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import game.graphics.GamePanel;

public class GameWindowAction extends WindowAdapter {

	private GamePanel gamePanel;

	public GameWindowAction(GamePanel gamePanel) {
		this.gamePanel = gamePanel;
	}

	@Override
	public void windowClosed(WindowEvent e) {
		System.out.println("DEBUGGING\tCLOSED");
	}

	@Override
	public void windowClosing(WindowEvent we) {
		this.gamePanel.getSave().outputData();
	}

}
