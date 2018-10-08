package online.gamgineer.game.gui;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GameControl implements KeyListener {

	private GamePanel gamePanel;

	public GameControl(GamePanel gamePanel) {
		this.gamePanel = gamePanel;
	}

	@Override
	public void keyPressed(KeyEvent ke) {
		System.out.println(ke);
		if (ke.getKeyCode() == KeyEvent.VK_UP) {
			gamePanel.updatePC(0,-1);
		}
		if (ke.getKeyCode() == KeyEvent.VK_DOWN) {
		}
		if (ke.getKeyCode() == KeyEvent.VK_LEFT) {
		}
		if (ke.getKeyCode() == KeyEvent.VK_RIGHT) {
		}
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
	}

}
