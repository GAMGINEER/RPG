package online.gamgineer.game.gui;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GameControl implements KeyListener {

	private static final int SPEED = 10;

	private GamePanel gamePanel;

	public GameControl(GamePanel gamePanel) {
		this.gamePanel = gamePanel;
	}

	@Override
	public void keyPressed(KeyEvent ke) {
		System.out.println(ke);
		if (ke.getKeyCode() == KeyEvent.VK_UP) {
			gamePanel.movePC(0, -(SPEED));
		}
		if (ke.getKeyCode() == KeyEvent.VK_DOWN) {
			gamePanel.movePC(0, SPEED);
		}
		if (ke.getKeyCode() == KeyEvent.VK_LEFT) {
			gamePanel.movePC(-(SPEED), 0);
		}
		if (ke.getKeyCode() == KeyEvent.VK_RIGHT) {
			gamePanel.movePC(SPEED, 0);
		}
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
	}

}
