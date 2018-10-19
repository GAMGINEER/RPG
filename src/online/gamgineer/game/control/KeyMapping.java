package online.gamgineer.game.control;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyMapping implements KeyListener {

	private static final int SPEED = 10;

	private GamePanel gamePanel;

	public KeyMapping(GamePanel gamePanel) {
		this.gamePanel = gamePanel;
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(KeyEvent e) {
		System.out.println(e);
		if (e.getKeyCode() == KeyEvent.VK_UP) {
			gamePanel.movePC(0, -(SPEED));
		}
		if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			gamePanel.movePC(0, SPEED);
		}
		if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			gamePanel.movePC(-(SPEED), 0);
		}
		if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			gamePanel.movePC(SPEED, 0);
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

}
